package com.temps1101.yummyazuikibar.yummyazuikibar.commands;

import com.temps1101.yummyazuikibar.yummyazuikibar.yamlDB.LoginData;
import com.temps1101.yummyazuikibar.yummyazuikibar.tools.ChatTools;
import com.temps1101.yummyazuikibar.yummyazuikibar.yamlDB.YAMLHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class LoginDataCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy'年'MM'月'dd'日'E'曜日'k'時'mm'分'ss'秒'");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
        if (args.length == 2) {
            Player argPlayer = Bukkit.getPlayer(args[1]);
            if (YAMLHandler.playerExists(argPlayer)) {
                switch (args[0].toLowerCase()) {
                    case "firstjoin":
                        Date lastJoinDate = LoginData.getLoginData(argPlayer).getFirstjoin();
                        sender.sendMessage(ChatTools.prettyStringFormatted("&l&b{0}&r&7は&c&l{1}&r&7に初ログインをしました。", argPlayer.getName(), sdf.format(lastJoinDate)));
                        return true;
                    case "lastjoin":
                        Date firstJoinDate = LoginData.getLoginData(argPlayer).getLastJoin();
                        sender.sendMessage(ChatTools.prettyStringFormatted("&l&b{0}&r&7は&c&l{1}&r&7に最終ログインをしました。", argPlayer.getName(), sdf.format(firstJoinDate)));
                        return true;
                    case "count":
                        Integer count = LoginData.getLoginData(argPlayer).getCount();
                        sender.sendMessage(ChatTools.prettyStringFormatted("&l&b{0}&r&7は&c&l{1}&r&7回ログインをしました。", argPlayer.getName(), count.toString()));
                        return true;
                    default: // when the specified player hasn't joined or doesn't exist
                        sender.sendMessage(ChatTools.prettyStringFormatted("&7使い方 &r: &7/logindata [&3firstjoin &7| &3lastjoin &7| &3count&7] [&3MCID&7]"));
                }
            } else {
                sender.sendMessage(ChatTools.prettyStringFormatted("&c指定したプレイヤーはこのサーバーに未参加、もしくは存在しません！"));
                return false;
            }
        } else { // when args didn't have the expected length
            sender.sendMessage(ChatTools.prettyStringFormatted("&7使い方 &r: &7/logindata [&3firstjoin &7| &3lastjoin &7| &3count&7] [&3MCID&7]"));
            return false;
        }
        return false;
    }
}
