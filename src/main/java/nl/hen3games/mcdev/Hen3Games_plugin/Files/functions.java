package nl.hen3games.mcdev.Hen3Games_plugin.Files;

import nl.hen3games.mcdev.Hen3Games_plugin.records.Messages;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Permissions;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Records;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;

public class functions {

    static File MijnPluginmdk = Files.MijnPluginmdk;
    static File Messagesmdk = Files.Messagesmdk;

    static File Config = Files.Config;
    static File SpelerData= Files.SpelerData;
    static File rank = Files.rank;
    static File messages = Files.Messages;
    static File permissions = Files.Permissions;

    static FileConfiguration configConfig = Files.configConfig;
    static FileConfiguration spelerdataConfig = Files.spelerdataConfig;
    static FileConfiguration rankConfig = Files.rankConfig;
    static FileConfiguration messagesConfig = Files.messagesConfig;
    static FileConfiguration permissionsConfig = Files.permissionsConfig;

    static ArrayList<Records.playerRec> playerList = Records.playerList;
    static ArrayList<Records.rankRec> rankList = Records.rankList;


    public static void makeDirectroyIfNotExist(File file){
        if (!file.exists()){
            file.mkdir();
        }
    }

    public static void makeFileIfNotExist(File file){
        if (!file.exists()){
            try{
                file.createNewFile();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public static void saveFiles(){
        saveSpelerData();
        saveRank();
        saveMessages();
        savePermissions();
    }

    public static void loadFiles(){
        loadMijnPluginmdk();
        loadMessagesmdk();
        loadConfig();
        loadRank();
        loadSpelerData();
        loadRankMembers();
        loadMessages();
        loadPermissions();
    }

    private static void saveRank() {
        if (rankList.size() >= 1){
            makeDirectroyIfNotExist(MijnPluginmdk);
            makeFileIfNotExist(rank);
            try{
                rankConfig.load(rank);
                for (int a = 0; a < rankList.size(); a++){
                    Records.rankRec Rank = rankList.get(a);
                    rankConfig.set("Ranks." + a + ".Name", Rank.name);
                    rankConfig.set("Ranks." + a + ".Colour", "");
                    rankConfig.set("Ranks." + a + ".Colour", Rank.colour);
                    rankConfig.set("Ranks." + a + ".Members.", null);
                    for (int b = 0; 0 < Rank.member.size(); b++){
                        Records.rankMemberRec member = Rank.member.get(b);
                        rankConfig.set("Ranks." + a + ".Members." + b + ".Name", member.player.Name);
                        rankConfig.set("Ranks." + a + ".Members." + b + ".Uuid", member.player.Uuid);
                    }
                    rankConfig.save(rank);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private static void saveSpelerData(){
        if (playerList.size() >= 1) {
            makeDirectroyIfNotExist(MijnPluginmdk);
            makeFileIfNotExist(SpelerData);
            try {
                spelerdataConfig.load(SpelerData);
                for (int a = 0; a < playerList.size(); a++){
                    Records.playerRec player = playerList.get(a);
                    spelerdataConfig.set("Spelers." + a + ".Uuid", player.Uuid);
                    spelerdataConfig.set("Spelers." + a + ".Name", player.Name);
                    spelerdataConfig.set("Spelers." + a + ".Colour", player.colour);
                    if (player.Rank != null) {
                        spelerdataConfig.set("Spelers." + a + ".Rank", player.Rank.name);
                    }else{
                        spelerdataConfig.set("Spelers." + a + ".Rank", null);
                    }
                    spelerdataConfig.save(SpelerData);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private static void loadMijnPluginmdk(){
        if (!MijnPluginmdk.exists()){
            makeDirectroyIfNotExist(MijnPluginmdk);
        }
    }

    private static void loadMessagesmdk(){
        if (!Messagesmdk.exists()){
            makeDirectroyIfNotExist(Messagesmdk);
        }
    }

    private static void loadConfig(){
        if (!Config.exists()){
            makeFileIfNotExist(Config);
            try{
                configConfig.load(Config);
                configConfig.set("Config", "");
                configConfig.save(Config);
            }catch(Exception ex){
                ex.printStackTrace();
            }

        }
    }

    private static void loadSpelerData(){
        if (SpelerData.exists()){
            try {
                spelerdataConfig.load(SpelerData);
                for (int a = 0; spelerdataConfig.contains("Spelers." + a); a++){
                    Records.playerRec playerRecord = new Records.playerRec();
                    playerList.add(playerRecord);
                    playerRecord.Uuid = spelerdataConfig.getString("Spelers." + a + ".Uuid");
                    playerRecord.Name = spelerdataConfig.getString("Spelers." + a + ".Name");
                    playerRecord.colour = spelerdataConfig.getString("Spelers." + a + ".Colour");
                    playerRecord.Rank = getRankRecord(spelerdataConfig.getString("Spelers." + a + ".Rank"));
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private static void loadRank() {
        if (rank.exists()){
            try{
                rankConfig.load(rank);
                for (int a = 0; rankConfig.contains("Ranks." + a); a++){
                    Records.rankRec rankRecord = new Records.rankRec();
                    rankList.add(rankRecord);
                    rankRecord.name = rankConfig.getString("Ranks." + a + ".Name");
                    rankRecord.colour = rankConfig.getString("Ranks." + a + ".Colour");
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private static void loadRankMembers(){
        if (rank.exists()){
            try{
                rankConfig.load(rank);
                for (int a = 0; rankConfig.contains("Ranks." + a); a++){
                    Records.rankRec rankRecord = getRankRecord(rankConfig.getString("Ranks." + a + ".name"));
                    for (int b = 0; rankConfig.contains("Ranks." + a + ".Members." + b); b++){
                        Records.rankMemberRec rankMemberRecord = new Records.rankMemberRec();
                        rankRecord.member.add(rankMemberRecord);
                        Records.playerRec playerRecord = getPlayerRecord(rankConfig.getString("Ranks." + a + ".Members." + b + ".Uuid"));
                        rankMemberRecord.player = playerRecord;
                    }
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public static Records.playerRec getPlayerRecord(String Uuid){
        for (Records.playerRec playerRecord : playerList){
            if (playerRecord.Uuid.equals(Uuid)){
                return playerRecord;
            }
        }
        return null;
    }

    public static Records.rankRec getRankRecord(String name){
        for (Records.rankRec rankRecord : rankList){
            if (rankRecord.name.equals(name)){
                return rankRecord;
            }
        }
        return null;
    }

    private static void loadMessages(){
        if (!messages.exists()){
            makeFileIfNotExist(messages);
            try{
                messagesConfig.load(messages);
                if (messagesConfig.getString("Messages.JoinMessage") != null) {
                    Messages.JoinMessage = messagesConfig.getString("Messages.JoinMessage");
                }else{
                    Messages.JoinMessage = "&6%player% heeft de server gejoind!";
                }
                if (messagesConfig.getString("Messages.NoPerms") != null){
                    Messages.NoPerms = messagesConfig.getString("Messages.NoPerms");
                }else{
                    Messages.NoPerms = "&cJe hebt hier geen permissie voor!";
                }
                if (messagesConfig.getString("Messages.senderCmd") != null) {
                    Messages.senderCmd = messagesConfig.getString("Messages.senderCmd");
                }else{
                    Messages.senderCmd = "&cDeze command is alleen voor spelers";
                }
                if (messagesConfig.getString("Messages.ExistRank") != null){
                    Messages.ExistRank = messagesConfig.getString("Messages.ExistRank");
                }else{
                    Messages.ExistRank = "&cDeze rank bestaat al";
                }
                if (messagesConfig.getString("Messages.newRankForPlayer") != null){
                    Messages.newRankForPlayer = messagesConfig.getString("Messages.newRankForPlayer");
                }else
                    Messages.newRankForPlayer = "&6Deze speler heeft nu de rank: ";
                if (messagesConfig.getString("Messages.isNotAPlayer") != null){
                    Messages.isNotAPlayer = messagesConfig.getString("Messages.isNotAPlayer");
                }else{
                    Messages.isNotAPlayer = "&cDit is geen speler";
                }
                if (messagesConfig.getString("Messages.newColourForRank") != null){
                    Messages.newColourForRank= messagesConfig.getString("Messages.newColourForRank");
                }else{
                    Messages.newColourForRank = "&6De kleur van de rank is succusful veranderd";
                }
                if (messagesConfig.getString("Messages.QuitMessage") != null){
                    Messages.QuitMessage = messagesConfig.getString("Messages.QuitMessage");
                }else{
                    Messages.QuitMessage = "&6%player% heeft de server verlaten!";
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }

        }
    }

    private static void saveMessages(){
        if (!messages.exists()) {
            makeFileIfNotExist(messages);
            try {
                messagesConfig.load(messages);
                if (Messages.JoinMessage != null){
                    messagesConfig.set("Messages.JoinMessage", Messages.JoinMessage);
                }else{
                    messagesConfig.set("Messages.JoinMessage", "&6%player% heeft de server gejoind!");
                }
                if (Messages.NoPerms != null){
                    messagesConfig.set("Messages.NoPerms", Messages.NoPerms);
                }else {
                    messagesConfig.set("Messages.NoPerms", "&cJe hebt hier geen permissie voor!");
                }
                if (Messages.senderCmd != null){
                    messagesConfig.set("Messages.senderCmd", Messages.senderCmd);
                }else{
                    messagesConfig.set("Messages.senderCmd", "&cDeze command is alleen voor spelers");
                }
                if (Messages.ExistRank != null){
                    messagesConfig.set("Messages.ExistRank", Messages.ExistRank);
                }else{
                    messagesConfig.set("Messages.ExistRank", "&cDeze rank bestaat al");
                }
                if (Messages.newRankForPlayer != null){
                    messagesConfig.set("Messages.newRankForPlayer", Messages.newRankForPlayer);
                }else{
                    messagesConfig.set("Messages.newRankForPlayer", "&6Deze speler heeft nu de rank: ");
                }
                if (Messages.newColourForRank != null){
                    messagesConfig.set("Messages.newColourForRank", Messages.newColourForRank);
                }else{
                    messagesConfig.set("Messages.newColourForRank", "&6De kleur van de rank is succusful veranderd");
                }
                if (Messages.isNotAPlayer != null){
                    messagesConfig.set("Messages.isNotAPlayer", Messages.isNotAPlayer);
                }else{
                    messagesConfig.set("Messages.isNotAPlayer", "&cDit is geen speler");
                }
                if (Messages.QuitMessage != null){
                    messagesConfig.set("Messages.QuitMessage", Messages.QuitMessage);
                }else{
                    messagesConfig.set("Messages.QuitMessage", "&6%player% heeft de server verlaten!");
                }
                messagesConfig.save(messages);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private static void loadPermissions(){
        if (!permissions.exists()){
            makeFileIfNotExist(permissions);
            try{
                permissionsConfig.load(permissions);
                if (permissionsConfig.getString("Permissions.Mod") != null){
                    Permissions.Mod = permissionsConfig.getString("Permissions.Mod");
                }else{
                    Permissions.Mod = "mod";
                }
                if (permissionsConfig.getString("Permissions.Rank") != null){
                    Permissions.Rank = permissionsConfig.getString("Permissions.Rank");
                }else{
                    Permissions.Rank = "mod.rank";
                }
                if (permissionsConfig.getString("Permissions.RankCreate") != null){
                    Permissions.RankCreate = permissionsConfig.getString("Permissions.RankCreate");
                }else {
                    Permissions.RankCreate = "mod.rank.create";
                }
                if (permissionsConfig.getString("Permissions.RankList") != null){
                    Permissions.RankList = permissionsConfig.getString("Permissions.RankList");
                }else{
                    Permissions.RankList = "mod.rank.list";
                }
                if (permissionsConfig.getString("Permissions.RankRemove") != null){
                    Permissions.RankRemove = permissionsConfig.getString("Permissions.RankRemove");
                }else{
                    Permissions.RankRemove = "mod.rank.remove";
                }
                if (permissionsConfig.getString("Permissions.RankSet") != null){
                    Permissions.RankSet = permissionsConfig.getString("Permissions.RankSet");
                }else{
                    Permissions.RankSet = "mod.rank.set";
                }
                if (permissionsConfig.getString("Permissions.RankColour") != null){
                    Permissions.RankColour = permissionsConfig.getString("Permissions.RankColour");
                }else{
                    Permissions.RankColour = "mod.rank.colour";
                }
                if (permissionsConfig.getString("Permissions.Player") != null){
                    Permissions.Player = permissionsConfig.getString("Permissions.Player");
                }else{
                    Permissions.Player = "mod.player";
                }
                if (permissionsConfig.getString("Permissions.PlayerSetColour") != null){
                    Permissions.PlayerSetColour = permissionsConfig.getString("Permissions.PlayerSetColour");
                }else{
                    Permissions.PlayerSetColour = "mod.player.setcolour";
                }
                if (permissionsConfig.getString("Permissions.Gamemode") != null){
                    Permissions.Gamemode = permissionsConfig.getString("Permissions.Gamemode");
                }else{
                    Permissions.Gamemode = "gamemode";
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }

        }
    }

    private static void savePermissions(){
        if (!permissions.exists()){
            makeFileIfNotExist(permissions);
            try{
                permissionsConfig.load(permissions);
                if (Permissions.Mod != null){
                    permissionsConfig.set("Permissions.Mod", Permissions.Mod);
                }else{
                    permissionsConfig.set("Permissions.Mod", "mod");
                }
                if (Permissions.Rank != null){
                    permissionsConfig.set("Permission.Rank", Permissions.Rank);
                }else{
                    permissionsConfig.set("Permissions.Rank", "mod.rank");
                }
                if (Permissions.RankCreate != null){
                    permissionsConfig.set("Permissions.RankCreate", Permissions.RankCreate);
                }else{
                    permissionsConfig.set("Permissions.RankCreate", "mod.rank.create");
                }
                if (Permissions.RankList != null){
                    permissionsConfig.set("Permissions.RankList", Permissions.RankList);
                }else{
                    permissionsConfig.set("Permissions.RankList", "mod.rank.list");
                }
                if (Permissions.RankRemove != null){
                    permissionsConfig.set("Permissions.RankRemove", Permissions.RankRemove);
                }else{
                    permissionsConfig.set("Permissions.RankRemove", "mod.rank.remove");
                }
                if (Permissions.RankSet != null){
                    permissionsConfig.set("Permissions.RankSet", Permissions.RankSet);
                }else{
                    permissionsConfig.set("Permissions.RankSet", "mod.rank.set");
                }
                if (Permissions.RankColour != null){
                    permissionsConfig.set("Permisions.RankColour", Permissions.RankColour);
                }else{
                    permissionsConfig.set("Permissions.RankColour", "mod.rank.colour");
                }
                if (Permissions.Player != null){
                    permissionsConfig.set("Permissions.Player", Permissions.Player);
                }else{
                    permissionsConfig.set("Permissions.Player", "mod.player");
                }
                if (Permissions.PlayerSetColour != null){
                    permissionsConfig.set("Permissions.PlayerSetColour", Permissions.PlayerSetColour);
                }else{
                    permissionsConfig.set("Permissions.PlayerSetColour", "mod.player.setcolour");
                }
                if (Permissions.Gamemode != null){
                    permissionsConfig.set("Permissions.Gamemmode", Permissions.Gamemode);
                }else{
                    permissionsConfig.set("Permissions.Gamemode", "gamemode");
                }
                permissionsConfig.save(permissions);
            }catch(Exception ex){
                ex.printStackTrace();
            }

        }
    }

    public static void createRank(String name, Player player){
        Records.rankRec rankRecord = new Records.rankRec();
        rankList.add(rankRecord);
        rankRecord.name = name;
        rankRecord.colour = "";
        player.sendMessage(ChatColor.AQUA + "De rank " + name + " is gemaakt!");
    }

    public static void createPlayer(Player player){
        Records.playerRec playerRecord = new Records.playerRec();
        playerList.add(playerRecord);
        playerRecord.Name = player.getName();
        playerRecord.Uuid = player.getUniqueId().toString();
        playerRecord.Rank = null;
    }

    public static boolean playerAlreadyCreated(Player player){
        for (Records.playerRec playerRecord : playerList){
            if (playerRecord.Uuid.equals(player.getUniqueId().toString())){
                return true;
            }
        }
        return false;
    }

    public static Records.rankRec getPlayerRankRecord(Records.playerRec player){
        for (Records.playerRec playerRecord : playerList){
            if (playerRecord.Name.equals(player.Name)){
                return player.Rank;
            }
        }
        return null;
    }

    public static void setPlayerInRank(Records.rankRec rank, Records.playerRec player){
        Records.rankMemberRec rankMemberRecord = new Records.rankMemberRec();
        rank.member.add(rankMemberRecord);
        rankMemberRecord.player = player;
        player.Rank = rank;
    }

    public static boolean isPlayerInRank(Records.playerRec player){
        for (Records.playerRec playerRecord : playerList){
            if (playerRecord.Name.equals(player.Name)) {
                if (player.Rank != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void removePlayerFormRank(Records.rankRec rank, Records.playerRec player){
        if (rank == null || player == null){
            return;
        }
        for (int a = 0; a < rank.member.size(); a++){
            Records.rankMemberRec rankMember = rank.member.get(a);
            if (rankMember.player.Uuid.equals(player.Uuid)) {
                rank.member.remove(a);
                player.Rank = null;
            }
        }
    }

    public static boolean isRankColourAlreadySet(Records.rankRec rankRecord){
        for (Records.rankRec rank : rankList){
            if (rank.name.toLowerCase().equals(rankRecord.name.toLowerCase())){
                if (rankRecord.colour != null){
                    return true;
                }
            }
        }
        return false;
    }

    public static void setColourRank(Records.rankRec rankRecord, String args){
        rankRecord.colour = args;
    }

    public static void removeColourFormRank(Records.rankRec rankRecord){
        rankRecord.colour = "";
    }

    public static boolean isPlayerColourAlreadySet(Records.playerRec playerRecord){
        for (Records.playerRec player : playerList){
            if (player.Name.toLowerCase().equals(playerRecord.Name.toLowerCase())){
                if (playerRecord.colour != null){
                    return true;
                }
            }
        }
        return false;
    }

    public static void setColourPlayer(Records.playerRec playerRecord, String args){
        playerRecord.colour = args;
    }

    public static void removeColourFormPlayer(Records.playerRec playerRecord){
        playerRecord.colour = "";
    }

}
