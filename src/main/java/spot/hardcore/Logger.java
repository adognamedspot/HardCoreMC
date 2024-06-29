package spot.hardcore;

import org.bukkit.Bukkit;

public class Logger {
	
	/**
	 * Sends a message to the console if Debug is Enabled
	 * 
	 * @param msg  Message to send to the console
	 */
	public void debug(String msg) {
			if (HardCore.getPluginConfig().getDebugEnabled()) {
				if (HardCore.getPluginConfig().getDebugChatEnabled()) {
					Bukkit.broadcastMessage(String.format("[HC] §4(§eDEBUG§4)§r: %s", msg));
				} else {
					Bukkit.getConsoleSender().sendMessage(String.format("[HardCore] §4(§eDEBUG§4)§r: %s", msg));
				}
			}
			
	}
	public void debug(Object... args) {
	    String format = new String(new char[args.length])
	        .replace("\0", "%s");
	    debug(String.format(format, args));
	}
	/**
	 * Sends a message to the console
	 * 
	 * @param msg  Message to send to the console
	 */
	public void console(String msg) {
		Bukkit.getConsoleSender().sendMessage(String.format("[HardCore] %s", msg));
	}
	
	public void info(String msg) {
		Bukkit.getLogger().info(String.format("[HardCore] %s", msg));
	}
	
	
	/**
	 * Sends a warning message to the console
	 * 
	 * @param msg  Warning to send to the console
	 */
	public void warning(String msg) {
		Bukkit.getLogger().warning(String.format("[HardCore] %s", msg));
	}
	
}
