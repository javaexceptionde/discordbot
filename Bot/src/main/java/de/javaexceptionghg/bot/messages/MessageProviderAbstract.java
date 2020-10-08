package de.javaexceptionghg.bot.messages;

import de.javaexceptionghg.bot.Startup;
import de.javaexceptionghg.bot.database.abstracts.DatabaseAbstracts;
import de.javaexceptionghg.bot.messages.enums.MessageEnum;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class MessageProviderAbstract {

    DatabaseAbstracts databaseUtils;

    public MessageProviderAbstract(){
        databaseUtils = Startup.getInstance().getMainDatabase();
    }

    public abstract MessageAbstract getMessage(MessageEnum messageKey, Guild guild, MessageReceivedEvent event);

    public abstract void initDefaultMessages(Guild guild);

    public abstract void setMessage(Guild guild, MessageEnum messageKey, String message);

    public abstract void checkNewMessages(Guild guild);

    public abstract boolean messagesExists(Guild guild);

}
