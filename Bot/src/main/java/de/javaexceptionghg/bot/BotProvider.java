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

package de.javaexceptionghg.bot;

import de.javaexceptionghg.bot.cache.PermissionUserCache;
import de.javaexceptionghg.bot.database.DatabaseProvider;
import de.javaexceptionghg.bot.database.abstracts.IDatabaseProvider;
import de.javaexceptionghg.bot.messages.MessageProvider;
import de.javaexceptionghg.bot.permission.IPermissionProvider;
import de.javaexceptionghg.bot.permission.PermissionProvider;
import de.javaexceptionghg.bot.schedule.Scheduler;
import lombok.Getter;

@Getter
public class BotProvider {

    private static BotProvider instance;

    public static BotProvider getInstance(){
        return (instance != null ? instance : (instance = new BotProvider()));
    }

    public BotProvider(){
        permissionUserCache = new PermissionUserCache();
        databaseProvider = new DatabaseProvider();
        scheduler = new Scheduler();
        permissionProvider = new PermissionProvider();
        messageProvider = new MessageProvider();
    }

    private PermissionUserCache permissionUserCache;
    private IDatabaseProvider databaseProvider;
    private Scheduler scheduler;
    private IPermissionProvider permissionProvider;
    private MessageProvider messageProvider;
}
