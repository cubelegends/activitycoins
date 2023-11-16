package de.cubelegends.activitycoins.listeners;

import de.cubelegends.activitycoins.Config;
import de.cubelegends.activitycoins.activities.ActivityMapper;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class KillActivityListener implements Listener {
    private final Config config;
    private final ActivityMapper activityMapper;

    public KillActivityListener(Config config, ActivityMapper activityMapper) {
        this.config = config;
        this.activityMapper = activityMapper;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDeath(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getDamager();

        if (player.getGameMode() != GameMode.SURVIVAL) {
            return;
        }

        this.activityMapper.addActivity(player, this.config.getKillWorth());
    }
}
