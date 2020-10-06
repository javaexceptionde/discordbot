package de.javaexceptionghg.bot.guild.permission;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

public interface IPermissionProvider {

    default Boolean isManageAble(Member member){
        return !member.isOwner() && !member.hasPermission(Permission.ADMINISTRATOR);
    }
}
