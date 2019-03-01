package net.geforcemods.rologia.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IInteractionObject;

public class BaseInteractionObject implements IInteractionObject
{
	protected final ResourceLocation id;

	public BaseInteractionObject(ResourceLocation id)
	{
		this.id = id;
	}

	@Override
	public ITextComponent getName()
	{
		return new TextComponentString(id.toString());
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
	}

	@Override
	public ITextComponent getCustomName()
	{
		return null;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player)
	{
		return null;
	}

	@Override
	public String getGuiID()
	{
		return id.toString();
	}
}
