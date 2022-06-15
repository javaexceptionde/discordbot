/*
 *
 *  *     Copyright (C) 2022  Jonathan Benedikt Bull<jonathan@jbull.dev>
 *  *
 *  *     This program is free software: you can redistribute it and/or modify
 *  *     it under the terms of the GNU General Public License as published by
 *  *     the Free Software Foundation, either version 3 of the License, or
 *  *     (at your option) any later version.
 *  *
 *  *     This program is distributed in the hope that it will be useful,
 *  *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *     GNU General Public License for more details.
 *  *
 *  *     You should have received a copy of the GNU General Public License
 *  *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

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
