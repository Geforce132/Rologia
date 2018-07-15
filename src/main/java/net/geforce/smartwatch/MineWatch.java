package net.geforce.smartwatch;

import java.util.HashMap;

import net.geforce.smartwatch.events.RologiaEventHandler;
import net.geforce.smartwatch.gui.GuiHandler;
import net.geforce.smartwatch.item.ItemMineWatch;
import net.geforce.smartwatch.network.ServerProxy;
import net.geforce.smartwatch.rologia.os.Rologia;
import net.geforce.smartwatch.rologia.os.apps.App;
import net.geforce.smartwatch.rologia.os.apps.events.AppEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.GameData;

@Mod(modid=MineWatch.MOD_ID, name=MineWatch.MOD_NAME,version=MineWatch.VERSION)
public class MineWatch {
	
	public static final String MOD_ID = "rologia";
	public static final String MOD_NAME = "Rologia";
	public static final String VERSION = "0.0.1";
	
	@Instance(MOD_ID)
	public static MineWatch instance = new MineWatch();
	
	@SidedProxy(clientSide = "net.geforce.smartwatch.network.ClientProxy", serverSide = "net.geforce.smartwatch.network.ServerProxy")
	public static ServerProxy serverProxy;

	public static SimpleNetworkWrapper network;
	public static RologiaEventHandler eventHandler = new RologiaEventHandler();
	private GuiHandler guiHandler = new GuiHandler();

	@SideOnly(Side.CLIENT)
	public HashMap<String, Rologia> rologiaInstances = new HashMap<String, Rologia>();
	
	public static Item mineWatch;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		network = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
		
		mineWatch = new ItemMineWatch().setCreativeTab(CreativeTabs.REDSTONE).setMaxStackSize(1).setRegistryName("mine_watch").setUnlocalizedName("mineWatch");
		GameData.register_impl(mineWatch);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(eventHandler);
	}

	public void postRologiaEvent(AppEvent event) {
		for(Rologia os : rologiaInstances.values()) {
			for(App app : os.getApps())
			{
				System.out.println(os.getApps().size());
				System.out.println("posting " + os);
				app.onEventPosted(event);
			}
		}
	}

}
