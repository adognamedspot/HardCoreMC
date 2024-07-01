package spot.hardcore.limbo;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import spot.hardcore.HardCore;
import spot.hardcore.Logger;

public class Limbo {
	
	World WORLD;
	
	Logger LOGGER = HardCore.getLog();
	
	public Limbo() {
			enableWorld();
			WORLD = Bukkit.getWorld("world_limbo");
	}
	
	public static void enableWorld() {
		WorldCreator wc = new WorldCreator("world_limbo");
		wc.environment(Environment.NETHER);
		wc.generator(new VoidChunkGenerator());
		wc.createWorld();
	}
	
	public World getWorld() {
		return WORLD;
	}
	
	public boolean transportPlayer(Player player) {
		LOGGER.debug("transportPlayer() - Setting GameMode to Spectator");
		player.setGameMode(GameMode.SPECTATOR);
		player.setAllowFlight(true);
		player.setDisplayName(String.format("%s §8§o%s", HardCore.getPluginConfig().getDeadPrefix(), player.getName()));
		player.setPlayerListName(String.format("%s §8§o%s", HardCore.getPluginConfig().getDeadPrefix(), player.getName()));
		Location limbo = new Location(WORLD, 0, 100, 0);
		LOGGER.debug("transportPlayer() - Transporting Player to Limbo");
		return player.teleport(limbo);
	}
	
	public boolean resurrectPlayer(Player player) {
		LOGGER.debug("resurrectPlayer() - Setting GameMode to Survival");
		player.setGameMode(GameMode.SURVIVAL);
		player.setAllowFlight(false);
		player.setDisplayName(player.getName());
		player.setPlayerListName(player.getName());
		player.setPlayerListHeader(null);
		LOGGER.debug("resurrectPlayer() - Transporting Player to ReSpawn point");
		Location respawn = player.getRespawnLocation();
		if (respawn == null)
			respawn = Bukkit.getWorld("world").getSpawnLocation();
		return player.teleport(respawn);
	}
}
