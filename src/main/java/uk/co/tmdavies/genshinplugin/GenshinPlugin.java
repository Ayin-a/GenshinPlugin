package uk.co.tmdavies.genshinplugin;

import emu.grasscutter.plugin.Plugin;
import emu.grasscutter.server.event.EventHandler;
import emu.grasscutter.server.event.HandlerPriority;
import emu.grasscutter.server.event.player.PlayerJoinEvent;

import uk.co.tmdavies.genshinplugin.commands.ExampleCommand;
import uk.co.tmdavies.genshinplugin.commands.TestCommand;
import uk.co.tmdavies.genshinplugin.events.PlayerListener;
import uk.co.tmdavies.genshinplugin.utils.ShadowConfig;

/**
 * The Grasscutter plugin template.
 * This is the main class for the plugin.
 */
public final class GenshinPlugin extends Plugin {

    /* Turn the plugin into a singleton. */
    private static GenshinPlugin instance;

    /* Public Configs */
    public static ShadowConfig CONFIG;

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

        CONFIG = new ShadowConfig("config");

        CONFIG.add("Messages.Join", "Welcome to Shisou, %player%!");

        // Log a plugin status message.
        this.getLogger().info("GenshinPlugin Loaded.");
    }

    /**
     * This method is called before the servers are started, or when the plugin enables.
     */
    @Override public void onEnable() {
        // Register event listeners.
        new EventHandler<>(PlayerJoinEvent.class)
                .priority(HandlerPriority.LOW)
                .listener(PlayerListener::onJoin)
                .register();
        
        // Register commands.
        this.getHandle().registerCommand(new TestCommand());

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

}
