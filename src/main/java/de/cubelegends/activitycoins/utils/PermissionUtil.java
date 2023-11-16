package de.cubelegends.activitycoins.utils;

import de.cubelegends.activitycoins.Language;
import de.cubelegends.activitycoins.exceptions.NoPermissionsException;
import org.bukkit.command.CommandSender;

public final class PermissionUtil {
    private PermissionUtil() {
    }

    public static void hasPermission(Language language, CommandSender sender, String permission) throws NoPermissionsException {
        if (!sender.hasPermission(permission)) {
            throw new NoPermissionsException(language, permission);
        }
    }
}
