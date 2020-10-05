package de.javaexceptionghg.bot.commands;

import de.javaexceptionghg.bot.abstracts.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("!help");
    }

    @Override
    public void onCommand(MessageReceivedEvent event, String[] args, Guild guild, Member sender) {
        event.getMessage().getChannel().sendMessage("Help ").submit();

    }
}
