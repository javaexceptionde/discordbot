package de.javaexceptionghg.bot.abstracts;

import de.javaexceptionghg.bot.interfaces.CommandInterface;
import de.javaexceptionghg.bot.list.CommandList;
import lombok.Getter;

public abstract class Command implements CommandInterface{
    @Getter
    private String command;

    public Command(String command){
        this.command = command;
        CommandList.getInstance().put(command, this);
    }


}
