package com.henningstorck.activitycoins.payout;

import com.henningstorck.activitycoins.activities.ActivityMapper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PayoutTask extends BukkitRunnable {
	private final ActivityMapper activityMapper;

	public PayoutTask(ActivityMapper plugin) {
		this.activityMapper = plugin;
	}

	@Override
	public void run() {
		this.activityMapper.setLastPayout(System.currentTimeMillis());
		for (Player player : Bukkit.getOnlinePlayers()) {
			this.activityMapper.payout(player);
		}
	}
}
