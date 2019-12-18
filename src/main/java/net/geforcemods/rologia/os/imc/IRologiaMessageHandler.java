package net.geforcemods.rologia.os.imc;

import net.geforcemods.rologia.os.RologiaOS;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IRologiaMessageHandler {

	public void handleMessage(RologiaOS os, World world, RologiaMessage message);

}
