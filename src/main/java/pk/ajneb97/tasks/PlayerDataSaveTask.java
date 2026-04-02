package pk.ajneb97.tasks;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import pk.ajneb97.PlayerKits2;
import pk.ajneb97.utils.FoliaScheduler;

public class PlayerDataSaveTask {

	private PlayerKits2 plugin;
	private boolean end;
	private ScheduledTask task;
	private FoliaScheduler scheduler;
	public PlayerDataSaveTask(PlayerKits2 plugin) {
		this.plugin = plugin;
		this.end = false;
		this.scheduler = new FoliaScheduler(plugin);
	}
	
	public void end() {
		end = true;
		if(task != null) {
			task.cancel();
		}
	}
	
	public void start(int seconds) {
		long ticks = seconds* 20L;
		task = scheduler.runAsyncRepeating(() -> {
			if(!end) {
				execute();
			}
		}, 0L, ticks);
	}
	
	public void execute() {
		plugin.getConfigsManager().getPlayersConfigManager().saveConfigs();
	}
}
