package de.cubelegends.activitycoins.payout;

import org.bukkit.entity.Player;

public interface PayoutListener {
    void notify(Player player, double payoutAmount, double quota);
}
