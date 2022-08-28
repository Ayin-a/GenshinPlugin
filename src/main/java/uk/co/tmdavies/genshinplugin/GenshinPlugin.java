package uk.co.tmdavies.genshinplugin;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.plugin.Plugin;

import emu.grasscutter.server.event.EventHandler;
import emu.grasscutter.server.event.HandlerPriority;
import emu.grasscutter.server.event.player.PlayerJoinEvent;
import uk.co.tmdavies.genshinplugin.commands.DumpCommand;
import uk.co.tmdavies.genshinplugin.commands.SetLevelCommand;
import uk.co.tmdavies.genshinplugin.commands.TestCommand;
import uk.co.tmdavies.genshinplugin.events.PlayerListener;
import uk.co.tmdavies.genshinplugin.objects.PluginConfig;
import uk.co.tmdavies.genshinplugin.utils.ShadowUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * The Grasscutter plugin template.
 * This is the main class for the plugin.
 */
public final class GenshinPlugin extends Plugin {

    /* Turn the plugin into a singleton. */
    private static GenshinPlugin instance;

    public PluginConfig configuration;

    /**
     * Gets the plugin instance.
     * @return A plugin singleton.
     */
    public static GenshinPlugin getInstance() {
        return instance;
    }
    
    /**
     * This method is called immediately after the plugin is first loaded into system memory.
     */
    @Override public void onLoad() {

        // Set the plugin instance.
        instance = this;

        /* Public Configs */
        File configFile = new File(this.getDataFolder() + "/config.json");

        if (!configFile.exists())
            this.configuration = ShadowUtils.initiateConfiguration(new File(this.getDataFolder() + "/config.json"));
        else {
            try {
                this.configuration = Grasscutter.getGsonFactory().fromJson(new FileReader(configFile), PluginConfig.class);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Log a plugin status message.
        this.getLogger().info("GenshinPlugin Loaded.");
    }

    /**
     * This method is called before the servers are started, or when the plugin enables.
     */
    @Override public void onEnable() {
        // Register event listeners. (Doesn't work for me as I'm using the development branch and .register() requires a param, cannot set param here.
        new EventHandler<>(PlayerJoinEvent.class)
                .priority(HandlerPriority.LOW)
                .listener(PlayerListener::onJoin)
                .register(this);
        
        // Register commands.
        new TestCommand(this);
        new DumpCommand(this);
        new SetLevelCommand(this);

        // Log a plugin status message.
        this.getLogger().info("GenshinPlugin has been enabled.");
    }

    /**
     * This method is called when the plugin is disabled.
     */
    @Override public void onDisable() {
        // Log a plugin status message.
        this.getLogger().info("GenshinPlugin has been disabled.");
    }

    /**
     * Gets the plugin's configuration.
     * @return A plugin config instance.
     */
    public PluginConfig getConfiguration() {
        return this.configuration;
    }

}
