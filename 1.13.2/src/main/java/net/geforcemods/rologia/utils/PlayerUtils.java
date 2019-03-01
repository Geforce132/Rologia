package net.geforcemods.rologia.utils;

import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class PlayerUtils {

	/**
	 * Sets the given player's position and rotation. <p>
	 *
	 * Args: player, x, y, z, yaw, pitch.
	 */
	public static void setPlayerPosition(EntityPlayer player, double x, double y, double z, float yaw, float pitch) {
		player.setPositionAndRotation(x, y, z, yaw, pitch);
		player.setPositionAndUpdate(x, y, z);
	}

	/**
	 * Gets the EntityPlayer instance of a player (if they're online) using their name. <p>
	 *
	 * Args: playerName.
	 */
	public static EntityPlayer getPlayerFromName(String par1) {
		if(FMLLoader.getDist() == Dist.CLIENT)
		{
			List<?> players = Minecraft.getInstance().world.playerEntities;
			Iterator<?> iterator = players.iterator();

			while(iterator.hasNext())
			{
				EntityPlayer tempPlayer = (EntityPlayer) iterator.next();
				
				if(tempPlayer.getName().equals(par1))
					return tempPlayer;
			}

			return null;
		}
		else
		{
			List<?> players = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers();
			Iterator<?> iterator = players.iterator();

			while(iterator.hasNext())
			{
				EntityPlayer tempPlayer = (EntityPlayer) iterator.next();

				if(tempPlayer.getName().equals(par1))
					return tempPlayer;
			}

			return null;
		}
	}

	public static String getPlayerUUID(EntityPlayer player) {
		return player.getGameProfile().getId().toString();
	}

	/**
	 * Returns true if a player with the given name is in the world.
	 *
	 * Args: playerName.
	 */
	public static boolean isPlayerOnline(String par1) {

		if(FMLLoader.getDist() == Dist.CLIENT)
		{
			for(int i = 0; i < Minecraft.getInstance().world.playerEntities.size(); i++)
			{
				EntityPlayer player = Minecraft.getInstance().world.playerEntities.get(i);

				if(player != null && player.getName().equals(par1))
					return true;
			}

			return false;
		}
		else
			return (ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerByUsername(par1) != null);
	}

	/**
	 * Returns true if the player is holding the given item.
	 *
	 * Args: player, item.
	 */
	public static boolean isHoldingItem(EntityPlayer player, Item item) {
		if(item == null && player.inventory.getCurrentItem().isEmpty())
			return true;

		return (!player.inventory.getCurrentItem().isEmpty() && player.inventory.getCurrentItem().getItem() == item);
	}
	
	/**
	 * Returns true if the player is holding the given item.
	 *
	 * Args: player, item.
	 */
	public static Item getHeldItem(EntityPlayer player, Item item) {
		if(!isHoldingItem(player, item)) return null;
		
		if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == item)
			return player.getHeldItemMainhand().getItem();
		if(player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() == item)
			return player.getHeldItemOffhand().getItem();
		
		return null;
	}
	
}