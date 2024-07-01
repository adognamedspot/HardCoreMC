package spot.hardcore.utils;

import spot.hardcore.HardCore;

public class TimeUtils {

	public static boolean timeIsUp(long deathTime) {
		if ((deathTime + timeInLimbo2millis()) <= System.currentTimeMillis()) {
			return true;
		}
		return false;
	}

	public static String timeUntilResurrection(long deathTime) {
		long elapsed = System.currentTimeMillis() - deathTime;
		long duration = timeInLimbo2millis() - elapsed;
		long second = (duration / 1000) % 60;
		long minute = (duration / (1000 * 60)) % 60;
		long hour = (duration / (1000 * 60 * 60)) % 24;

		return String.format("%02d:%02d:%02d", hour, minute, second);
	}
	
	/**
	 * Takes a string and converts it to millis
	 * @param time ie: 1d 8h 14m 53s
	 * @return time in millis
	 */
	public static long timeInLimbo2millis() {
		long day = 86400000;
		long hour = 3600000;
		long minute = 60000;
		long second = 1000;
		int days = 0;
		int hours = 0;
		int minutes = 0;
		int seconds = 0;
		
		String[] split = HardCore.getPluginConfig().getDefaultTimeInLimbo().split(" ");
		for (int i = 0; i < split.length; i++) {
			if (split[i].toLowerCase().contains("d")) {
				days = Integer.parseInt(split[i].substring(0, split[i].toString().length() - 1));
			} else if (split[i].toLowerCase().contains("h")) {
				hours = Integer.parseInt(split[i].substring(0, split[i].toString().length() - 1));
			} else if (split[i].toLowerCase().contains("m")) {
				minutes = Integer.parseInt(split[i].substring(0, split[i].toString().length() - 1));
			} else if (split[i].toLowerCase().contains("s")) {
				seconds = Integer.parseInt(split[i].substring(0, split[i].toString().length() - 1));
			} else {
				// Invalid String Format
			}
		}
		
		return (days * day) + (hours * hour) + (minutes * minute) + (seconds * second);
	}
}
