package net.geforcemods.rologia.item;

import net.geforcemods.rologia.gui.GuiRologia;
import net.geforcemods.rologia.os.RologiaOS;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemRologia extends Item {
	
	public static final String NAME = "smart_watch";

	public ItemRologia() {
		super(new Item.Properties().group(ItemGroup.REDSTONE).maxStackSize(1));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack itemStackIn = player.getHeldItem(hand);

		if(player.isCrouching()) {
			RologiaOS.removeInstance();
			return ActionResult.func_226250_c_(itemStackIn);
		}

		if(world.isRemote) {
			//System.out.println(Rologia.channel);
			//System.out.println(Rologia.cTypeSmartWatch.getRegistryName());
			//Rologia.channel.sendToServer(new OpenGui(Rologia.cTypeSmartWatch.getRegistryName(), world.getDimension().getType().getId(), player.getPosition()));
			Minecraft.getInstance().displayGuiScreen(new GuiRologia(player, (ItemRologia) player.getHeldItem(hand).getItem()));
			return ActionResult.func_226250_c_(itemStackIn);
		}
		else {
			return ActionResult.func_226250_c_(itemStackIn);
		}
	}

}
