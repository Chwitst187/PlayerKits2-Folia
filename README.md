# PlayerKits2
https://www.spigotmc.org/resources/playerkits-2-fully-configurable-kits-1-8-1-20.112616/

## Folia/Paper troubleshooting

If you see an error like this while using `/plugman load`:

`attempted to add duplicate plugin identifier PlayerKits2`

That means the server is trying to register PlayerKits2 twice in the same runtime (usually because of PlugMan/PlugManX hot-loading).  
On modern Paper/Folia, this is unsupported and can break plugin state.

### Safe update flow

1. Stop the server completely.
2. Replace the old `PlayerKits2` jar in `plugins/`.
3. Delete stale remapped copies if they exist:
   - `plugins/.paper-remapped/unknown-origin/PlayerKits2-*.jar`
4. Start the server again.

Avoid using PlugMan/PlugManX to load/reload PlayerKits2 on Folia/Paper.
