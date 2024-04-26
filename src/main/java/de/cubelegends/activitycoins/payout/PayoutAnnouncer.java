package de.cubelegends.activitycoins.payout;

import de.cubelegends.activitycoins.Language;
import de.cubelegends.activitycoins.activities.ActivityChart;
import org.bukkit.entity.Player;

public class PayoutAnnouncer implements PayoutListener {
    private final Language language;

    public PayoutAnnouncer(Language language) {
        this.language = language;
    }

    @Override
    public void notify(Player player, double payoutAmount, double quota) {
        ActivityChart activityChart = new ActivityChart(quota);
        player.sendMessage(String.format(this.language.getCurrentActivity(), activityChart.draw()));
        player.sendMessage(String.format(this.language.getPayout(), payoutAmount));
    }
}
