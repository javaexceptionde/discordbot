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

package de.javaexceptionghg.bot.listener;

import de.javaexceptionghg.bot.Startup;
import de.javaexceptionghg.bot.database.DiscordDatabaseUtils;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GuildMemberJoinListener extends ListenerAdapter {
    private final DiscordDatabaseUtils databaseUtils;

    public GuildMemberJoinListener(){
        databaseUtils = Startup.getInstance().getDatabaseUtils();
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        if (!databaseUtils.memberExists(event.getGuild(), event.getMember()))databaseUtils.createMember(event.getGuild(), event.getMember());
    }
}
