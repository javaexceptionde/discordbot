package de.javaexceptionghg.bot.listener;

import de.javaexceptionghg.bot.Startup;
import de.javaexceptionghg.bot.abstracts.Command;
import de.javaexceptionghg.bot.list.CommandList;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class CommandExecuteListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        String command = args[0];
        if (!CommandList.getInstance().containsKey(command)){
            System.out.println("testg");
            return;
        }
        StringBuilder messageArgs = new StringBuilder();
        for (int i = 0; i < args.length; i++){
            if (i == 0)continue;
            messageArgs.append(args[i]);
        }
        args = messageArgs.toString().split(" ");
        CommandList.getInstance().get(command).onCommand(event, args, event.getGuild(), event.getMember());
    }
}
