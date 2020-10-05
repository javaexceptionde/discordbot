package de.javaexceptionghg.bot.database;

import de.javaexceptionghg.bot.Startup;
import de.javaexceptionghg.bot.database.abstracts.DiscordDatabaseAbstracts;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DiscordDatabaseUtils extends DiscordDatabaseAbstracts {

    private MainDatabase database;

    public DiscordDatabaseUtils(){
        database = Startup.getInstance().getMainDatabase();
    }

    @Override
    public void create(Guild guild) {
        Document document = new Document();
        document.append("ID", 1);
        document.append("GuildId", guild.getId());
        document.append("Owner", Objects.requireNonNull(guild.getOwner()).getId());
        database.insert("Guilds",document);
    }

    @Override
    public void createMember(Guild guild, Member member) {
        Document document = new Document();
        document.append("ID", 1);
        document.append("MemberId", member.getId());
        document.append("GuildId", guild.getId());
        document.append("joined", true);
        database.insert("Members", document);
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void ban(Member member, Guild guild, Member initiator) {
        ban(member, initiator, guild, null);
    }

    @Override
    public void ban(Member member, Member initiator, Guild guild, String reason) {
        ban(member.getId(), initiator.getId(), guild, reason);
    }

    @Override
    public void ban(String id, String initiator, Guild guild, String reason) {
        ban(id, initiator, guild.getId(), reason);
    }

    @Override
    public void ban(String id, String initiator, String guildId, String reason) {
    }

    @Override
    public void ban(String id, String initiator, Guild guild) {
        ban(id, initiator, guild.getId());
    }

    @Override
    public void ban(String id, String initiator, String guildId) {
        ban(id, initiator, guildId, null);
    }

    @Override
    public void ban(String id, Member initiator, String guildId) {
        ban(id, initiator.getId(), guildId);
    }

    @Override
    public void ban(Member member, Guild guild) {
        ban(member.getId(), guild.getId());
    }

    @Override
    public void ban(Member member, Guild guild, String reason) {
        ban(member.getId(), guild, reason);
    }

    @Override
    public void ban(String id, Guild guild, String reason) {
        ban(id, null, guild, reason);
    }

    @Override
    public void ban(String id, Guild guild) {
        ban(id, guild.getId());
    }

    @Override
    public void ban(String id, String guildId) {
        ban(id, "", guildId);
    }

    @Override
    public void kick(Member member) {

    }

    @Override
    public void kick(Member member, String reason) {

    }

    @Override
    public void kick(Member member, Member initiator) {

    }

    @Override
    public void kick(Member member, Member initiator, String reason) {

    }

    @Override
    public boolean guildExists(Guild guild) {
        return database.contains("GuildId", guild.getId(), "Guilds");
    }

    @Override
    public boolean memberExists(Guild guild, Member member) {
        Map<String, Object> map = new HashMap<>();
        map.put("MemberId", member.getId());
        map.put("GuildId", guild.getId());
        return database.contains(map, "Members");
    }

}