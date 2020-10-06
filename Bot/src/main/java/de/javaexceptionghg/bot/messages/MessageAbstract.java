package de.javaexceptionghg.bot.messages;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public abstract class MessageAbstract {

    @Setter
    private String message;
    private String messageKey;

    public MessageAbstract(String message){
        this.message = message;
    }

    public String get(){
        return message;
    }

    private String getMessageKey(){
        return messageKey;
    }

}
