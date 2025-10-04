package com.henningstorck.activitycoins.blockhistory;

import org.bukkit.Location;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockHistory {
	private final int maxHistorySize;
	private final Queue<Location> locationQueue;

	public BlockHistory(int maxHistorySize) {
		this.maxHistorySize = maxHistorySize;
		this.locationQueue = new LinkedBlockingQueue<>();
	}

	public boolean contains(Location location) {
		return this.locationQueue.contains(location);
	}

	public void add(Location location) {
		this.locationQueue.add(location);
		if (this.locationQueue.size() > this.maxHistorySize) {
			this.locationQueue.remove();
		}
	}
}
