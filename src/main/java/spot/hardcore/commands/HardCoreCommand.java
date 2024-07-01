package spot.hardcore.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import spot.hardcore.HardCore;
import spot.hardcore.Logger;
import spot.hardcore.limbo.Limbo;
import spot.hardcore.utils.PlayerUtils;

public class HardCoreCommand implements CommandExecutor, TabExecutor {
	Logger LOGGER = HardCore.getLog();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if (args.length < 1) return false;
    	switch(args[0].toUpperCase()) {
    	case "RELOAD":
    		return reloadCommand(sender, args);
    	case "SETLIVES":
    		return setLivesCommand(sender, args);
    	}
		return false;
    
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> Tab = new ArrayList<String> ();
		
		switch (args.length) {
		case 1:
			Tab.add("reload");
			Tab.add("setlives");
			return Tab;
		case 2:
			switch (args[0].toUpperCase()) {
			case "RELOAD":
				Tab.add("");
				return Tab;
			case "SETLIVES":
				return null;
			}
		case 3:
			if (args[0].toUpperCase().equals("SETLIVES")) {
				for (int x = 0; x <= HardCore.getPluginConfig().getDefaultLives(); x++) {
					Tab.add(String.format("%d", x));
				}
				return Tab;
			}
			Tab.add("");
			return Tab;
				
		}
		return null;
	}

	private boolean reloadCommand(CommandSender sender, String[] args) {
		LOGGER.debug("Reloading configuration files");
		HardCore.getPluginConfig().reload();
		if (sender instanceof Player) {
			sender.sendMessage("All configuration files have been reloaded.");
		}
		return true;
	}
	
	private boolean setLivesCommand(CommandSender sender, String[] args) {
		if (args.length < 3) return false;
		Player player = Bukkit.getPlayer(args[1]);
		if (player == null) {
			sender.sendMessage("Player '%s' cannot be found", args[1]);
			return true;
		}
		if (!player.isOnline()) {
			sender.sendMessage("Player '%s' is not online", args[1]);
			return true;
		}
		if (!args[2].matches("-?(0|[1-9]\\d*)")) {
			sender.sendMessage("'%s' is not a valid number", args[2]);
			return true;
		}
		if (Integer.parseInt(args[2]) > HardCore.getPluginConfig().getDefaultLives()) {
			sender.sendMessage("The maximum number of lives is %d", String.valueOf(HardCore.getPluginConfig().getDefaultLives()));
		}
		if (Integer.parseInt(args[2]) == 0) {
			// Sending them to Limbo
			PlayerUtils.setLives(player, 0);
			if (!PlayerUtils.checkLives(player))
				HardCore.getLimbo().transportPlayer(player);
			return true;
		}
		if (PlayerUtils.getLives(player) == 0 && Integer.parseInt(args[2]) > 0) {
			// Bringing them back from Limbo
			HardCore.getLimbo().resurrectPlayer(player);			
		}
		PlayerUtils.setLives(player, Integer.parseInt(args[2]));
		PlayerUtils.announceLives(player);
		return true;
	}
	
}
