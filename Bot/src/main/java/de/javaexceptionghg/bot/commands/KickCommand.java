package de.javaexceptionghg.bot.commands;

import de.javaexceptionghg.bot.abstracts.Command;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public class KickCommand extends Command {
    public KickCommand() {
        super("!kick");
    }

    @Override
    public void onCommand(MessageReceivedEvent event, String[] args, Guild guild, Member sender) {
        Message message = event.getMessage();
        if (args.length == 0){
            message.getChannel().sendMessage(new MessageBuilder("!kick <@user> [<Grund>]").build()).submit();
        }
        if (args.length == 1) {
            if (message.getMentionedMembers().isEmpty()) {
                message.getChannel().sendMessage(new MessageBuilder("!kick <@user> [<Grund>]").build()).submit();
                return;
            }
            if (message.getMentionedMembers().size() > 1) {
                message.getChannel().sendMessage("Du darfst nicht mehr als einen User markieren").submit();
                return;
            }
            message.getMentionedMembers().forEach(member -> {
                message.getChannel().sendMessage(member.getAsMention() + " wurde gekickt").submit();
                member.kick().submit();
            });
        }
        if (args.length >= 2){
            if (message.getMentionedMembers().isEmpty()) {
                message.getChannel().sendMessage(new MessageBuilder("!kick <@user> [<Grund>]").build()).submit();
                return;
            }
            if (message.getMentionedMembers().size() > 1) {
                message.getChannel().sendMessage("Du darfst nicht mehr als einen User markieren").submit();
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < args.length; i++){
                stringBuilder.append(args[i]);
            }
            message.getMentionedMembers().forEach(member -> {
                member.getUser().openPrivateChannel().queue(privateChannel -> {
                    privateChannel.sendMessage("Du wurdest gekickt\nGrund: " + stringBuilder.toString()).submit();
                });
                message.getChannel().sendMessage(member.getAsMention() + " wurde gekickt").submit();
            });
        }
    }
}
