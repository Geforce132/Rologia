package net.geforcemods.rologia.network.packets;

import io.netty.buffer.ByteBuf;
import net.geforcemods.rologia.Rologia;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketCSendRologiaMessage implements IMessage {

	private String sender;
	private String message;

	public PacketCSendRologiaMessage() {

	}

	public PacketCSendRologiaMessage(String sender, String message) {
		this.sender = sender;
		this.message = message;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		sender = ByteBufUtils.readUTF8String(buf);
		message = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, sender);
		ByteBufUtils.writeUTF8String(buf, message);
	}

	public static class Handler implements IMessageHandler<PacketCSendRologiaMessage, IMessage> {

		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(PacketCSendRologiaMessage packet, MessageContext ctx) {
			Rologia.serverProxy.getRologiaInstance().getIMCManager().handleRologiaMessage(packet.sender, packet.message);
			return null;
		}

	}

}
