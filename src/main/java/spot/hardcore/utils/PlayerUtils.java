package spot.hardcore.utils;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.md_5.bungee.api.ChatColor;
import spot.hardcore.HardCore;
import spot.hardcore.Logger;
import spot.hardcore.limbo.NecrologyEntry;

public class PlayerUtils {
	
	static Logger LOGGER = HardCore.getLog();
	
	public static int getLives(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();
		NamespacedKey key = new NamespacedKey(HardCore.getInstance(), "HC_Lives");
		if (pdc.has(key, PersistentDataType.INTEGER)) {
			return pdc.get(key, PersistentDataType.INTEGER);
		}
		return HardCore.getPluginConfig().getDefaultLives();
	}
	
	public static void setLives(Player player, int lives) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();
		NamespacedKey key = new NamespacedKey(HardCore.getInstance(), "HC_Lives");
		pdc.set(key, PersistentDataType.INTEGER, lives);
	}

	/**
	 * 
	 * @param player
	 * @return true if still alive, false if dead
	 */
	public static boolean checkLives(Player player) {
		if (getLives(player) <= 0) {
			LOGGER.debug("checkLives() - Out of Lives");
			setLives(player, 0);
			announceDead(player);
			return false;
		}
		announceLives(player);
		return true;
	}
	
	public static void resetPlayer(Player player) {
		PlayerUtils.setLives(player, HardCore.getPluginConfig().getDefaultLives());
		if (PlayerUtils.checkLives(player))
			HardCore.getLimbo().resurrectPlayer(player);
		removeNecrologyEntry(player.getUniqueId());
	}

	public static NecrologyEntry getNecrologyEntry(Player player) {
		for (NecrologyEntry e : HardCore.getNecrology()) {
			if (e.getUUID().equals(player.getUniqueId()))
				return e;
		}
		return null;
	}
	
	public static void removeNecrologyEntry(UUID uuid) {
		for (NecrologyEntry e : HardCore.getNecrology()) {
			if (e.getUUID().equals(uuid)) {
				HardCore.getNecrology().remove(e);
				break;
			}
		}
	}
	
	public static void announceLives(Player player) {
		player.sendTitle(String.format("You have %d %s remaining.", getLives(player), lifeLives(getLives(player))), ChatColor.YELLOW + "Use them wisely", 10, 70, 20);
	}

	public static void announceDead(Player player) {
		player.sendTitle(ChatColor.RED + "You have 0 lives remaining.", "You are dead dead", 10, 70, 20);
	}
	
	public static void announceTime(Player player, String time) {
		player.sendTitle(ChatColor.GREEN + time, ChatColor.WHITE + "Time Remaining before Resurrection", 0, 20, 0);
	}
	
	private static String lifeLives(int amount) {
		if (amount == 1)
			return "life";
		return "lives";
	}
	
}
