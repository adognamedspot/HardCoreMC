package spot.hardcore.listeners;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.md_5.bungee.api.ChatColor;
import spot.hardcore.HardCore;
import spot.hardcore.limbo.Limbo;

public class PlayerListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		checkLives(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		setLives(player, getLives(player) - 1);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		event.setTo(event.getFrom());
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		checkLives(event.getPlayer());
	}
	
	private int getLives(Player player) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();
		NamespacedKey key = new NamespacedKey(HardCore.getInstance(), "HC_Lives");
		if (pdc.has(key, PersistentDataType.INTEGER)) {
			return pdc.get(key, PersistentDataType.INTEGER);
		}
		return HardCore.getPluginConfig().getDefaultLives();
	}
	
	private void setLives(Player player, int lives) {
		PersistentDataContainer pdc = player.getPersistentDataContainer();
		NamespacedKey key = new NamespacedKey(HardCore.getInstance(), "HC_Lives");
		pdc.set(key, PersistentDataType.INTEGER, lives);
	}
	
	private void checkLives(Player player) {
		if (getLives(player) <= 0) {
			setLives(player, 0);
			announceDead(player);
			Limbo.transportPlayer(player);
		} else {
			announceLives(player);
		}
	}
	
	private void announceLives(Player player) {
		player.sendTitle(String.format("You have %d lives remaining.", getLives(player)), ChatColor.YELLOW + "Use them wisely", 10, 70, 20);
	}

	private void announceDead(Player player) {
		player.sendTitle(ChatColor.RED + "You have 0 lives remaining.", "You are dead dead", 10, 70, 20);
	}

}
