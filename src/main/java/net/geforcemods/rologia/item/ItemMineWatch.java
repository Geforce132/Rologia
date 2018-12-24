package net.geforcemods.rologia.item;

import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.gui.GuiHandler;
import net.geforcemods.rologia.os.RologiaOS;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemMineWatch extends Item {
	
	@Override
	public boolean isFull3D(){
		return true;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack itemStackIn = player.getHeldItem(hand);

		if(player.isSneaking()) {
			RologiaOS.removeInstanceForPlayer(player);
			return ActionResult.newResult(EnumActionResult.PASS, itemStackIn);
		}

		if(world.isRemote)
			return ActionResult.newResult(EnumActionResult.PASS, itemStackIn);
		else{
			player.openGui(Rologia.instance, GuiHandler.BOOT_SCREEN_ID, world, (int)player.posX, (int)player.posY, (int)player.posZ);
			return ActionResult.newResult(EnumActionResult.PASS, itemStackIn);
		}
	}

}
