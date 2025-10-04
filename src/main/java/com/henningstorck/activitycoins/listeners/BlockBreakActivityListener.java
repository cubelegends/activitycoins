package com.henningstorck.activitycoins.listeners;

import com.henningstorck.activitycoins.Config;
import com.henningstorck.activitycoins.activities.ActivityMapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakActivityListener implements Listener {
	private final Config config;
	private final ActivityMapper activityMapper;

	public BlockBreakActivityListener(Config config, ActivityMapper activityMapper) {
		this.config = config;
		this.activityMapper = activityMapper;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockBreak(BlockBreakEvent event) {
		double worth = 0.0;

		switch (event.getPlayer().getGameMode()) {
			case SURVIVAL:
				worth = this.config.getBlockBreakSurvivalWorth();
				break;
			case CREATIVE:
				worth = this.config.getBlockBreakCreativeWorth();
				break;
		}

		this.activityMapper.addActivity(
			event.getPlayer(),
			event.getBlock().getLocation(),
			worth
		);
	}
}
