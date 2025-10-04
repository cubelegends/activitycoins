package com.henningstorck.activitycoins.payout;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PayoutObserver {
	private final List<PayoutListener> payoutListenerList;

	public PayoutObserver() {
		this.payoutListenerList = new ArrayList<>();
	}

	public void register(PayoutListener payoutListener) {
		this.payoutListenerList.add(payoutListener);
	}

	public void notify(Player player, double payoutAmount, double quota) {
		for (PayoutListener eachPayoutListener : this.payoutListenerList) {
			eachPayoutListener.notify(player, payoutAmount, quota);
		}
	}
}
