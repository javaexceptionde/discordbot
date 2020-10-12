package de.javaexceptionghg.bot.permission;

import de.javaexceptionghg.bot.BotProvider;
import de.javaexceptionghg.bot.cache.PermissionUserCache;

import java.util.Map;

public class PermissionProvider implements IPermissionProvider {
    @Override
    public boolean reloadPermissionProvider() {
        return false;
    }

    @Override
    public PermissionUser getPermissionUser(String id) {
        if (BotProvider.getInstance().getPermissionUserCache().containsKey(id)){
            return BotProvider.getInstance().getPermissionUserCache().get(id);
        }
        return new PermissionUser();
    }

    @Override
    public PermissionGuildUser getPermissionGuildUser(String id, String guildId) {
        return null;
    }

    @Override
    public boolean addPermission(PermissionGuildUser permissionGuildUser, String permission) {
        return false;
    }

    @Override
    public boolean addPermission(PermissionUser permissionGuildUser, String permission) {
        return false;
    }
}
