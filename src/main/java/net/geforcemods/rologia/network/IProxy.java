package net.geforcemods.rologia.network;

import java.util.ArrayList;

import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.os.imc.IRologiaMessageHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface IProxy {
	
	public void setupProxy();

	@OnlyIn(Dist.CLIENT)
	public RologiaOS getRologiaInstance();

	@OnlyIn(Dist.CLIENT)
	public void registerMessageHandler(IRologiaMessageHandler handler);

	@OnlyIn(Dist.CLIENT)
	public void setRologiaInstance(RologiaOS rologia);

	@OnlyIn(Dist.CLIENT)
	public ArrayList<IRologiaMessageHandler> getHandlers();

}
