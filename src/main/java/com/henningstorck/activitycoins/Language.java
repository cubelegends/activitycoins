package com.henningstorck.activitycoins;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Language {
	private final YamlConfiguration lang;
	private final File dataFolder;

	public Language(JavaPlugin plugin) {
		this.dataFolder = plugin.getDataFolder();
		FileConfiguration config = plugin.getConfig();
		this.initializeEn();
		this.initializeDe();
		File langFile = new File(String.format("%s/lang/%s.yml", this.dataFolder, config.getString("language")));
		this.lang = YamlConfiguration.loadConfiguration(langFile);
	}

	private void initializeEn() {
		try {
			this.tryInitializeEn();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void tryInitializeEn() throws IOException {
		File file = new File(String.format("%s/lang/en.yml", this.dataFolder));
		YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

		yamlConfiguration.addDefault("prefix", "§8[§6ActivityCoins§8] §7");
		yamlConfiguration.addDefault("noPermissions", "§4You don't have enough permissions to do this: %s");
		yamlConfiguration.addDefault("currentActivity", "Activity: %s");
		yamlConfiguration.addDefault("currentActivityFromPlayer", "%s §8- §7%s");
		yamlConfiguration.addDefault("remainingTime", "Payout in: %.2f minutes");
		yamlConfiguration.addDefault("payout", "Payout: %.2f");
		yamlConfiguration.options().copyDefaults(true);
		yamlConfiguration.save(file);
	}

	private void initializeDe() {
		try {
			this.tryInitializeDe();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void tryInitializeDe() throws IOException {
		File file = new File(String.format("%s/lang/de.yml", this.dataFolder));
		YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);

		yamlConfiguration.addDefault("prefix", "§8[§6ActivityCoins§8] §7");
		yamlConfiguration.addDefault("noPermissions", "§4Dir fehlen die Rechte, um dies zu tun: %s");
		yamlConfiguration.addDefault("currentActivity", "Aktivität: %s");
		yamlConfiguration.addDefault("currentActivityFromPlayer", "%s §8- §7%s");
		yamlConfiguration.addDefault("remainingTime", "Auszahlung in: %.2f Minuten");
		yamlConfiguration.addDefault("payout", "Auszahlung: %.2f");
		yamlConfiguration.options().copyDefaults(true);
		yamlConfiguration.save(file);
	}

	public String getNoPermissions() {
		return this.lang.getString("prefix") + this.lang.getString("noPermissions");
	}

	public String getCurrentActivity() {
		return this.lang.getString("prefix") + this.lang.getString("currentActivity");
	}

	public String getCurrentActivityFromPlayer() {
		return this.lang.getString("prefix") + this.lang.getString("currentActivityFromPlayer");
	}

	public String getRemainingTime() {
		return this.lang.getString("prefix") + this.lang.getString("remainingTime");
	}

	public String getPayout() {
		return this.lang.getString("prefix") + this.lang.getString("payout");
	}

}
