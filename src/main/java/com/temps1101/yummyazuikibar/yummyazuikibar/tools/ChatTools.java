package com.temps1101.yummyazuikibar.yummyazuikibar.tools;

import org.bukkit.ChatColor;

import java.text.MessageFormat;

public class ChatTools {
    public static String prettyStringFormatted(String text, Object... args) {
        return MessageFormat.format(ChatColor.translateAlternateColorCodes('&', "&7[&3YummyAzukibar&7] &f: &r" + text), args);
    }
}
