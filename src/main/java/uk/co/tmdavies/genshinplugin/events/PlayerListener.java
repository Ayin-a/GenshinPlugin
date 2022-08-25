package uk.co.tmdavies.genshinplugin.events;

import emu.grasscutter.game.player.Player;
import emu.grasscutter.server.event.EventHandler;
import emu.grasscutter.server.event.HandlerPriority;
import emu.grasscutter.server.event.player.PlayerJoinEvent;
import uk.co.tmdavies.genshinplugin.GenshinPlugin;
import uk.co.tmdavies.genshinplugin.utils.ShadowConfig;

/**
 * A class containing all event handlers.
 * 
 * Syntax in event handler methods are similar to CraftBukkit.
 * To register an event handler, create a new instance of {@link EventHandler}.
 * Pass through the event class you want to handle. (ex. `new EventHandler<>(PlayerJoinEvent.class);`)
 * From this class, invoke {@link EventHandler#register()} to register the handler.
 * You can change the point at which the handler method is invoked with {@link EventHandler#priority(HandlerPriority)}.
 * You can set whether the handler method should be invoked when another plugin cancels the event with {@link EventHandler#ignore(boolean)}.
 */
public final class PlayerListener {
    /* Saves the configuration to a local variable, reducing calls made to the plugin instance. */
    private static final ShadowConfig config = GenshinPlugin.CONFIG;
    
    /**
     * Called when the player joins the server.
     * @param event PlayerJoinEvent.
     */
    public static void onJoin(PlayerJoinEvent event) {

        if (config.getString("Messages.Join") == null) return; // Check if the plugin is configured to send a message when a player joins.
        
        Player player = event.getPlayer(); // Get the player who joined from the event.
        player.dropMessage(config.getString("Messages.Join").replace("%player%", player.getNickname())); // "Drop" the player a message.

    }
}
