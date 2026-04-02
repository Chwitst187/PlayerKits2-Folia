package pk.ajneb97.utils;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public class FoliaScheduler {

    private final Plugin plugin;

    public FoliaScheduler(Plugin plugin) {
        this.plugin = plugin;
    }

    public ScheduledTask runAsync(Runnable runnable) {
        return Bukkit.getAsyncScheduler().runNow(plugin, task -> runnable.run());
    }

    public ScheduledTask runAsyncLater(Runnable runnable, long delayTicks) {
        return Bukkit.getAsyncScheduler().runDelayed(plugin, task -> runnable.run(), ticksToMillis(delayTicks), TimeUnit.MILLISECONDS);
    }

    public ScheduledTask runAsyncRepeating(Runnable runnable, long initialDelayTicks, long periodTicks) {
        long safeInitialDelayTicks = Math.max(1L, initialDelayTicks);
        long safePeriodTicks = Math.max(1L, periodTicks);
        return Bukkit.getAsyncScheduler().runAtFixedRate(
                plugin,
                task -> runnable.run(),
                ticksToMillis(safeInitialDelayTicks),
                ticksToMillis(safePeriodTicks),
                TimeUnit.MILLISECONDS
        );
    }

    public ScheduledTask runGlobal(Runnable runnable) {
        return Bukkit.getGlobalRegionScheduler().run(plugin, task -> runnable.run());
    }

    public ScheduledTask runGlobalLater(Runnable runnable, long delayTicks) {
        return Bukkit.getGlobalRegionScheduler().runDelayed(plugin, task -> runnable.run(), delayTicks);
    }

    public ScheduledTask runGlobalRepeating(Runnable runnable, long initialDelayTicks, long periodTicks) {
        long safeInitialDelayTicks = Math.max(1L, initialDelayTicks);
        long safePeriodTicks = Math.max(1L, periodTicks);
        return Bukkit.getGlobalRegionScheduler().runAtFixedRate(plugin, task -> runnable.run(), safeInitialDelayTicks, safePeriodTicks);
    }

    public ScheduledTask runAtLocation(Location location, Runnable runnable) {
        return Bukkit.getRegionScheduler().run(plugin, location, task -> runnable.run());
    }

    public ScheduledTask runAtLocationLater(Location location, Runnable runnable, long delayTicks) {
        return Bukkit.getRegionScheduler().runDelayed(plugin, location, task -> runnable.run(), delayTicks);
    }

    public ScheduledTask runAtPlayer(Player player, Runnable runnable) {
        return runAtLocation(player.getLocation(), runnable);
    }

    public ScheduledTask runAtPlayerLater(Player player, Runnable runnable, long delayTicks) {
        return runAtLocationLater(player.getLocation(), runnable, delayTicks);
    }

    private long ticksToMillis(long ticks) {
        return ticks * 50L;
    }
}
