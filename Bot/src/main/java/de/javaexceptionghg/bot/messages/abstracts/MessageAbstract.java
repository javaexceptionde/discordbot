package de.javaexceptionghg.bot.messages.abstracts;

import net.dv8tion.jda.api.entities.Guild;

public abstract class MessageAbstract {

    public abstract String getMessage(String messageKey,Guild guild);

    public abstract void initDefaultMessages(Guild guild);

    public abstract void setMessage(Guild guild, String messageKey, String message);

}
