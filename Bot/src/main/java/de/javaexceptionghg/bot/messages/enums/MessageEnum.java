/*
 *
 *  *     Copyright (C) 2022  Jonathan Benedikt Bull<jonathan@jbull.dev>
 *  *
 *  *     This program is free software: you can redistribute it and/or modify
 *  *     it under the terms of the GNU General Public License as published by
 *  *     the Free Software Foundation, either version 3 of the License, or
 *  *     (at your option) any later version.
 *  *
 *  *     This program is distributed in the hope that it will be useful,
 *  *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *     GNU General Public License for more details.
 *  *
 *  *     You should have received a copy of the GNU General Public License
 *  *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package de.javaexceptionghg.bot.messages.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MessageEnum {
    KICK_USAGE("KICK_USAGE", "!kick <@user> [<Grund>]"),
    KICK_NOT_MORE("KICK_MORE_THEN_ONE", "Du darfst nicht mehr als einen User markieren"),
    KICK_MEMBER("KICK_MEMBER", "%mention_1% wurde gekickt"),
    KICK_MEMBER_REASON("KICK_MEMBER_KICKED_REASON", "Du wurdest gekickt\nGrund: %args3%"),
    CANNOT_MODIFY_MEMBER("CANNOT_MODIFY_MEMBER", "Du darfst keine Aktion mit diesem Mitglied ausf\u00fchren");
    private String messageKey;
    @Getter
    private String defaultMessage;

    @Override
    public String toString() {
        return messageKey;
    }

}
