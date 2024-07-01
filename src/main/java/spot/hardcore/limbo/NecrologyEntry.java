package spot.hardcore.limbo;

import java.util.UUID;

import org.bukkit.entity.Player;

public class NecrologyEntry {
	
	private UUID uuid;
	private long timeOfDeath;
	private boolean needToBeResurrected;
	
	/**
	 * Creates an entry in the Necrology
	 * @param player Player to be entered
	 */
	public NecrologyEntry(Player player) {
		this.uuid = player.getUniqueId();
		this.timeOfDeath = System.currentTimeMillis();
	}
	
	/**
	 * Returns UUID of player
	 * @return UUID
	 */
	public UUID getUUID() {
		return this.uuid;
	}
	
	/**
	 * Returns player's Time of Death
	 * @return time in millis
	 */
	public long getTOD() {
		return this.timeOfDeath;
	}
	
	/**
	 * Sets whether player needs to be resurrected.
	 * @param tf true/false
	 */
	public void needsToBeResurrected(boolean tf) {
		this.needToBeResurrected = tf;
	}

	/**
	 * Time has ended - waiting to be resurrected.
	 * @return True if resurrection impending, False if still waiting
	 */
	public boolean resurrected() {
		return this.needToBeResurrected;
	}
	
}
