package spot.hardcore;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import spot.hardcore.commands.HardCoreCommand;
import spot.hardcore.config.Config;
import spot.hardcore.limbo.Limbo;
import spot.hardcore.limbo.LimboResurrectionCountdown;
import spot.hardcore.limbo.NecrologyEntry;
import spot.hardcore.listeners.PlayerListener;

public final class HardCore extends JavaPlugin implements Listener {
	private static HardCore instance;
	
	private static Config config;
	
	private static Logger LOGGER;
	
	public static Limbo LIMBO;
	
	private BukkitTask Countdown;
	
	private static List<NecrologyEntry> necrology = new ArrayList<>();
	
    @Override
    public void onEnable() {
		instance = this;
		LOGGER = new Logger();
		validateFiles();
    	config = new Config();
		getCommand("hardcore").setExecutor(new HardCoreCommand());
		LIMBO = new Limbo();
		registerEvents(new Listener[] { new PlayerListener() });
		Countdown = new LimboResurrectionCountdown(this).runTaskTimer(this, 0L, 20L);
    }
    
    @Override
    public void onDisable() {
    	// TODO Make this do something
    }
    
    public static Config getPluginConfig() {
        return config;
    }
    
    public static HardCore getInstance() {
    	return instance;
    	}
    
    public static Logger getLog() {
    	return LOGGER;
    }
    
    public static Limbo getLimbo() {
    	return LIMBO;
    }
    
    public static List<NecrologyEntry> getNecrology() {
    	return necrology;
    }
    
    public static void Necrology(List<NecrologyEntry> list) {
    	necrology = list;
    }
    
    private void validateFiles() {
        LOGGER.info("Validating configuration files...");
        FileValidator(new String[] { "config.yml" });
      }
      
    public static void FileValidator (String... fileNames) {
        for (String name : fileNames) {
          File file = new File(instance.getDataFolder(), name);
          if (!file.exists())
            instance.saveResource(name, false); 
        } 
    }

    public static void registerEvents(Listener... listeners) {
        JavaPlugin plugin = instance;
        for (Listener l : listeners)
          Bukkit.getServer().getPluginManager().registerEvents(l, (Plugin)plugin); 
      }


}



