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

import de.javaexceptionghg.bot.Startup;
import de.javaexceptionghg.bot.database.DatabaseProvider;
import de.javaexceptionghg.bot.messages.enums.MessageEnum;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class MessageProviderAbstract {

    DatabaseProvider databaseUtils;

    public MessageProviderAbstract(){
        databaseUtils = Startup.getInstance().getMainDatabase();
    }

    public abstract MessageAbstract getMessage(MessageEnum messageKey, Guild guild, MessageReceivedEvent event);

    public abstract void initDefaultMessages(Guild guild);

    public abstract void setMessage(Guild guild, MessageEnum messageKey, String message);

    public abstract void checkNewMessages(Guild guild);

    public abstract boolean messagesExists(Guild guild);

}
