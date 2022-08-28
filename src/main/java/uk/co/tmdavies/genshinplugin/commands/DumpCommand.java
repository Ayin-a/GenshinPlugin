package uk.co.tmdavies.genshinplugin.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;
import uk.co.tmdavies.genshinplugin.GenshinPlugin;
import uk.co.tmdavies.genshinplugin.objects.PluginConfig;
import uk.co.tmdavies.genshinplugin.utils.ShadowUtils;

import java.util.ArrayList;
import java.util.List;

@Command(label = "dump", usage = "dump",
        permission = "genshinplugin.dump")
public class DumpCommand implements CommandHandler {

    public DumpCommand(GenshinPlugin plugin) {

        plugin.getHandle().registerCommand(this);

    }

    @Override
    public void execute(Player sender, Player targetPlayer, List<String> args) {

        // Find a better way of doing this. StringBuilder + MailBuilder?

        PluginConfig config = GenshinPlugin.getInstance().configuration;

        sender.dropMessage("Configuration Options\n sendJoinMessage: "
                + config.sendJoinMessage + "\njoinMessage: \""
                + config.joinMessage.replace("%player%", sender.getNickname())
                + "\""
        );

        sender.dropMessage(ShadowUtils.Colour("This `ris to test `ecthe colour system `gfor chat."));

    }
}
