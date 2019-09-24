package net.geforcemods.rologia.gui;

import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.item.ItemRologia;
import net.geforcemods.rologia.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.FMLPlayMessages.OpenContainer;

public class GuiHandler {
	
	public static final ResourceLocation WATCH_SCREEN = new ResourceLocation(Rologia.MOD_ID, "watch_screen");

	public static GuiScreen getClientGuiElement(OpenContainer message) {
		EntityPlayerSP player = Minecraft.getInstance().player;

		if(message.getId().equals(WATCH_SCREEN)) {
			if(Utils.isHoldingItem(player, Rologia.smart_watch)) {
				return new GuiRologia(player, (ItemRologia) player.inventory.getCurrentItem().getItem());
			}
		}

		return null;
	}

}
