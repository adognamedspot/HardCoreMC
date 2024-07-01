# HardCore ToDo List

## Startup

- [x] Register Logger
- [x] Validate Configuration Files
- [x] Register Config
- [x] Register Command Executor
- [x] Check for Limbo World - create if doesn't exist
- [ ] Check Permissions Group [Limbo] - create if doesn't exist
	
## GamePlay:

#### onEnable()

- [ ] Load NecrologyEntries from disk

#### onDisable()

- [ ] Save NecrologyEntries to disk

#### onLogin()

- [x] Check to see if they need to be sent to Limbo
- [x] Remind them how many lives left

#### onDeath()

###### PvE Death

- [x] Remove 1 Life
- [x] Check Remaining Lives - if 0 send to Limbo

###### PvP Death - (config option) 

- [ ] both lose 1 life 
- [ ] Killer doesn't actually die unless out of lives
	
## Limbo:

- [x] Reminder of how long they have left in limbo (Boss Bar?)
- [x] Locked in place
- [x] Spectator Mode
- [ ] Permissions Group [Limbo]
- [x] Shows Death prefix with gray name in Tab and chat

## Commands:

#### HardCore

- [x] ***RELOAD*** - Reloads configuration file
- [x] ***SETLIVES*** - Sets a player's number of lives
		
## Configuration:

- [x] Time in Limbo - **String** - `Number of Hours spent in Limbo before being resurrected ie: 1d 2h 3m 4s`
- [x] Number of Lives - **int** - `Number of Lives before sent to Limbo`
- [ ] PvP bad - **boolean** - `Killer loses 1 life for killing another player`
- [ ] Limbo Chat - **boolean** - `Can chat in Limbo`
- [x] Limbo Prefix - **String** - `Prefix to show in Tab List - [Dead]`

## Bugs so far:

- [ ] Dead prefix only shows up for other players and in chat
- [ ] Players in Limbo stuck in Limbo after server restart - Save to file needed