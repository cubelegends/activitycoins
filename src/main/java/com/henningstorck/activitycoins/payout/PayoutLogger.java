package com.henningstorck.activitycoins.payout;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class PayoutLogger implements PayoutListener {
	private final Logger logger = Bukkit.getLogger();

	@Override
	public void notify(Player player, double payoutAmount, double quota) {
		this.logger.info("Activity for " + player.getName() + ": " + quota * 100 + "%");
	}
}
