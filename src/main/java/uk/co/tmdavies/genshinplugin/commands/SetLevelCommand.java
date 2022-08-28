package uk.co.tmdavies.genshinplugin.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.entity.EntityAvatar;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.server.packet.send.PacketSceneEntityAppearNotify;
import emu.grasscutter.utils.Position;
import uk.co.tmdavies.genshinplugin.GenshinPlugin;
import uk.co.tmdavies.genshinplugin.utils.ShadowUtils;

import java.util.List;

@Command(label = "setlevel", usage = "<level>",
        permission = "genshinplugin.setlevel")
public class SetLevelCommand implements CommandHandler {

    public SetLevelCommand(GenshinPlugin plugin) {

        plugin.getHandle().registerCommand(this);

    }

    @Override
    public void execute(Player sender, Player targetPlayer, List<String> args) {

        EntityAvatar avatar = targetPlayer.getTeamManager().getCurrentAvatarEntity();
        boolean targetSelf = false;

        if (args.size() == 0) {

            sender.dropMessage(ShadowUtils.Colour("`rUsage: /setlevel [<@UID>] <level>"));

            return;

        }

        int newLevel = Integer.parseInt(args.get(0));

        if (newLevel > 90) {

            sender.dropMessage(ShadowUtils.Colour("`rLevel cannot be above 90."));

            return;

        }

        if (avatar == null) {

            if (sender.getTeamManager().getCurrentAvatarEntity() == null) {

                sender.dropMessage(ShadowUtils.Colour("`r[Error] Cannot grab current active avatar. SetLevelCommand#execute"));

                return;

            }

            avatar = sender.getTeamManager().getCurrentAvatarEntity();
            targetSelf = true;

        }

        int promotionLevel = 0;

        if (newLevel <= 20 && newLevel >= 1) promotionLevel = 1;
        else if (newLevel <= 50 && newLevel >= 41) promotionLevel = 2;
        else if (newLevel <= 60 && newLevel >= 51) promotionLevel = 3;
        else if (newLevel <= 70 && newLevel >= 61) promotionLevel = 4;
        else if (newLevel <= 80 && newLevel >= 71) promotionLevel = 5;
        else if (newLevel >= 81) promotionLevel = 6;

        avatar.getAvatar().setPromoteLevel(promotionLevel);
        avatar.getAvatar().setLevel(newLevel);
        avatar.getAvatar().save();

        avatar.getAvatar().recalcStats();

        Player player = targetSelf ? sender : targetPlayer;

        ShadowUtils.refreshScene(player);

        player.dropMessage(ShadowUtils.Colour("`gSuccessfully set " + avatar.getAvatar().getAvatarData().getName() + "'s level to " + args.get(0) + "."));

    }

}
