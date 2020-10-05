package de.javaexceptionghg.bot.listener;

import de.javaexceptionghg.bot.Startup;
import de.javaexceptionghg.bot.database.DiscordDatabaseUtils;
import de.javaexceptionghg.bot.database.MainDatabase;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GuildJoinListener extends ListenerAdapter {
    private final DiscordDatabaseUtils databaseUtils;

    public GuildJoinListener(){
        databaseUtils = Startup.getInstance().getDatabaseUtils();
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        if (!databaseUtils.guildExists(event.getGuild())){
            databaseUtils.create(event.getGuild());
        }
    }
}
