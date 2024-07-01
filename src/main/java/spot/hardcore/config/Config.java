package spot.hardcore.config;

public class Config extends YamlFile {

	public Config() {
		super("config.yml");
	}

	public boolean getDebugEnabled() {
		return this.loadedFile.getBoolean("Debug.Enabled");
	}
	public boolean getDebugChatEnabled() {
		return this.loadedFile.getBoolean("Debug.BroadcastChatEnabled");
	}
	
	public int getDefaultLives() {
		return this.loadedFile.getInt("Options.DefaultNumberOfLives");
	}
	
	public String getDefaultTimeInLimbo() {
		return this.loadedFile.getString("Options.DefaultTimeInLimbo");
	}
	
	public String getDeadPrefix() {
		return this.loadedFile.getString("Options.DeadPrefix");
	}
	
}
