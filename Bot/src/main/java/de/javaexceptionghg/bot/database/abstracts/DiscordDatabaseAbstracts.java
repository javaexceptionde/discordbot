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

    public abstract boolean guildExists(Guild guild);

    /**
     * Checks if the member exists already in the Database
     * @param guild Instance of the {@link Guild} that the {@link Member} to be checked
     * @param member Instance of the {@link Member} is to be checked
     * @return Returns a value{@link Boolean} as to whether the {@link Member} exists
     */
    public abstract boolean memberExists(Guild guild, Member member);

}
