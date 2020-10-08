package de.javaexceptionghg.bot.interfaces;

import de.javaexceptionghg.bot.messages.CommandMessageProvider;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public interface CommandInterface {

    /**
     *
     * @param event Is the instance of the {@link MessageReceivedEvent}
     * @param args The arguments of the Command
     * @param guild The guild where the Command was executed
     * @param sender The sender of the Command
     */
    void onCommand(MessageReceivedEvent event, String[] args, Guild guild, Member sender, CommandMessageProvider messageProvider);

}
