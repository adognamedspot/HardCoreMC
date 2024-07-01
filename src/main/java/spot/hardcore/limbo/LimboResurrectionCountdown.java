package spot.hardcore.limbo;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import spot.hardcore.HardCore;
import spot.hardcore.Logger;
import spot.hardcore.utils.PlayerUtils;
import spot.hardcore.utils.TimeUtils;

public class LimboResurrectionCountdown extends BukkitRunnable {

	Logger LOGGER = HardCore.getLog();
	
	public LimboResurrectionCountdown(HardCore plugin) {
		
	}
	
	@Override
	public void run() {
		List<NecrologyEntry> list = HardCore.getNecrology();
		for (NecrologyEntry e : list) {
			OfflinePlayer player = Bukkit.getOfflinePlayer(e.getUUID());
			if (player.isOnline()) {
				if (e.resurrected()) {
					PlayerUtils.resetPlayer(player.getPlayer());
					break;
				}
				PlayerUtils.announceTime(player.getPlayer(), TimeUtils.timeUntilResurrection(e.getTOD()));
			}
			if (TimeUtils.timeIsUp(e.getTOD())) {
				e.needsToBeResurrected(true);
			}
		}
	}

}
