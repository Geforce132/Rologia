package net.geforcemods.rologia.network;

import java.util.ArrayList;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.imc.IRologiaMessageHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends ServerProxy {

	public RologiaOS instance;

	@Override
	@SideOnly(Side.CLIENT)
	public void setupProxy() {

	}

	@Override
	@SideOnly(Side.CLIENT)
	public RologiaOS getRologiaInstance() {
		return instance;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerMessageHandler(IRologiaMessageHandler handler) {
		messageHandlers.add(handler);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ArrayList<IRologiaMessageHandler> getHandlers() {
		return messageHandlers;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setRologiaInstance(RologiaOS rologia) {
		instance = rologia;
	}

}
