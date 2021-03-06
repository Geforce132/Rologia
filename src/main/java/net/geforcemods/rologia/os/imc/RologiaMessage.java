package net.geforcemods.rologia.os.imc;

import net.geforcemods.rologia.utils.Utils;
import net.minecraft.entity.player.PlayerEntity;

public class RologiaMessage {

	public final String senderName;
	public final String recipientName;
	public final String key;
	public final String body;

	public RologiaMessage(String sendingPlayer, String receivingPlayer, String messageKey, String messageBody) {
		this.senderName = sendingPlayer;
		this.recipientName = receivingPlayer;
		this.key = messageKey;
		this.body = messageBody;
	}

	public PlayerEntity getSender() {
		if(Utils.isPlayerOnline(senderName)) {
			return Utils.getPlayerFromName(recipientName);
		}

		return null;
	}

	public PlayerEntity getRecipient() {
		if(Utils.isPlayerOnline(senderName)) {
			return Utils.getPlayerFromName(recipientName);
		}

		return null;
	}

}
