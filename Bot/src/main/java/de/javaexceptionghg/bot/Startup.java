package de.javaexceptionghg.bot;

import de.javaexceptionghg.bot.commands.HelpCommand;
import de.javaexceptionghg.bot.commands.KickCommand;
import de.javaexceptionghg.bot.database.DiscordDatabaseUtils;
import de.javaexceptionghg.bot.database.MainDatabase;
import de.javaexceptionghg.bot.listener.CommandExecuteListener;
import de.javaexceptionghg.bot.listener.GuildJoinListener;
import de.javaexceptionghg.bot.listener.GuildMemberJoinListener;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;


@Getter
public class Startup {

    private MainDatabase mainDatabase;
    private DiscordDatabaseUtils databaseUtils;
    private Logger logger;
    @Getter
    private static Startup instance;

    public static void main(String[] args) {
        Startup startup = new Startup();
        instance = startup;
        startup.init();
        JDABuilder jdaBuilder = new JDABuilder("NjY1ODk1NzIxODkwNzQyMjgy.XhsRtw.bcBX6F7oYevcnOE19xZMCVXeutw");
        jdaBuilder.setStatus(OnlineStatus.IDLE);
        jdaBuilder.setActivity(Activity.listening("Startup Script"));
        jdaBuilder.addEventListeners(new CommandExecuteListener());
        jdaBuilder.addEventListeners(new GuildJoinListener());
        jdaBuilder.addEventListeners(new GuildMemberJoinListener());
        try {
            jdaBuilder.build();
            getInstance().getLogger().info("Startup successful");
        } catch (LoginException e) {
            getInstance().getLogger().error("The Bot failed on Startup" + e.getMessage());
        }
        Register.registerCommand(new HelpCommand());
        Register.registerCommand(new KickCommand());
    }

    public void init(){
        logger = LoggerFactory.getLogger(Startup.class.getSimpleName());
        mainDatabase = new MainDatabase();
        mainDatabase.connect();
        //mainDatabase.getDatabase().createCollection("Guilds");
        //mainDatabase.getDatabase().createCollection("Members");
        databaseUtils = new DiscordDatabaseUtils();
    }


}
