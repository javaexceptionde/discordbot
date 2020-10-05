package de.javaexceptionghg.bot;

import de.javaexceptionghg.bot.abstracts.Command;
import de.javaexceptionghg.bot.list.CommandList;

public class Register {

    /**
     * Register a command
     * @param command Instance of the {@link Command} to be registered
     */
    public static void registerCommand(Command command){
        CommandList.getInstance().put(command.getCommand(), command);
    }
}
