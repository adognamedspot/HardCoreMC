# HardCore ToDo List

## Startup

- [x] Register Logger
- [x] Validate Configuration Files
- [x] Register Config
- [x] Register Command Executor
- [x] Check for Limbo World - create if doesn't exist
- [ ] Check Permissions Group [Limbo] - create if doesn't exist
	
## GamePlay:

#### onLogin()

- [x] Check to see if they need to be sent to Limbo

#### onDeath()

###### PvE Death

- [x] Remove 1 Life
- [x] Check Remaining Lives - if 0 send to Limbo

###### PvP Death - (config option) 

- [ ] both lose 1 life 
- [ ] Killer doesn't actually die unless out of lives
	
## Limbo:

- [ ] Reminder of how long they have left in limbo (Boss Bar?)
- [x] Locked in place
- [x] Spectator Mode
- [ ] Permissions Group [Limbo]
- [ ] Shows Limbo prefix with gray name in Tab

## Commands:

#### HardCore

- [x] ***RELOAD*** - Reloads configuration file
- [ ] ***LIMBO*** - Sends player to/Removes player from Limbo
		
## Configuration:

- [ ] Time in Limbo - **double** - `Number of Hours spent in Limbo before being resurrected`
- [x] Number of Lives - **int** - `Number of Lives before sent to Limbo`
- [ ] PvP bad - **boolean** - `Killer loses 1 life for killing another player`
- [ ] Limbo Chat - **boolean** - `Can chat in Limbo`
- [ ] Limbo Prefix - **String** - `Prefix to show in Tab List - [Dead]`
