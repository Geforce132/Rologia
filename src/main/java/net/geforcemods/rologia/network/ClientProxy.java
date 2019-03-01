package net.geforcemods.rologia.network;

import java.util.ArrayList;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.imc.IRologiaMessageHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ClientProxy implements IProxy {

	public ArrayList<IRologiaMessageHandler> messageHandlers = new ArrayList<IRologiaMessageHandler>();

	public RologiaOS instance;

	@Override
	@OnlyIn(Dist.CLIENT)
	public void setupProxy() {

	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public RologiaOS getRologiaInstance() {
		return instance;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void registerMessageHandler(IRologiaMessageHandler handler) {
		messageHandlers.add(handler);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ArrayList<IRologiaMessageHandler> getHandlers() {
		return messageHandlers;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void setRologiaInstance(RologiaOS rologia) {
		instance = rologia;
	}

}
