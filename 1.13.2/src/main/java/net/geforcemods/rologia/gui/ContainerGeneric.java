package net.geforcemods.rologia.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerGeneric extends Container {

	public ContainerGeneric() {}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

}
