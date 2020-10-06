package de.javaexceptionghg.bot.messages;

import de.javaexceptionghg.bot.messages.enums.MessageEnum;
import javafx.scene.chart.ValueAxis;
import net.dv8tion.jda.api.entities.Guild;
import org.bson.Document;

public class MessageProvider extends MessageProviderAbstract {
    

    @Override
    public MessageAbstract getMessage(MessageEnum messageKey, Guild guild) {
        return new Message(databaseUtils.getDocument("Messages", "GuildId", guild.getId()).getString(messageKey.toString()));
    }

    @Override
    public void initDefaultMessages(Guild guild) {
        Document document = new Document();
        document.append("GuildId", guild.getId());
        document.append(MessageEnum.KICK_USAGE.toString(), "!kick <@user> [<Grund>]");
        document.append(MessageEnum.KICK_NOT_MORE.toString(), "Du darfst nicht mehr als einen User markieren");
        document.append(MessageEnum.KICK_MEMBER.toString(), "%mention% wurde gekickt");
        document.append(MessageEnum.KICK_MEMBER_REASON.toString(), "Du wurdest gekickt\nGrund: %reason%");
        document.append(MessageEnum.CANNOT_MODIFY_MEMBER.toString(), "Du darfst keine Aktion mit diesem Mitglied ausführen");
        databaseUtils.insert("Messages", document);
    }

    @Override
    public void setMessage(Guild guild, MessageEnum messageKey, String message){

    }

    @Override
    public void checkNewMessages(Guild guild) {
        Document document = databaseUtils.getDocument("Messages", "GuildId", guild.getId());
        for (MessageEnum value : MessageEnum.values()) {
            if (document.containsKey(value.toString()))return;
            document.append(value.toString(), value.getDefaultMessage());
        }
        databaseUtils.update(databaseUtils.getDocument("Messages", "GuildId", guild.getId()), document, "Messages");
    }
}
