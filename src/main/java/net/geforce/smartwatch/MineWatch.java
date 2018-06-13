package net.geforce.smartwatch;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.geforce.smartwatch.events.MWEventHandler;
import net.geforce.smartwatch.gui.GuiHandler;
import net.geforce.smartwatch.item.ItemMineWatch;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid=MineWatch.MOD_ID, name=MineWatch.MOD_NAME,version=MineWatch.VERSION)
public class MineWatch {
	
	public static final String MOD_ID = "rologia";
	public static final String MOD_NAME = "Rologia";
	public static final String VERSION = "0.0.1";
	
	@Instance(MOD_ID)
	public static MineWatch instance = new MineWatch();
	
	public static SimpleNetworkWrapper network;
	public static MWEventHandler eventHandler = new MWEventHandler();
	private GuiHandler guiHandler = new GuiHandler();
	
	public static Item mineWatch;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		network = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
		
		mineWatch = new ItemMineWatch().setCreativeTab(CreativeTabs.tabRedstone).setMaxStackSize(1).setUnlocalizedName("mineWatch");
		GameRegistry.registerItem(mineWatch, "mineWatch");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(eventHandler);
	}

}
