package net.geforcemods.rologia;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.imc.IRologiaMessageHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class MessageHandler implements IRologiaMessageHandler {

	@Override
	public void handleMessage(RologiaOS os, World world, EntityPlayer recipient, String key, String body) {
		System.out.printf("Rologia message: %s %s %s\n", body, FMLCommonHandler.instance().getEffectiveSide(), os);
	}

}
