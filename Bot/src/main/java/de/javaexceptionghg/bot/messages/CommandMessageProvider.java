package de.javaexceptionghg.bot.messages;

import de.javaexceptionghg.bot.messages.enums.MessageEnum;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandMessageProvider extends MessageProvider {
    private final Guild guild;
    private final MessageReceivedEvent event;

    public CommandMessageProvider(Guild guild, MessageReceivedEvent event) {
        this.guild = guild;
        this.event = event;
    }

    public MessageAbstract get(MessageEnum messageEnum){
        return getMessage(messageEnum, guild, event);
    }
}
