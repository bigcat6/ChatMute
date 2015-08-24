package com.bigcat6.chatmute;

import java.util.HashSet;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatMute
        extends JavaPlugin
        implements Listener
{
    Set<Player> mutedPlayers = new HashSet();
    private String removemessage = null;
    private String addmessage = null;

    public ChatMute() {}

    public void onEnable() { Bukkit.getPluginManager().registerEvents(this, this); }


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e)
    {
        e.getRecipients().removeAll(mutedPlayers);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (label.equalsIgnoreCase("chatmute"))
        {
            Player p = (Player)sender;
            if (mutedPlayers.contains(p)) {
                mutedPlayers.remove(p);
                removemessage = ChatColor.translateAlternateColorCodes('&', getConfig().getString("removemessage"));
                sender.sendMessage(getConfig().getString(removemessage));
            }
            else
            {
                mutedPlayers.add(p);
                addmessage = ChatColor.translateAlternateColorCodes('&', getConfig().getString("addmessage"));
                sender.sendMessage(getConfig().getString("addmessage"));
            }
        }
        return super.onCommand(sender, command, label, args);
    }
}