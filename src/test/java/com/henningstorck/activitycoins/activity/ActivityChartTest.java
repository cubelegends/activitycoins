package com.henningstorck.activitycoins.activity;

import com.henningstorck.activitycoins.activities.ActivityChart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ActivityChartTest {
	@BeforeEach
	public void setup() {
		Locale.setDefault(Locale.GERMANY);
	}

	@Test
	public void testDraw0() {
		ActivityChart activityChart = new ActivityChart(0.0);
		assertEquals("§8[§c§8....................] §70,00%", activityChart.draw());
	}

	@Test
	public void testDraw50() {
		ActivityChart activityChart = new ActivityChart(0.5);
		assertEquals("§8[§e||||||||||§8..........] §750,00%", activityChart.draw());
	}

	@Test
	public void testDraw100() {
		ActivityChart activityChart = new ActivityChart(1);
		assertEquals("§8[§a||||||||||||||||||||§8] §7100,00%", activityChart.draw());
	}
}
