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

import de.javaexceptionghg.bot.commands.HelpCommand;
import de.javaexceptionghg.bot.commands.KickCommand;
import de.javaexceptionghg.bot.database.DiscordDatabaseUtils;
import de.javaexceptionghg.bot.database.MainDatabase;
import de.javaexceptionghg.bot.listener.CommandExecuteListener;
import de.javaexceptionghg.bot.listener.GuildJoinListener;
import de.javaexceptionghg.bot.listener.GuildMemberJoinListener;
import de.javaexceptionghg.bot.messages.MessageProvider;
import de.javaexceptionghg.bot.schedule.Scheduler;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;


@Getter
public class Startup {

    private MainDatabase mainDatabase;
    private DiscordDatabaseUtils databaseUtils;
    private Logger logger;
    private MessageProvider messageProvider;
    private JDA jda;
    @Getter
    private static Startup instance;

    public static void main(String[] args) {
        Startup startup = new Startup();
        instance = startup;
        Scheduler scheduler = new Scheduler();

        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " Schedule");
            }
        });
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " Schedule1");
            }
        });
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " Schedule2");
            }
        });
        startup.init();
        JDABuilder jdaBuilder = new JDABuilder("NjY1ODk1NzIxODkwNzQyMjgy.XhsRtw.bcBX6F7oYevcnOE19xZMCVXeutw");
        jdaBuilder.setStatus(OnlineStatus.ONLINE);
        jdaBuilder.setActivity(Activity.listening("!help"));
        jdaBuilder.addEventListeners(new CommandExecuteListener());
        jdaBuilder.addEventListeners(new GuildJoinListener());
        jdaBuilder.addEventListeners(new GuildMemberJoinListener());
        try {
            getInstance().setJda(jdaBuilder.build());
            getInstance().getLogger().info("Startup successful");
        } catch (LoginException e) {
            getInstance().getLogger().error("The Bot failed on Startup" + e.getMessage());
        }
        Register.registerCommand(new HelpCommand());
        Register.registerCommand(new KickCommand());
        getInstance().getLogger().info("Registered Commands");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                getInstance().afterStart();
                this.cancel();
            }
        }, 5000);
    }

    public void init(){
        logger = JDALogger.getLog(Startup.class);;
        mainDatabase = new MainDatabase();
        mainDatabase.connect();
        logger.info("Connected to MongoDatabase");
        //mainDatabase.getDatabase().createCollection("Guilds");
        //mainDatabase.getDatabase().createCollection("Members");
        databaseUtils = new DiscordDatabaseUtils();
        messageProvider = new MessageProvider();
        logger.debug("Initialized MessageProvider");
    }

    public void afterStart(){
        logger.info("Test logger");
        if (jda.getGuilds().isEmpty())logger.debug("No Guilds found");
        logger.info("Started Database Compatibility Check");
        jda.getGuilds().forEach(guild -> {
            if (!databaseUtils.guildExists(guild)){
                databaseUtils.create(guild);
                messageProvider.initDefaultMessages(guild);
                return;
            }
            if (!messageProvider.messagesExists(guild))messageProvider.initDefaultMessages(guild);
            messageProvider.checkNewMessages(guild);
            guild.getMember(jda.getUserById("665895721890742282")).modifyNickname(databaseUtils.getNickname(guild)).complete();
        });
        logger.info("Finished Database Compatibility Check");
    }

    public void setJda(JDA jda) {
        this.jda = jda;
    }
}
