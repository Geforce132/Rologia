package net.geforce.smartwatch.item;

import net.geforce.smartwatch.MineWatch;
import net.geforce.smartwatch.gui.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMineWatch extends Item {
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.openGui(MineWatch.instance, GuiHandler.BOOT_SCREEN_ID, world, (int) player.posX, (int) player.posY, (int) player.posZ);
		return stack;
	}

}
