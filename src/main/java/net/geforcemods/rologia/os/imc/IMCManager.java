package net.geforcemods.rologia.os.imc;

import java.util.logging.Level;

import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.network.packets.PacketSSendRologiaMessage;
import net.geforcemods.rologia.os.RologiaOS;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class IMCManager {
	
	public static final String IMC = "imc";
	public static final String IM = "im";
	
	public static final String SEPARATOR = "$";

	public static void sendMessage(String destinationPlayerName, String key, String body) {
		Rologia.network.sendToServer(new PacketSSendRologiaMessage(destinationPlayerName, key, body));
	}
	
	@SuppressWarnings("static-access")
	public void registerMessageHandler(String classPath) {
		try {
			Class<?> handlerClass = Class.forName(classPath);
			IRologiaMessageHandler handler = (IRologiaMessageHandler) handlerClass.newInstance();

			if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
				Rologia.instance.serverProxy.registerMessageHandler(handler);
				RologiaOS.LOGGER.log(Level.INFO, "Registering " + classPath + "...");
			}
		}
	    catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			RologiaOS.LOGGER.log(Level.WARNING, "Registration of " + classPath + " failed!");
			e.printStackTrace();
		}
	}
}