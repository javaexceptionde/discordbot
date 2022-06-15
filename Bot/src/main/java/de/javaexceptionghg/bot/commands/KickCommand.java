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

package de.javaexceptionghg.bot.commands;

import de.javaexceptionghg.bot.Startup;
import de.javaexceptionghg.bot.abstracts.Command;
import de.javaexceptionghg.bot.messages.CommandMessageProvider;
import de.javaexceptionghg.bot.messages.enums.MessageEnum;
import de.javaexceptionghg.bot.messages.MessageProvider;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.HierarchyException;

public class KickCommand extends Command {
    MessageProvider messageProvider;

    public KickCommand() {
        super("!kick");
        messageProvider = Startup.getInstance().getMessageProvider();
    }

    @Override
    public void onCommand(MessageReceivedEvent event, String[] args, Guild guild, Member sender, CommandMessageProvider messageProvider) {
        Message message = event.getMessage();
        if (args.length == 0){
            message.getChannel().sendMessage(new MessageBuilder(messageProvider.get(MessageEnum.KICK_USAGE).get()).build()).submit();
        }
        if (args.length == 1) {
            if (message.getMentionedMembers().isEmpty()) {
                message.getChannel().sendMessage(new MessageBuilder(messageProvider.get(MessageEnum.KICK_USAGE).get()).build()).submit();
                return;
            }
            if (message.getMentionedMembers().size() > 1) {
                message.getChannel().sendMessage(messageProvider.get(MessageEnum.KICK_NOT_MORE).get()).submit();
                return;
            }
            message.getMentionedMembers().forEach(member -> {
                try {
                    member.kick().submit();
                }catch (HierarchyException exception){
                    message.getChannel().sendMessage(messageProvider.get(MessageEnum.CANNOT_MODIFY_MEMBER).get()).submit();
                    return;
                }
                message.getChannel().sendMessage(messageProvider.get(MessageEnum.KICK_MEMBER).get()).submit();
            });
        }
        if (args.length >= 2){
            if (message.getMentionedMembers().isEmpty()) {
                message.getChannel().sendMessage(new MessageBuilder(messageProvider.get(MessageEnum.KICK_USAGE).get()).build()).submit();
                return;
            }
            if (message.getMentionedMembers().size() > 1) {
                message.getChannel().sendMessage(messageProvider.get(MessageEnum.KICK_NOT_MORE).get()).submit();
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < args.length; i++){
                stringBuilder.append(args[i]);
            }
            message.getMentionedMembers().forEach(member -> {
                member.getUser().openPrivateChannel().queue(privateChannel -> {
                    privateChannel.sendMessage(messageProvider.get(MessageEnum.KICK_MEMBER_REASON).get()).submit();
                });
                message.getChannel().sendMessage(messageProvider.get(MessageEnum.KICK_MEMBER).get()).submit();
                member.kick().submit();
            });
        }
    }
}
