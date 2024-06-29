package spot.hardcore.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import spot.hardcore.HardCore;
import spot.hardcore.Logger;

public class HardCoreCommand implements CommandExecutor, TabExecutor {
	Logger LOGGER = HardCore.getLog();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if (args.length < 1) return false;
    	switch(args[0].toUpperCase()) {
    	case "RELOAD":
    		LOGGER.debug("Reloading configuration files");
    		HardCore.getPluginConfig().reload();
    		if (sender instanceof Player) {
    			sender.sendMessage("All configuration files have been reloaded.");
    		}
    		return true;
    	}
		return false;
    
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List<String> Tab = new ArrayList<String> ();
		
		if (args.length == 1) {
			Tab.clear();
			Tab.add("RELOAD");
			return Tab;
		} else if (args.length > 1) {
				Tab.clear();
				Tab.add("");
				return Tab;
		}
		return null;
	}

}
