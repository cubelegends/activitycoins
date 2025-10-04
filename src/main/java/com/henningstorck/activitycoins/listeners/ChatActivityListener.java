package com.henningstorck.activitycoins.listeners;

import com.henningstorck.activitycoins.Config;
import com.henningstorck.activitycoins.activities.ActivityMapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatActivityListener implements Listener {
	private final Config config;
	private final ActivityMapper activityMapper;

	public ChatActivityListener(Config config, ActivityMapper activityMapper) {
		this.config = config;
		this.activityMapper = activityMapper;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onChat(AsyncPlayerChatEvent event) {
		this.activityMapper.addActivity(event.getPlayer(), this.config.getChatWorth());
	}
}
