package com.henningstorck.activitycoins.activities;

import org.bukkit.ChatColor;

public class ActivityChart {
	private final double quota;

	public ActivityChart(double quota) {
		this.quota = quota;
	}

	public String draw() {
		int totalLength = 20;
		int barLength = (int) (20 * this.quota);
		StringBuilder sb = new StringBuilder();

		sb.append("§8[");

		if (this.quota > 0.67) {
			sb.append(ChatColor.GREEN);
		} else if (this.quota < 0.33) {
			sb.append(ChatColor.RED);
		} else {
			sb.append(ChatColor.YELLOW);
		}

		sb.append(this.repeat("|", barLength));
		sb.append("§8");
		sb.append(this.repeat(".", totalLength - barLength));
		sb.append(String.format("] §7%.2f%%", this.quota * 100));

		return sb.toString();
	}

	private String repeat(String part, int times) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < times; i++) {
			sb.append(part);
		}

		return sb.toString();
	}
}
