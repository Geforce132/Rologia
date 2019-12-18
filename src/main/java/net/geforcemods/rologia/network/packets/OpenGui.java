package net.geforcemods.rologia.network.packets;

import java.util.function.Supplier;

import net.geforcemods.rologia.Rologia;
import net.geforcemods.rologia.containers.ContainerGeneric;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkHooks;

public class OpenGui {

	private ResourceLocation id;
	private int dimId;
	private BlockPos pos;

	public OpenGui(){}

	public OpenGui(ResourceLocation id, int dimId, BlockPos pos){
		this.id = id;
		this.dimId = dimId;
		this.pos = pos;
	}

	public void toBytes(PacketBuffer buf) {
		buf.writeResourceLocation(id);
		buf.writeInt(dimId);
		buf.writeBlockPos(pos);
	}

	public void fromBytes(PacketBuffer buf) {
		id = buf.readResourceLocation();
		dimId = buf.readInt();
		pos = buf.readBlockPos();
	}

	public static void encode(OpenGui message, PacketBuffer packet)
	{
		message.toBytes(packet);
	}

	public static OpenGui decode(PacketBuffer packet)
	{
		OpenGui message = new OpenGui();

		message.fromBytes(packet);
		return message;
	}

	public static void onMessage(OpenGui message, Supplier<NetworkEvent.Context> ctx)
	{
		ctx.get().enqueueWork(() -> {
			ResourceLocation id = message.id;
			BlockPos pos = message.pos;
			ServerPlayerEntity player = ctx.get().getSender();

			if(id.equals(Rologia.cTypeSmartWatch.getRegistryName()))
			{
				NetworkHooks.openGui(player, new INamedContainerProvider() {
					@Override
					public Container createMenu(int windowId, PlayerInventory inv, PlayerEntity player)
					{
						return new ContainerGeneric(Rologia.cTypeSmartWatch, windowId);
					}

					@Override
					public ITextComponent getDisplayName()
					{
						return new TranslationTextComponent(Rologia.smart_watch.getTranslationKey());
					}
				}, pos);
			};
		});

		ctx.get().setPacketHandled(true);
	}
}
