package com.temps1101.yummyazuikibar.yummyazuikibar.yamlDB;

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
    public LoginData(Date firstJoin, Date lastJoin, int count) {
        this.firstJoin = firstJoin;
        this.lastJoin = lastJoin;
        this.count = count;
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
        loginData.increaseCount();
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
    public void increaseCount() {
        count++;
    }
    public static void uncacheAllLoginData() {
        cache.forEach((uuid, loginData) -> {
            YAMLHandler.saveLoginData(Bukkit.getPlayer(uuid), loginData);
        });
    }
}
