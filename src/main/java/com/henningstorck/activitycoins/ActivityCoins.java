package com.henningstorck.activitycoins;

import com.henningstorck.activitycoins.activities.ActivityCommand;
import com.henningstorck.activitycoins.activities.ActivityMapper;
import com.henningstorck.activitycoins.listeners.*;
import com.henningstorck.activitycoins.payout.PayoutAnnouncer;
import com.henningstorck.activitycoins.payout.PayoutLogger;
import com.henningstorck.activitycoins.payout.PayoutObserver;
import com.henningstorck.activitycoins.payout.PayoutTask;
import com.henningstorck.activitycoins.vault.VaultAdapter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class ActivityCoins extends JavaPlugin {
	private final Logger logger = Bukkit.getLogger();

	private ActivityMapper activityMapper;
	private Config config;
	private Language language;
	private VaultAdapter vaultAdapter;

	@Override
	public void onEnable() {
		this.config = new Config(this);
		this.language = new Language(this);
		this.vaultAdapter = new VaultAdapter(this);

		this.initializeActivityMapper();
		this.initializeListeners();
		this.initializeTasks();
		this.initializeCommands();
	}

	private void initializeActivityMapper() {
		PayoutObserver payoutObserver = new PayoutObserver();

		if (this.config.isAnnounce()) {
			payoutObserver.register(new PayoutAnnouncer(this.language));
		}
		if (this.config.isLogging()) {
			payoutObserver.register(new PayoutLogger());
		}

		this.activityMapper = new ActivityMapper(this.vaultAdapter, this.config, payoutObserver);
	}

	private void initializeListeners() {
		this.getServer().getPluginManager().registerEvents(new PlayerJoinQuitListener(this.activityMapper), this);

		double blockBreakSurvivalWorth = this.config.getBlockBreakSurvivalWorth();
		double blockBreakCreativeWorth = this.config.getBlockBreakCreativeWorth();
		double blockPlaceSurvivalWorth = this.config.getBlockPlaceSurvivalWorth();
		double blockPlaceCreativeWorth = this.config.getBlockPlaceCreativeWorth();
		double chatWorth = this.config.getChatWorth();
		double cmdWorth = this.config.getCommandWorth();
		double fishingWorth = this.config.getFishingWorth();
		double killWorth = this.config.getKillWorth();

		boolean blockBreakingEnabled = blockBreakSurvivalWorth > 0.0 || blockBreakCreativeWorth > 0.0;
		boolean blockPlacingEnabled = blockPlaceSurvivalWorth > 0.0 || blockPlaceCreativeWorth > 0.0;
		boolean chatEnabled = chatWorth > 0.0;
		boolean cmdEnabled = cmdWorth > 0.0;
		boolean fishingEnabled = fishingWorth > 0.0;
		boolean killEnabled = killWorth > 0.0;

		if (blockBreakingEnabled) {
			this.getServer().getPluginManager()
				.registerEvents(new BlockBreakActivityListener(this.config, this.activityMapper), this);
		}

		if (blockPlacingEnabled) {
			this.getServer().getPluginManager()
				.registerEvents(new BlockPlaceActivityListener(this.config, this.activityMapper), this);
		}

		if (chatEnabled) {
			this.getServer().getPluginManager()
				.registerEvents(new ChatActivityListener(this.config, this.activityMapper), this);
		}

		if (cmdEnabled) {
			this.getServer().getPluginManager()
				.registerEvents(new CmdActivityListener(this.config, this.activityMapper), this);
		}

		if (fishingEnabled) {
			this.getServer().getPluginManager()
				.registerEvents(new FishingActivityListener(this.config, this.activityMapper), this);
		}

		if (killEnabled) {
			this.getServer().getPluginManager()
				.registerEvents(new KillActivityListener(this.config, this.activityMapper), this);
		}

		this.logger.info("Block breaking activity: " + blockBreakingEnabled);
		this.logger.info("Block placing activity:  " + blockPlacingEnabled);
		this.logger.info("Chat activity:           " + chatEnabled);
		this.logger.info("Command activity:        " + cmdEnabled);
		this.logger.info("Fishing activity:        " + fishingEnabled);
		this.logger.info("Kill activity:           " + killEnabled);
	}

	private void initializeTasks() {
		int interval = this.config.getInterval() * 20 * 60;
		new PayoutTask(this.activityMapper).runTaskTimerAsynchronously(this, interval, interval);
	}

	private void initializeCommands() {
		this.getCommand("activity").setExecutor(new ActivityCommand(this.language, this.activityMapper));
	}
}
