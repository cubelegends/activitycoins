package com.henningstorck.activitycoins.blockhistory;

import org.bukkit.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class BlockHistoryTest {
	private BlockHistory blockHistory;

	@Mock
	private Location location1;

	@Mock
	private Location location2;

	@Mock
	private Location location3;

	@BeforeEach
	public void setup() {
		this.blockHistory = new BlockHistory(2);
	}

	@Test
	public void testMockitoEqualsTest() {
		this.location3 = this.location1;
		assertNotEquals(this.location1, this.location2);
		assertEquals(this.location1, this.location3);
	}

	@Test
	public void testEmptyHistory() {
		assertFalse(this.blockHistory.contains(this.location1));
		assertFalse(this.blockHistory.contains(this.location2));
		assertFalse(this.blockHistory.contains(this.location3));
	}

	@Test
	public void testHalfHistory() {
		this.blockHistory.add(this.location1);
		assertTrue(this.blockHistory.contains(this.location1));
		assertFalse(this.blockHistory.contains(this.location2));
		assertFalse(this.blockHistory.contains(this.location3));
	}

	@Test
	public void testFullHistory() {
		this.blockHistory.add(this.location1);
		this.blockHistory.add(this.location2);
		assertTrue(this.blockHistory.contains(this.location1));
		assertTrue(this.blockHistory.contains(this.location2));
		assertFalse(this.blockHistory.contains(this.location3));
	}

	@Test
	public void testOverfullHistory() {
		this.blockHistory.add(this.location1);
		this.blockHistory.add(this.location2);
		this.blockHistory.add(this.location3);
		assertFalse(this.blockHistory.contains(this.location1));
		assertTrue(this.blockHistory.contains(this.location2));
		assertTrue(this.blockHistory.contains(this.location3));
	}
}
