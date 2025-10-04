package com.henningstorck.activitycoins.vault;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class VaultAdapter {
	public static final String VAULT_PLUGIN_NAME = "Vault";

	private final JavaPlugin plugin;

	private Economy economy;

	public VaultAdapter(JavaPlugin plugin) {
		this.plugin = plugin;

		if (!this.setupEconomy()) {
			plugin.getLogger().info("Cannot find Vault installation.");
			plugin.getServer().getPluginManager().disablePlugin(plugin);
		}
	}

	private boolean setupEconomy() {
		if (this.plugin.getServer().getPluginManager().getPlugin(VAULT_PLUGIN_NAME) == null) {
			return false;
		}

		RegisteredServiceProvider<Economy> rsp =
			this.plugin.getServer().getServicesManager().getRegistration(Economy.class);

		if (rsp == null) {
			return false;
		}

		this.economy = rsp.getProvider();
		return true;
	}

	public Economy getEconomy() {
		return this.economy;
	}
}
