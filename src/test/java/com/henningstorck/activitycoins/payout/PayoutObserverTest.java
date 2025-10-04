package com.henningstorck.activitycoins.payout;

import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PayoutObserverTest {
	private PayoutObserver payoutObserver;

	@Mock
	private PayoutListener payoutListener1;

	@Mock
	private PayoutListener payoutListener2;

	@Mock
	private Player player;

	@BeforeEach
	public void setup() {
		this.payoutObserver = new PayoutObserver();
	}

	@Test
	public void testWithoutListeners() {
		this.payoutObserver.notify(this.player, 200, 1);
		verify(this.payoutListener1, times(0)).notify(any(Player.class), anyDouble(), anyDouble());
		verify(this.payoutListener2, times(0)).notify(any(Player.class), anyDouble(), anyDouble());
	}

	@Test
	public void testWithOneListener() {
		this.payoutObserver.register(this.payoutListener1);
		this.payoutObserver.notify(this.player, 200, 1);
		verify(this.payoutListener1, times(1)).notify(this.player, 200, 1);
		verify(this.payoutListener2, times(0)).notify(any(Player.class), anyDouble(), anyDouble());
	}

	@Test
	public void testWithTwoListeners() {
		this.payoutObserver.register(this.payoutListener1);
		this.payoutObserver.register(this.payoutListener2);
		this.payoutObserver.notify(this.player, 200, 1);
		verify(this.payoutListener1, times(1)).notify(this.player, 200, 1);
		verify(this.payoutListener2, times(1)).notify(this.player, 200, 1);
	}
}
