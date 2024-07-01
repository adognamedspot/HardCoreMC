package spot.hardcore.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import spot.hardcore.HardCore;
import spot.hardcore.Logger;
import spot.hardcore.utils.PlayerUtils;

public class PlayerListener implements Listener {
	
	Logger LOGGER = HardCore.getLog();

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (!PlayerUtils.checkLives(event.getPlayer()))
			HardCore.getLimbo().transportPlayer(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		PlayerUtils.setLives(player, PlayerUtils.getLives(player) - 1);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if (PlayerUtils.getLives(event.getPlayer()) == 0) {
			event.setTo(event.getFrom());
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		if (!PlayerUtils.checkLives(event.getPlayer())) {
			HardCore.getLimbo().transportPlayer(event.getPlayer());
			event.setRespawnLocation(new Location(HardCore.getLimbo().getWorld(), 0, 100, 0));
		}
		LOGGER.debug("onPlayerRespawn() - " + event.getRespawnLocation().toString());
			
	}
	
}
