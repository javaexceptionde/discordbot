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

package de.javaexceptionghg.bot.listener;

import de.javaexceptionghg.bot.Startup;
import de.javaexceptionghg.bot.abstracts.Command;
import de.javaexceptionghg.bot.list.CommandList;
import de.javaexceptionghg.bot.messages.CommandMessageProvider;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class CommandExecuteListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        String command = args[0];
        if (!CommandList.getInstance().containsKey(command)){
            return;
        }
        if (event.getAuthor().isBot())return;
        StringBuilder messageArgs = new StringBuilder();
        for (int i = 0; i < args.length; i++){
            if (i == 0)continue;
            messageArgs.append(args[i]);
        }
        if (messageArgs.toString().isEmpty()){
            args = new String[0];
        }else {
            args = messageArgs.toString().split(" ");
        }
        CommandList.getInstance().get(command).onCommand(event, args, event.getGuild(), event.getMember(), new CommandMessageProvider(event.getGuild(), event));
    }
}
