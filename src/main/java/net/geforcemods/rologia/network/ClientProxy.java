package net.geforcemods.rologia.network;

import net.geforcemods.rologia.os.RologiaOS;
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

	public void setRologiaInstance(RologiaOS rologia) {
		instance = rologia;
	}

}
