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
