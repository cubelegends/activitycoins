package de.cubelegends.activitycoins.listeners;

import de.cubelegends.activitycoins.Config;
import de.cubelegends.activitycoins.activities.ActivityMapper;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class FishingActivityListener implements Listener {
    private final Config config;
    private final ActivityMapper activityMapper;

    public FishingActivityListener(Config config, ActivityMapper activityMapper) {
        this.config = config;
        this.activityMapper = activityMapper;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerFishEvent(PlayerFishEvent event) {
        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH) {
            return;
        }

        if (event.getPlayer().getGameMode() != GameMode.SURVIVAL) {
            return;
        }

        this.activityMapper.addActivity(event.getPlayer(), this.config.getFishingWorth());
    }
}
