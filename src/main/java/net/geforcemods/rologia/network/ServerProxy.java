package net.geforcemods.rologia.network;

import java.util.ArrayList;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.imc.IRologiaMessageHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ServerProxy {

	public ArrayList<IRologiaMessageHandler> messageHandlers = new ArrayList<IRologiaMessageHandler>();

	public void setupProxy() {}

	public RologiaOS getRologiaInstance() {
		return null;
	}

	@SideOnly(Side.CLIENT)
	public void registerMessageHandler(IRologiaMessageHandler handler) {}

	@SideOnly(Side.CLIENT)
	public void setRologiaInstance(RologiaOS rologia) {}

	@SideOnly(Side.CLIENT)
	public ArrayList<IRologiaMessageHandler> getHandlers() {
		return messageHandlers;
	}

}
