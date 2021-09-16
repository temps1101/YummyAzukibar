package com.temps1101.yummyazukibar.yummyazukibar;

import com.temps1101.yummyazukibar.yummyazukibar.commands.ConstructTabComplete;
import com.temps1101.yummyazukibar.yummyazukibar.commands.LoginDataCommand;
import com.temps1101.yummyazukibar.yummyazukibar.listeners.PlayerJoinListener;
import com.temps1101.yummyazukibar.yummyazukibar.yamlDB.LoginData;
import com.temps1101.yummyazukibar.yummyazukibar.yamlDB.YAMLHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;



public final class YummyAzukibar extends JavaPlugin {
    private static YAMLHandler handler;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        handler = new YAMLHandler(getDataFolder());
        Bukkit.getPluginCommand("logindata").setExecutor(new LoginDataCommand());
        Bukkit.getPluginCommand("logindata").setTabCompleter(new ConstructTabComplete());
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable() {
        LoginData.uncacheAllLoginData();
    }
}
