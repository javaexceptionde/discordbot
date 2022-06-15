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

package de.javaexceptionghg.bot.database.abstracts;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

public abstract class DiscordDatabaseAbstracts {

    public abstract  void create(Guild guild);

    public abstract void createMember(Guild guild, Member member);

    public abstract  void createUser(User user);

    public abstract void ban(Member member, Guild guild, Member initiator);

    public abstract void ban(Member member, Member initiator, Guild guild, String reason);

    public abstract void ban(String id, String initiator, Guild guild, String reason);

    public abstract void ban(String id, String initiator, String guildId, String reason);

    public abstract void ban(String id, String initiator, Guild guild);

    public abstract void ban(String id, String initiator, String guildId);

    public abstract void ban(String id, Member initiator, String guildId);

    public abstract void ban(Member member, Guild guild);

    public abstract void ban(Member member, Guild guild, String reason);

    public abstract void ban(String id, Guild guild, String reason);

    public abstract void ban(String id, Guild guild);

    public abstract void ban(String id, String guildId);

    public abstract void kick(Member member);

    public abstract void kick(Member member, String reason);

    public abstract void kick(Member member, Member initiator);

    public abstract void kick(Member member, Member initiator, String reason);

    public abstract String getNickname(Guild guild);

    public abstract boolean guildExists(Guild guild);

    /**
     * Checks if the member exists already in the Database
     * @param guild Instance of the {@link Guild} that the {@link Member} to be checked
     * @param member Instance of the {@link Member} is to be checked
     * @return Returns a value{@link Boolean} as to whether the {@link Member} exists
     */
    public abstract boolean memberExists(Guild guild, Member member);

    public abstract void setBotNickname(String nickname, Guild guild);
}
