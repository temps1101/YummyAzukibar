package com.temps1101.yummyazukibar.yummyazukibar.yamlDB;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class LoginData {
    public static HashMap<UUID, LoginData> cache = new HashMap<UUID, LoginData>();
    private Date firstJoin;
    private Date lastJoin;
    private int count;
    private final Date lastUpdate;
    public LoginData(Date firstJoin, Date lastJoin, int count, Date lastUpdate) {
        this.firstJoin = firstJoin;
        this.lastJoin = lastJoin;
        this.count = count;
        this.lastUpdate = lastUpdate;
    }
    public static LoginData getLoginData(Player p) {
        UUID uuid = p.getUniqueId();
        if (cache.containsKey(uuid)) {
            return cache.get(uuid);
        } else {
            return cacheLoginData(p);
        }
    }
    public static LoginData cacheLoginData(Player p) {
        UUID uuid = p.getUniqueId();
        LoginData loginData = YAMLHandler.getLoginData(p);
        loginData.updateData(p);
        cache.put(uuid, loginData);
        return loginData;
    }
    public static void uncacheLoginData(Player p) {
        UUID uuid = p.getUniqueId();
        YAMLHandler.saveLoginData(p, cache.get(uuid));
        cache.remove(uuid);
    }
    public Date getFirstjoin() {
        return firstJoin;
    }
    public Date getLastJoin() {
        return lastJoin;
    }
    public int getCount() {
        return count;
    }
    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void updateData(Player p) {
        firstJoin = new Date(p.getFirstPlayed());
        lastJoin = new Date(p.getLastPlayed());
        count++;
    }
    public static void uncacheAllLoginData() {
        cache.forEach((uuid, loginData) -> {
            YAMLHandler.saveLoginData(Bukkit.getPlayer(uuid), loginData);
        });
    }
}
