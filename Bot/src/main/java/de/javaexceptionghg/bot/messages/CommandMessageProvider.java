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

package de.javaexceptionghg.bot.messages;

import de.javaexceptionghg.bot.messages.enums.MessageEnum;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandMessageProvider extends MessageProvider {
    private final Guild guild;
    private final MessageReceivedEvent event;

    public CommandMessageProvider(Guild guild, MessageReceivedEvent event) {
        this.guild = guild;
        this.event = event;
    }

    public MessageAbstract get(MessageEnum messageEnum){
        return getMessage(messageEnum, guild, event);
    }
}
