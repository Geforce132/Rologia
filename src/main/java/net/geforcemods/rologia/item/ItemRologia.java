package net.geforcemods.rologia.item;

import net.geforcemods.rologia.gui.BaseInteractionObject;
import net.geforcemods.rologia.gui.GuiHandler;
import net.geforcemods.rologia.os.RologiaOS;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ItemRologia extends Item {
	
	public static final String NAME = "smart_watch";

	public ItemRologia() {
		super(new Item.Properties().group(ItemGroup.REDSTONE).maxStackSize(1));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack itemStackIn = player.getHeldItem(hand);

		if(player.isSneaking()) {
			RologiaOS.removeInstance();
			return ActionResult.newResult(EnumActionResult.PASS, itemStackIn);
		}

		if(world.isRemote)
			return ActionResult.newResult(EnumActionResult.PASS, itemStackIn);
		else{
			NetworkHooks.openGui((EntityPlayerMP) player, new BaseInteractionObject(GuiHandler.WATCH_SCREEN));
			return ActionResult.newResult(EnumActionResult.PASS, itemStackIn);
		}
	}

}
