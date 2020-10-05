package de.javaexceptionghg.bot.interfaces;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public interface CommandInterface {

    void onCommand(MessageReceivedEvent event, String[] args, Guild guild, Member sender);

}
