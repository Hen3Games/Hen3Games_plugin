package nl.hen3games.mcdev.Hen3Games_plugin.Files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Files {

    public static File MijnPluginmdk = new File("plugins/Mijn Plugin");
    public static File Messagesmdk = new File("plugins/Mijn Plugin/Messages");
    public static File Config = new File("plugins/Mijn Plugin/Config.yml");
    public static File SpelerData = new File("plugins/Mijn Plugin/SpelerData.yml");
    public static File rank = new File("plugins/Mijn Plugin/RankData.yml");
    public static File Messages = new File("plugins/Mijn Plugin/Messages/Messages.yml");
    public static File Permissions = new File("plugins/Mijn Plugin/Messages/Permissions.yml");

    public static FileConfiguration configConfig = new YamlConfiguration();
    public static FileConfiguration spelerdataConfig =  new YamlConfiguration();
    public static FileConfiguration rankConfig = new YamlConfiguration();
    public static FileConfiguration messagesConfig = new YamlConfiguration();
    public static FileConfiguration permissionsConfig = new YamlConfiguration();

}
