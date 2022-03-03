package dev.martinl.bsbrewritten;

import dev.martinl.bsbrewritten.commands.MainCommand;
import dev.martinl.bsbrewritten.listeners.InteractListener;
import dev.martinl.bsbrewritten.listeners.InventoryCloseListener;
import dev.martinl.bsbrewritten.manager.ShulkerManager;
import dev.martinl.bsbrewritten.util.ConfigurationParser;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class BSBRewritten extends JavaPlugin {
    private ShulkerManager shulkerManager;
    private ConfigurationParser configurationParser;

    @Override
    public void onEnable() {
        shulkerManager = new ShulkerManager(this);
        loadAndParseConfig();
        InteractListener interactListener = new InteractListener(this);
        InventoryCloseListener inventoryCloseListener = new InventoryCloseListener(this);
        MainCommand mainCommand = new MainCommand(this);

        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "BetterShulkerBoxes version " +
                ChatColor.YELLOW + this.getDescription().getVersion()
        + ChatColor.AQUA + " loaded! (" + this.getServer().getVersion() + " | " + this.getServer().getBukkitVersion() + ") - Made by Rektb (lMartin3#1975)");
    }

    @Override
    public void onDisable() {
        shulkerManager.closeAllInventories(true);
    }

    public void loadAndParseConfig() {
        saveDefaultConfig();
        saveConfig();
        configurationParser = new ConfigurationParser(this.getConfig());
        configurationParser.parseConfiguration();
    }


}
