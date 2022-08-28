package uk.co.tmdavies.genshinplugin.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;
import uk.co.tmdavies.genshinplugin.GenshinPlugin;

import java.util.ArrayList;
import java.util.List;

@Command(label = "test", usage = "<artifactSet> <level> <mainstat:value%> <substat:value%?>...",
        permission = "genshinplugin.artifact")
public class TestCommand implements CommandHandler {

    public TestCommand(GenshinPlugin plugin) {

        plugin.getHandle().registerCommand(this);

    }

    @Override
    public void execute(Player sender, Player targetPlayer, List<String> args) {

        String artifactSet = args.get(0);
        int level = Integer.parseInt(args.get(1));
        List<String> stats = new ArrayList<>();

        for (int i = 2; i < args.size(); i++) {

            stats.add(args.get(i));

        }

        StringBuilder feedback = new StringBuilder(artifactSet + " " + level);

        for (String string : stats) {

            feedback.append(" ").append(string);

        }

        sender.dropMessage(feedback.toString());

    }
}
