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
