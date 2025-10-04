package com.henningstorck.activitycoins.exceptions;

import com.henningstorck.activitycoins.Language;
import org.bukkit.command.CommandSender;

public class NoPermissionsException extends InGameException {
	private final Language language;

	public NoPermissionsException(Language language, String message) {
		super(message);
		this.language = language;
	}

	@Override
	public void sendMessage(CommandSender sender) {
		sender.sendMessage(String.format(this.language.getNoPermissions(), this.getMessage()));
	}
}
