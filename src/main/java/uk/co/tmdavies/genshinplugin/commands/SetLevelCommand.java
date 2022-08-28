package uk.co.tmdavies.genshinplugin.commands;

import emu.grasscutter.GameConstants;
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

        EntityAvatar avatar = null;
        boolean targetSelf = false;

        if (sender.getUid() == GameConstants.SERVER_CONSOLE_UID) {

            sender.dropMessage(ShadowUtils.Colour("`rOnly in-game players can use this command."));

            return;

        }

        int newLevel = Integer.parseInt(args.get(0));

        if (newLevel > 90) {

            sender.dropMessage(ShadowUtils.Colour("`rLevel cannot be above 90."));

            return;

        }

        if (targetPlayer != null) targetPlayer.getTeamManager().getCurrentAvatarEntity();
        else {

            avatar = sender.getTeamManager().getCurrentAvatarEntity();
            targetSelf = true;

        }

        if (avatar == null) return;

        avatar.getAvatar().setLevel(Integer.parseInt(args.get(0)));
        avatar.getAvatar().save();

        Player player = targetSelf ? sender : targetPlayer;
        Position pos = player.getPosition();
        int scene = player.getSceneId();

        player.getWorld().transferPlayerToScene(player, 1, pos);
        player.getWorld().transferPlayerToScene(player, scene, pos);
        player.getScene().broadcastPacket(new PacketSceneEntityAppearNotify(player));

        player.dropMessage(ShadowUtils.Colour("`gSuccessfully set " + avatar.getAvatar().getAvatarData().getName() + "'s level to " + args.get(0) + "."));


    }

}
