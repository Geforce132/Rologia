package net.geforcemods.smartwatch;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class RegistrationHandler {
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerResourceLocations(ModelRegistryEvent event)
	{
		ModelLoader.setCustomModelResourceLocation(MineWatch.mineWatch, 0, new ModelResourceLocation("rologia:mine_watch", "inventory"));
	}

}
