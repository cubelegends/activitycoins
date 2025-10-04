package com.henningstorck.activitycoins;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Config {
	private final JavaPlugin plugin;
	private final FileConfiguration config;

	public Config(JavaPlugin plugin) {
		this.plugin = plugin;
		this.config = plugin.getConfig();
		this.initialize();
	}

	private void initialize() {
		this.config.addDefault("interval", 15);
		this.config.addDefault("blockHistorySize", 5);
		this.config.addDefault("worth.blockBreakSurvival", 1.0);
		this.config.addDefault("worth.blockBreakCreative", 0.5);
		this.config.addDefault("worth.blockPlaceSurvival", 2.0);
		this.config.addDefault("worth.blockPlaceCreative", 1.0);
		this.config.addDefault("worth.chat", 1.0);
		this.config.addDefault("worth.command", 0.0);
		this.config.addDefault("worth.fishing", 20.0);
		this.config.addDefault("worth.kill", 4.0);
		this.config.addDefault("worth.max", 1000.0);
		this.config.addDefault("income.min", 0.0);
		this.config.addDefault("income.max", 500.0);
		this.config.addDefault("logging", true);
		this.config.addDefault("announce", true);
		this.config.addDefault("language", "en");
		this.config.addDefault("worlds", new String[]{"world", "world_nether", "world_the_end"});
		this.config.options().copyDefaults(true);
		this.plugin.saveConfig();
	}

	public int getInterval() {
		return this.config.getInt("interval");
	}

	public int getBlockHistorySize() {
		return this.config.getInt("blockHistorySize");
	}

	public double getBlockBreakSurvivalWorth() {
		return this.config.getDouble("worth.blockBreakSurvival");
	}

	public double getBlockBreakCreativeWorth() {
		return this.config.getDouble("worth.blockBreakCreative");
	}

	public double getBlockPlaceSurvivalWorth() {
		return this.config.getDouble("worth.blockPlaceSurvival");
	}

	public double getBlockPlaceCreativeWorth() {
		return this.config.getDouble("worth.blockPlaceCreative");
	}

	public double getChatWorth() {
		return this.config.getDouble("worth.chat");
	}

	public double getCommandWorth() {
		return this.config.getDouble("worth.command");
	}

	public double getFishingWorth() {
		return this.config.getDouble("worth.fishing");
	}

	public double getKillWorth() {
		return this.config.getDouble("worth.kill");
	}

	public double getMaxWorth() {
		return this.config.getDouble("worth.max");
	}

	public double getMinIncome() {
		return this.config.getDouble("income.min");
	}

	public double getMaxIncome() {
		return this.config.getDouble("income.max");
	}

	public boolean isLogging() {
		return this.config.getBoolean("logging");
	}

	public boolean isAnnounce() {
		return this.config.getBoolean("announce");
	}

	public String getLanguage() {
		return this.config.getString("language");
	}

	public List<String> getWorlds() {
		return this.config.getStringList("worlds");
	}

}
