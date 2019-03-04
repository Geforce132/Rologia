package net.geforcemods.rologia.os.imc;

import java.util.logging.Level;

import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.network.packets.CSendRologiaMessage;
import net.geforcemods.rologia.network.packets.SSendRologiaMessage;
import net.geforcemods.rologia.os.RologiaOS;
import net.geforcemods.rologia.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.thread.EffectiveSide;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.network.NetworkDirection;

public class IMCManager {
	
	public static final String IMC = "imc";
	public static final String IM = "im";
	
	public static final String SEPARATOR = "$";

	public static void sendMessage(String sendingPlayerName, String receivingPlayerName, String key, String body) {
		if(!PlayerUtils.isPlayerOnline(receivingPlayerName)) return;

		EntityPlayer recipient = PlayerUtils.getPlayerFromName(receivingPlayerName);
		LogicalSide side = EffectiveSide.get();
		RologiaMessage message = new RologiaMessage(sendingPlayerName, receivingPlayerName, key, body);

		if(side == LogicalSide.CLIENT) {
			Rologia.channel.sendToServer(new SSendRologiaMessage(message));

			for(IRologiaMessageHandler handler : Rologia.instance.serverProxy.getHandlers())
				handler.handleMessage(Rologia.instance.serverProxy.getRologiaInstance(), recipient.world, message);
		}
		else {
			Rologia.channel.sendTo(new CSendRologiaMessage(message), ((EntityPlayerMP) recipient).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);

			for(IRologiaMessageHandler handler : Rologia.instance.serverProxy.getHandlers())
				handler.handleMessage(null, recipient.world, message);
		}
	}
	
	@SuppressWarnings("static-access")
	public static void registerMessageHandler(String classPath) {
		try {
			Class<?> handlerClass = Class.forName(classPath);
			IRologiaMessageHandler handler = (IRologiaMessageHandler) handlerClass.newInstance();

			if(FMLLoader.getDist() == Dist.CLIENT) {
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
