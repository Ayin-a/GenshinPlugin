package uk.co.tmdavies.genshinplugin.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;

import java.util.ArrayList;
import java.util.List;

@Command(label = "test", description = "A test command for a new plugin!",
        usage = "test <artifactSet> <level> <stats...>", permission = "genshinplugin.artifact")
public class TestCommand implements CommandHandler {

    @Override
    public void execute(Player sender, Player targetPlayer, List<String> args) {

        String artifactSet = args.get(0);
        int level = Integer.parseInt(args.get(1));
        List<String> stats = new ArrayList<>();

        for (int i = 2; i < args.size(); i++) {

            stats.add(args.get(i));

        }

        StringBuilder feedback = new StringBuilder(artifactSet + " " + level + " ");

        for (String string : stats) {

            feedback.append(" ").append(string);

        }

        CommandHandler.sendMessage(sender, feedback.toString());

    }
}
