package com.henningstorck.activitycoins.listeners;

import com.henningstorck.activitycoins.activities.ActivityMapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitListener implements Listener {
	private final ActivityMapper activityMapper;

	public PlayerJoinQuitListener(ActivityMapper activityMapper) {
		this.activityMapper = activityMapper;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent event) {
		this.activityMapper.addPlayerIfNotAdded(event.getPlayer());
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerQuit(PlayerQuitEvent event) {
		this.activityMapper.removePlayer(event.getPlayer());
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerKick(PlayerKickEvent event) {
		this.activityMapper.removePlayer(event.getPlayer());
	}
}
