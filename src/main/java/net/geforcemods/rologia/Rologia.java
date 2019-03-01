package net.geforcemods.rologia;

import net.geforcemods.rologia.events.RologiaEventHandler;
import net.geforcemods.rologia.gui.GuiHandler;
import net.geforcemods.rologia.item.ItemRologia;
import net.geforcemods.rologia.network.ClientProxy;
import net.geforcemods.rologia.network.IProxy;
import net.geforcemods.rologia.network.ServerProxy;
import net.geforcemods.rologia.network.packets.CSendRologiaMessage;
import net.geforcemods.rologia.network.packets.SSendRologiaMessage;
import net.geforcemods.rologia.os.imc.IMCManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.registries.ObjectHolder;

@Mod(value=Rologia.MOD_ID)
public class Rologia {
	
	public static final String MOD_ID = "rologia";
	public static final String MOD_NAME = "Rologia";
	public static final String VERSION = "0.0.1";
	
	public static Rologia instance;
	
    public static IProxy serverProxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

	public static final String PROTOCOL_VERSION = "1.0";
	public static SimpleChannel channel = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, MOD_ID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	public static RologiaEventHandler eventHandler = new RologiaEventHandler();
	private GuiHandler guiHandler = new GuiHandler();
	private IMCManager imcManager = new IMCManager();

	@ObjectHolder(MOD_ID + ":"+ ItemRologia.NAME)
	public static Item smartwatch;
	
	public Rologia() {
		instance = this;
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onFMLCommonSetup);
		MinecraftForge.EVENT_BUS.register(new RologiaEventHandler());
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY, () -> GuiHandler::getClientGuiElement);
	}
	
	public void onFMLCommonSetup(FMLCommonSetupEvent event) //preInit
	{
		channel.registerMessage(0, CSendRologiaMessage.class, CSendRologiaMessage::encode, CSendRologiaMessage::decode, CSendRologiaMessage::onMessage);
		channel.registerMessage(1, SSendRologiaMessage.class, SSendRologiaMessage::encode, SSendRologiaMessage::decode, SSendRologiaMessage::onMessage);
	}
	
	@SubscribeEvent
	public static void registerItems(Register<Item> event)
	{
		event.getRegistry().register(new ItemRologia().setRegistryName(smartwatch.getRegistryName()));
	}
	
	@SubscribeEvent
	public void init(InterModEnqueueEvent event) {
		serverProxy.setupProxy();
		
		InterModComms.sendTo(MOD_ID, "register", () -> {return "net.geforcemods.rologia.MessageHandler";});
	}

	@SubscribeEvent
	public void processIMCMessages(InterModProcessEvent event) {
		event.getIMCStream().forEach(message -> {
			if(message.getMethod().equalsIgnoreCase("register")) {
				//TODO
				System.out.println(message.getMessageSupplier().get());
				//Supplier<Function<Void, Void>> supplier = message.getMessageSupplier().get();
                //supplier.get().apply(null);
			}
		});
	}

}
