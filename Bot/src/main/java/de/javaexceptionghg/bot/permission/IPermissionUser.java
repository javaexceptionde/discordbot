package de.javaexceptionghg.bot.permission;

public interface IPermissionUser {

    void addPermission(String permission);

    void removePermission(String permission);

    void addGroup(IPermissionGroup permissionGroup);
}
