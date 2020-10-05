package de.javaexceptionghg.bot;

import de.javaexceptionghg.bot.abstracts.Command;
import de.javaexceptionghg.bot.list.CommandList;

public class Register {

    public static void registerCommand(Command command){
        CommandList.getInstance().put(command.getCommand(), command);
    }
}
