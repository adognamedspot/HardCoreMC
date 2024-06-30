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
	
	static World WORLD;
	
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
	
	public static boolean transportPlayer(Player player) {
		player.setGameMode(GameMode.SPECTATOR);
		Location limbo = new Location(WORLD, 0, 100, 0);
		return player.teleport(limbo);
	}
}
