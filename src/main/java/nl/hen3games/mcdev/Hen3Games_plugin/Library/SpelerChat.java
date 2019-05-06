package nl.hen3games.mcdev.Hen3Games_plugin.Library;

import nl.hen3games.mcdev.Hen3Games_plugin.Files.functions;
import nl.hen3games.mcdev.Hen3Games_plugin.records.Records;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class SpelerChat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if (!functions.playerAlreadyCreated(player)){
            functions.createPlayer(player);
        }

        Records.playerRec playerRecord = functions.getPlayerRecord(player.getUniqueId().toString());
        String format = "";
        if (playerRecord.Rank != null){
            Records.rankRec rankRecord = functions.getRankRecord(playerRecord.Rank.name);
            if (rankRecord.colour != null){
                String colour = rankRecord.colour;
                if (playerRecord.colour != null) {
                    String spelerKleur = playerRecord.colour;
                    format = ChatColor.DARK_GRAY + "[" + ChatColor.translateAlternateColorCodes('&', colour) + rankRecord.name + ChatColor.DARK_GRAY + "][" + ChatColor.translateAlternateColorCodes('&', spelerKleur) + player.getName() + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + event.getMessage();
                }else{
                    format = ChatColor.DARK_GRAY + "[" + ChatColor.translateAlternateColorCodes('&', colour) + rankRecord.name + ChatColor.DARK_GRAY + "][" + ChatColor.GRAY + player.getName() + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + event.getMessage();
                }
            }else if (playerRecord.colour != null){
                String spelerKleur = playerRecord.colour;
                format = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + rankRecord.name + ChatColor.DARK_GRAY + "][" + ChatColor.translateAlternateColorCodes('&', spelerKleur) + player.getName() + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + event.getMessage();
            }else {
                format = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + rankRecord.name + ChatColor.DARK_GRAY + "][" + ChatColor.GRAY + player.getName() + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + event.getMessage();
            }
            event.setFormat(format);
        }
    }
}
