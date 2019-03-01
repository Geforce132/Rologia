package net.geforcemods.rologia.network;

import java.util.ArrayList;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.imc.IRologiaMessageHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ServerProxy implements IProxy {

	public void setupProxy() {}

	@OnlyIn(Dist.CLIENT)
	public RologiaOS getRologiaInstance() {
		return null;
	}

	@OnlyIn(Dist.CLIENT)
	public void registerMessageHandler(IRologiaMessageHandler handler) {}

	@OnlyIn(Dist.CLIENT)
	public void setRologiaInstance(RologiaOS rologia) {}

	@OnlyIn(Dist.CLIENT)
	public ArrayList<IRologiaMessageHandler> getHandlers() {
		return null;
	}

}
