package com.temps1101.yummyazukibar.yummyazukibar.yamlDB;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class YAMLHandler {
    private static File pluginDirectory;
    public static YAMLHandler(File pluginDirectory) {
        this.pluginDirectory = pluginDirectory;
    }

    public static LoginData getLoginData(Player p) {
        File loginDataFile = new File(pluginDirectory, p.getUniqueId().toString() + ".yml");
        if (!loginDataFile.exists()) {
            try {
                loginDataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(loginDataFile);
        Date firstJoin = new Date(conf.getLong("first_join", p.getFirstPlayed()));
        Date lastJoin = new Date(conf.getLong("last_join", p.getLastPlayed()));
        int count = conf.getInt("count", 0);
        Date lastUpdate = new Date(conf.getLong("last_update", (new Date()).getTime()));
        return new LoginData(firstJoin, lastJoin, count, lastUpdate);
    }
    public static void saveLoginData(Player p, LoginData loginData) {
        File loginDataFile = new File(pluginDirectory, p.getUniqueId().toString() + ".yml");
        if (loginDataFile.exists()) {
            YamlConfiguration conf = YamlConfiguration.loadConfiguration(loginDataFile);
            conf.set("first_join", loginData.getFirstjoin());
            conf.set("last_join", loginData.getLastJoin());
            conf.set("count", loginData.getCount());
            conf.set("last_update", (new Date()).getTime());
            try {
                conf.save(loginDataFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static boolean playerExists(Player p) {
        File loginDataFile = new File(pluginDirectory, p.getUniqueId().toString() + ".yml");
        return loginDataFile.exists();
    }
}
