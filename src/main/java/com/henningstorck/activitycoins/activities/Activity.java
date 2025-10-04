package com.henningstorck.activitycoins.activities;

import com.henningstorck.activitycoins.blockhistory.BlockHistory;
import com.henningstorck.activitycoins.payout.PayoutObserver;
import com.henningstorck.activitycoins.vault.VaultAdapter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Activity {
	private final BlockHistory blockHistory;

	private double maxWorth = 1000;
	private double minIncome = 0;
	private double maxIncome = 0;
	private double totalWorth = 0;

	public Activity(BlockHistory blockHistory) {
		this.blockHistory = blockHistory;
	}

	public void add(double worth, Location location) {
		if (this.blockHistory.contains(location)) {
			return;
		}
		this.blockHistory.add(location);
		this.add(worth);
	}

	public void add(double worth) {
		this.totalWorth = this.totalWorth + worth;
		if (this.totalWorth > this.maxWorth) {
			this.totalWorth = this.maxWorth;
		}
	}

	public void payout(PayoutObserver payoutObserver, VaultAdapter vaultAdapter, Player player) {
		double quota = this.getQuota();
		double amount = (this.maxIncome - this.minIncome) * quota + this.minIncome;
		vaultAdapter.getEconomy().depositPlayer(player, amount);
		payoutObserver.notify(player, amount, quota);
	}

	public double getQuota() {
		return this.totalWorth / this.maxWorth;
	}

	public void setMaxWorth(double maxWorth) {
		this.maxWorth = maxWorth;
	}

	public void setMinIncome(double minIncome) {
		this.minIncome = minIncome;
	}

	public void setMaxIncome(double maxIncome) {
		this.maxIncome = maxIncome;
	}
}
