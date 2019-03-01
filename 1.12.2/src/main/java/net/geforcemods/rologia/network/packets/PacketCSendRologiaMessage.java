package net.geforcemods.rologia.network.packets;

import io.netty.buffer.ByteBuf;
import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.os.imc.IRologiaMessageHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketCSendRologiaMessage implements IMessage {

	private String key;
	private String body;

	public PacketCSendRologiaMessage() {

	}

	public PacketCSendRologiaMessage(String key, String body) {
		this.key = key;
		this.body = body;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		key = ByteBufUtils.readUTF8String(buf);
		body = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, key);
		ByteBufUtils.writeUTF8String(buf, body);
	}

	public static class Handler implements IMessageHandler<PacketCSendRologiaMessage, IMessage> {

		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(PacketCSendRologiaMessage packet, MessageContext ctx) {
			for(IRologiaMessageHandler handler : Rologia.instance.serverProxy.getHandlers())
				handler.handleMessage(Rologia.instance.serverProxy.getRologiaInstance(), Minecraft.getMinecraft().world, Minecraft.getMinecraft().player, packet.key, packet.body);

			return null;
		}

	}

}
