package com.henningstorck.activitycoins.listeners;

import com.henningstorck.activitycoins.Config;
import com.henningstorck.activitycoins.activities.ActivityMapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CmdActivityListener implements Listener {
	private final Config config;
	private final ActivityMapper activityMapper;

	public CmdActivityListener(Config config, ActivityMapper activityMapper) {
		this.config = config;
		this.activityMapper = activityMapper;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
		this.activityMapper.addActivity(event.getPlayer(), this.config.getCommandWorth());
	}
}
