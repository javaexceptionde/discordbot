package de.javaexceptionghg.bot.messages;

import de.javaexceptionghg.bot.messages.enums.MessageEnum;
import javafx.scene.chart.ValueAxis;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.bson.Document;

import javax.print.Doc;

public class MessageProvider extends MessageProviderAbstract {
    

    @Override
    public MessageAbstract getMessage(MessageEnum messageKey, Guild guild, MessageReceivedEvent event) {
         databaseUtils.getDocument("GuildId", guild.getId(), "Messages", document -> {
             Message message= new Message(document.getString(messageKey.toString()));
        });
        String messageString = message.get();
        String[] messageArgs = messageString.split(" ");
        for (String s : messageArgs){
            if (s.startsWith("%argjghj") && s.endsWith("%")){
                s = s.substring(4, s.length() - 1);
                try {
                    int i = Integer.parseInt(s);
                    messageString = messageString.replace("%arg" + i + "%", event.getMessage().getContentRaw().split(" ")[i - 1]);
                }catch (NumberFormatException exception){

                }
            }
            if (s.startsWith("%args") && s.endsWith("%")) {
                if (s.contains("_")) {
                    s = s.substring(5, s.length() - 1);
                    String[] index = s.split("_");
                    if (index.length != 2) continue;
                    int i;
                    int ii;
                    try {
                        i = Integer.parseInt(index[0]);
                        ii = Integer.parseInt(index[0]);
                    } catch (NumberFormatException exception) {
                        continue;
                    }
                    String commandargs[] = event.getMessage().getContentRaw().split(" ");
                    i--;
                    ii--;
                    StringBuilder stringBuilder = new StringBuilder();
                    for (; i < ii; i++) {
                        stringBuilder.append(commandargs[i]);
                        stringBuilder.append(" ");
                    }
                    stringBuilder.trimToSize();
                    i = Integer.parseInt(index[0]);
                    messageString = messageString.replace("%args" + i + "_" + ii + "%", stringBuilder.toString());
                } else {
                    s = s.substring(5, s.length() - 1);
                    int i;
                    try {
                        i = Integer.parseInt(s);
                    } catch (NumberFormatException exception) {
                        continue;
                    }
                    String commandargs[] = event.getMessage().getContentRaw().split(" ");
                    i--;
                    StringBuilder stringBuilder = new StringBuilder();
                    for (; i < commandargs.length; i++) {
                        stringBuilder.append(commandargs[i]);
                        stringBuilder.append(" ");
                    }
                    stringBuilder.trimToSize();
                    i = Integer.parseInt(s);
                    messageString = messageString.replace("%args" + i + "%", stringBuilder.toString());
                }
            }
            if (s.contains("%sender%")){
                messageString = messageString.replace("%sender%", event.getMessage().getAuthor().getAsTag());
            }
            if (s.contains("%mention_1%")){
                for (Member mentionedMember : event.getMessage().getMentionedMembers()) {
                    messageString = messageString.replace("%mention_1%" ,mentionedMember.getAsMention());
                    break;
                }
            }
        }
        message.setMessage(messageString);
        return message;
    }

    @Override
    public void initDefaultMessages(Guild guild) {
        Document document = new Document();
        document.append("GuildId", guild.getId());
        document.append(MessageEnum.KICK_USAGE.toString(), MessageEnum.KICK_USAGE.getDefaultMessage());
        document.append(MessageEnum.KICK_NOT_MORE.toString(), MessageEnum.KICK_NOT_MORE.getDefaultMessage());
        document.append(MessageEnum.KICK_MEMBER.toString(), MessageEnum.KICK_MEMBER.getDefaultMessage());
        document.append(MessageEnum.KICK_MEMBER_REASON.toString(), MessageEnum.KICK_MEMBER_REASON.getDefaultMessage());
        document.append(MessageEnum.CANNOT_MODIFY_MEMBER.toString(), MessageEnum.CANNOT_MODIFY_MEMBER.getDefaultMessage());
        databaseUtils.insert(document, "Messages");
    }

    @Override
    public void setMessage(Guild guild, MessageEnum messageKey, String message){

    }

    @Override
    public void checkNewMessages(Guild guild) {
        Document check = databaseUtils.getDocument("Messages", "GuildId", guild.getId());
        Document document = databaseUtils.getDocument("Messages", "GuildId", guild.getId());
        for (MessageEnum value : MessageEnum.values()) {
            if (check.containsKey(value.toString()))continue;
            document.append(value.toString(), value.getDefaultMessage());
        }
        databaseUtils.replace(check, document, "Messages");
    }

    @Override
    public boolean messagesExists(Guild guild) {
        return databaseUtils.contains("GuildId", guild.getId(), "Messages");
    }
}
