package com.temps1101.yummyazukibar.yummyazukibar.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ConstructTabComplete implements TabCompleter {
    private List<String> options = new ArrayList<String>(){
        {
            add("firstjoin");
            add("lastjoin");
            add("count");
        }
    };
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
         if (cmd.getName().equalsIgnoreCase("logindata") && args.length <= 1) {
             return options;
         } else {
             return null;
         }
     }
}
