package com.henningstorck.activitycoins.activity;

import com.henningstorck.activitycoins.activities.Activity;
import com.henningstorck.activitycoins.blockhistory.BlockHistory;
import com.henningstorck.activitycoins.payout.PayoutObserver;
import com.henningstorck.activitycoins.vault.VaultAdapter;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class ActivityTest {
	private Activity activity;

	@Mock
	private BlockHistory blockHistory;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private VaultAdapter vaultAdapter;

	@Mock
	private PayoutObserver payoutObserver;

	@Mock
	private Player player;

	@BeforeEach
	public void setup() {
		this.activity = new Activity(this.blockHistory);
		this.activity.setMaxWorth(2);
		this.activity.setMinIncome(100);
		this.activity.setMaxIncome(200);
	}

	@Test
	public void testNoActivity() {
		this.activity.payout(this.payoutObserver, this.vaultAdapter, this.player);
		verify(this.payoutObserver, times(1)).notify(this.player, 100, 0);
	}

	@Test
	public void testHalfActivity() {
		this.activity.add(1);
		this.activity.payout(this.payoutObserver, this.vaultAdapter, this.player);
		verify(this.payoutObserver, times(1)).notify(this.player, 150, 0.5);
	}

	@Test
	public void testFullActivity() {
		this.activity.add(2);
		this.activity.payout(this.payoutObserver, this.vaultAdapter, this.player);
		verify(this.payoutObserver, times(1)).notify(this.player, 200, 1);
	}

	@Test
	public void testOverfullActivity() {
		this.activity.add(3);
		this.activity.payout(this.payoutObserver, this.vaultAdapter, this.player);
		verify(this.payoutObserver, times(1)).notify(this.player, 200, 1);
	}
}
