package de.javaexceptionghg.bot.messages.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MessageEnum {
    KICK_USAGE("KICK_USAGE", "!kick <@user> [<Grund>]"),
    KICK_NOT_MORE("KICK_MORE_THEN_ONE", "Du darfst nicht mehr als einen User markieren"),
    KICK_MEMBER("KICK_MEMBER", "%mention% wurde gekickt"),
    KICK_MEMBER_REASON("KICK_MEMBER_KICKED_REASON", "Du wurdest gekickt\nGrund: %reason%"),
    CANNOT_MODIFY_MEMBER("CANNOT_MODIFY_MEMBER", "Du darfst keine Aktion mit diesem Mitglied ausführen");
    private String messageKey;
    @Getter
    private String defaultMessage;

    @Override
    public String toString() {
        return messageKey;
    }

}
