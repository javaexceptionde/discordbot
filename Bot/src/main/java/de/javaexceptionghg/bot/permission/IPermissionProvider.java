package de.javaexceptionghg.bot.permission;

import net.dv8tion.jda.api.entities.Guild;

public interface IPermissionProvider {

    boolean reloadPermissionProvider();

    PermissionUser getPermissionUser(String id);

    PermissionGuildUser getPermissionGuildUser(String id, String guildId);

    boolean addPermission(PermissionGuildUser permissionGuildUser, String permission);

    boolean addPermission(PermissionUser permissionGuildUser, String permission);

}
