Startup:
	- Check for Limbo World 
		- create if doesn't exist
	- Check Permissions Group [Limbo]
		- create if doesn't exist
	
GamePlay:
	- onDeath()
		- PvE Death
			- Remove 1 Life
			- Check Remaining Lives
				- if 0 send to Limbo
		- PvP Death - (config option) 
			- both lose 1 life 
			- Killer doesn't actually die unless out of lives
	
Limbo:
		- Locked in place
		- Spectator Mode
		- Permissions Group [Limbo]
			- Shows Limbo prefix with gray name in Tab

Commands:
	- HardCore
		- --Reload - Reloads configuration file--
Configuration:
	- Number of Lives - int
		- Number of Lives before sent to Limbo
	- PvP bad - boolean
		- Killer loses 1 life for killing another player
	- Limbo Chat - boolean
		- Can chat in Limbo
	- Limbo Prefix - String
		- Prefix to show in Tab List - [Dead]