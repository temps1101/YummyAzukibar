package com.temps1101.yummyazuikibar.yummyazuikibar.listeners;

import com.temps1101.yummyazuikibar.yummyazuikibar.yamlDB.LoginData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        LoginData.cacheLoginData(p);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        LoginData.uncacheLoginData(p);
    }
}
