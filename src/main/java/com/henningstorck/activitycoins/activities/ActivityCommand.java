package com.henningstorck.activitycoins.activities;

import com.henningstorck.activitycoins.Language;
import com.henningstorck.activitycoins.Permissions;
import com.henningstorck.activitycoins.exceptions.InGameException;
import com.henningstorck.activitycoins.exceptions.NoPermissionsException;
import com.henningstorck.activitycoins.utils.PermissionUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ActivityCommand implements CommandExecutor {
	private final Language language;
	private final ActivityMapper activityMapper;

	public ActivityCommand(Language language, ActivityMapper activityMapper) {
		this.language = language;
		this.activityMapper = activityMapper;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("activity")) {

			if (sender instanceof Player && args.length == 0) {
				Player player = (Player) sender;
				this.ownActivity(player);
				return true;
			}

			if (args.length == 1 && args[0].equalsIgnoreCase("all")) {
				this.allActivities(sender);
				return true;
			}

		}
		return false;
	}

	private void ownActivity(Player player) {
		try {
			this.tryOwnActivity(player);
		} catch (InGameException e) {
			e.sendMessage(player);
		}
	}

	private void tryOwnActivity(Player player) throws NoPermissionsException {
		PermissionUtil.hasPermission(language, player, Permissions.OWN_ACTIVITY);
		ActivityChart activityChart = this.activityMapper.getActivityChart(player);
		player.sendMessage(String.format(
			this.language.getCurrentActivity(),
			activityChart.draw()
		));
		player.sendMessage(String.format(
			this.language.getRemainingTime(),
			this.activityMapper.getMinutesToPayout()
		));
	}

	private void allActivities(CommandSender sender) {
		try {
			this.tryAllActivities(sender);
		} catch (InGameException e) {
			e.sendMessage(sender);
		}
	}

	private void tryAllActivities(CommandSender sender) throws InGameException {
		PermissionUtil.hasPermission(language, sender, Permissions.ALL_ACTIVITIES);
		for (Player eachPlayer : sender.getServer().getOnlinePlayers()) {
			ActivityChart activityChart = this.activityMapper.getActivityChart(eachPlayer);
			sender.sendMessage(String.format(
				this.language.getCurrentActivityFromPlayer(),
				activityChart.draw(),
				eachPlayer.getName()
			));
		}
	}

}
