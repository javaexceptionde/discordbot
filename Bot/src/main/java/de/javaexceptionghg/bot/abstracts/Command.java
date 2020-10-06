package de.javaexceptionghg.bot.abstracts;

import de.javaexceptionghg.bot.interfaces.CommandInterface;
import de.javaexceptionghg.bot.list.CommandList;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;

public abstract class Command implements CommandInterface{
    @Getter
    private String command;

    public Command(String command){
        this.command = command;
    }
}
