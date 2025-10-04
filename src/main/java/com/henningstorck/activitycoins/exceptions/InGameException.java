package com.henningstorck.activitycoins.exceptions;

import org.bukkit.command.CommandSender;

public abstract class InGameException extends Exception {
	public InGameException(String message) {
		super(message);
	}

	public abstract void sendMessage(CommandSender sender);
}
