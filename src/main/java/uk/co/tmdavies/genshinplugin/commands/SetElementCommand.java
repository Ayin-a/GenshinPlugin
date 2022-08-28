package uk.co.tmdavies.genshinplugin.commands;

import emu.grasscutter.GameConstants;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.data.GameData;
import emu.grasscutter.data.excels.AvatarSkillDepotData;
import emu.grasscutter.game.entity.EntityAvatar;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.server.packet.send.PacketSceneEntityAppearNotify;
import emu.grasscutter.utils.Position;
import uk.co.tmdavies.genshinplugin.GenshinPlugin;
import uk.co.tmdavies.genshinplugin.enums.Element;
import uk.co.tmdavies.genshinplugin.utils.ShadowUtils;

import java.util.List;

@Command(label = "setelement", usage = "<element>", permission = "genshinplugin.setelement")
public class SetElementCommand implements CommandHandler {

    public SetElementCommand(GenshinPlugin plugin) {

        plugin.getHandle().registerCommand(this);

    }

    @Override
    public void execute(Player sender, Player targetPlayer, List<String> args) {

        if (args.size() != 1) {

            sender.dropMessage(ShadowUtils.Colour("`rUsage: /setelement [<@UID>] <element>"));

            return;

        }

        Player player = sender == null ? targetPlayer : sender;
        EntityAvatar avatar = player.getTeamManager().getCurrentAvatarEntity();

        if (!ShadowUtils.isTraveller(avatar.getAvatar())) {

            sender.dropMessage(ShadowUtils.Colour("`rTarget needs to have traveller active."));

            return;

        }

        boolean isMale = avatar.getAvatar().getAvatarId() == GameConstants.MAIN_CHARACTER_MALE;
        Element element = Element.getFromString(args.get(0));

        if (element == null) {

            sender.dropMessage(ShadowUtils.Colour("`rInvalid Element."));

            return;

        }

        // Changing
        AvatarSkillDepotData skillDepotData = GameData.getAvatarSkillDepotDataMap().get(isMale ? element.getMaleId() : element.getFemaleId());

        if (skillDepotData == null) {

            sender.dropMessage(ShadowUtils.Colour("`rUnknown Error with SkillDepotData."));

            return;

        }

        avatar.getAvatar().setSkillDepotData(skillDepotData);
        avatar.getAvatar().save();

        ShadowUtils.setTravellerConstellations(player, element, 6);

        Position pos = player.getPosition();
        int scene = player.getSceneId();

        player.getWorld().transferPlayerToScene(player, 1, pos);
        player.getWorld().transferPlayerToScene(player, scene, pos);
        player.getScene().broadcastPacket(new PacketSceneEntityAppearNotify(player));

        player.dropMessage(ShadowUtils.Colour("`gSuccessfully set "
                        + avatar.getAvatar().getData().getName()
                        + "'s element to " + element.getNames().get(0)));

        if (player != sender) {

            sender.dropMessage(ShadowUtils.Colour("`gSuccessfully set "
                    + player.getNickname() + "'s "
                    + avatar.getAvatar().getAvatarData().getName()
                    + "'s element to " + element.getNames().get(0)));

        }

    }

}
