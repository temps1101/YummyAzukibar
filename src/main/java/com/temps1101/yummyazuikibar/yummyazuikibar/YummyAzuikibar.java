package com.temps1101.yummyazuikibar.yummyazuikibar;

import com.temps1101.yummyazuikibar.yummyazuikibar.commands.LoginDataCommand;
import com.temps1101.yummyazuikibar.yummyazuikibar.listeners.PlayerJoinListener;
import com.temps1101.yummyazuikibar.yummyazuikibar.yamlDB.LoginData;
import com.temps1101.yummyazuikibar.yummyazuikibar.yamlDB.YAMLHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;



public final class YummyAzuikibar extends JavaPlugin {
    private static YAMLHandler handler;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        handler = new YAMLHandler(getDataFolder());
        Bukkit.getPluginCommand("logindata").setExecutor(new LoginDataCommand());
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable() {
        LoginData.uncacheAllLoginData();
    }
}
