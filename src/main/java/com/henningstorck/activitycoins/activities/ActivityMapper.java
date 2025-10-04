package com.henningstorck.activitycoins.activities;

import com.henningstorck.activitycoins.Config;
import com.henningstorck.activitycoins.blockhistory.BlockHistory;
import com.henningstorck.activitycoins.payout.PayoutObserver;
import com.henningstorck.activitycoins.vault.VaultAdapter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ActivityMapper {
	private final VaultAdapter vaultAdapter;
	private final Config config;
	private final PayoutObserver payoutObserver;
	private final Map<UUID, Activity> activityMap = new HashMap<>();

	private long lastPayout;

	public ActivityMapper(VaultAdapter vaultAdapter, Config config, PayoutObserver payoutObserver) {
		this.vaultAdapter = vaultAdapter;
		this.config = config;
		this.payoutObserver = payoutObserver;
		this.lastPayout = System.currentTimeMillis();
	}

	public void addPlayerIfNotAdded(Player player) {
		if (!this.activityMap.containsKey(player.getUniqueId())) {
			BlockHistory blockHistory = new BlockHistory(this.config.getBlockHistorySize());
			Activity activity = new Activity(blockHistory);
			activity.setMaxWorth(this.config.getMaxWorth());
			activity.setMinIncome(this.config.getMinIncome());
			activity.setMaxIncome(this.config.getMaxIncome());
			this.activityMap.put(player.getUniqueId(), activity);
		}
	}

	public void removePlayer(Player player) {
		this.activityMap.remove(player.getUniqueId());
	}

	public void addActivity(Player player, Location location, Double worth) {
		if (!this.isWorldWhitelisted(player.getWorld())) {
			return;
		}

		this.addPlayerIfNotAdded(player);
		Activity activity = this.activityMap.get(player.getUniqueId());
		activity.add(worth, location);
	}

	public void addActivity(Player player, Double worth) {
		if (!this.isWorldWhitelisted(player.getWorld())) {
			return;
		}

		this.addPlayerIfNotAdded(player);
		Activity activity = this.activityMap.get(player.getUniqueId());
		activity.add(worth);
	}

	private boolean isWorldWhitelisted(World world) {
		return this.config.getWorlds().contains(world.getName());
	}

	public void payout(Player player) {
		this.addPlayerIfNotAdded(player);
		Activity activity = this.activityMap.get(player.getUniqueId());
		activity.payout(this.payoutObserver, this.vaultAdapter, player);
		this.removePlayer(player);
	}

	public void setLastPayout(long lastPayout) {
		this.lastPayout = lastPayout;
	}

	public double getMinutesToPayout() {
		return this.config.getInterval() - (double) (System.currentTimeMillis() - this.lastPayout) / 60000;
	}

	public ActivityChart getActivityChart(Player player) {
		double quota = 0;

		if (this.activityMap.containsKey(player.getUniqueId())) {
			quota = this.activityMap.get(player.getUniqueId()).getQuota();
		}

		return new ActivityChart(quota);
	}
}
