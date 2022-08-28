package uk.co.tmdavies.genshinplugin.utils;

import emu.grasscutter.GameConstants;
import emu.grasscutter.Grasscutter;
import emu.grasscutter.game.avatar.Avatar;
import emu.grasscutter.game.entity.EntityAvatar;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.server.packet.send.PacketSceneEntityAppearNotify;
import emu.grasscutter.utils.Position;
import uk.co.tmdavies.genshinplugin.GenshinPlugin;
import uk.co.tmdavies.genshinplugin.enums.Colour;
import uk.co.tmdavies.genshinplugin.enums.Element;
import uk.co.tmdavies.genshinplugin.objects.PluginConfig;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ShadowUtils {

    public static String Colour(String message) {

        boolean hasHappenedBefore = false;
        StringBuilder builder = new StringBuilder();
        HashMap<String, String> colourCodes = new HashMap<>();
        List<String> tempCodesToRemove = new ArrayList<>();

        for (Colour colour : Colour.values()) colourCodes.put(colour.getColourCode(), colour.getHexCode());

        char[] characters = message.toCharArray();

        for (int i = 0; i < characters.length; i++) {

            char character = characters[i];

            if (character != Colour.SYMBOL.getColourCode().toCharArray()[0]) {

                builder.append(character);

                if (i == (characters.length-1) && hasHappenedBefore) builder.append("</color>");

                continue;

            }

            StringBuilder temp = new StringBuilder();

            for (int o = (i+1); o < (i+3); i++) {

                temp.append(characters[o]);

                if (colourCodes.containsKey(temp.toString())) break;

            }

            tempCodesToRemove.add(temp.toString());

            if (temp.toString().equals(Colour.RESET.getColourCode())) {

                if (!hasHappenedBefore) continue;

                builder.append("</color>");

                continue;

            }

            String hex = colourCodes.get(temp.toString());

            if (hasHappenedBefore) builder.append("</color>");

            builder.append("<color=").append(hex).append(">");

            hasHappenedBefore = true;

            builder.append(character);

            /* No idea why this works but it fixes some issues */
            i++;

        }

        String toReturn = builder.toString();

        for (String string : tempCodesToRemove) toReturn = toReturn.replace(Colour.SYMBOL.getColourCode().toCharArray()[0] + string, "");

        return toReturn.replace(Colour.SYMBOL.getColourCode().toCharArray()[0]+"", "");

    }

    public static void refreshScene(Player player) {

        Position pos = player.getPosition();
        int scene = player.getSceneId();

        player.getWorld().transferPlayerToScene(player, 1, pos);
        player.getWorld().transferPlayerToScene(player, scene, pos);
        player.getScene().broadcastPacket(new PacketSceneEntityAppearNotify(player));

    }

    public static boolean isTraveller(Avatar avatar) {

        int id = avatar.getAvatarId();

        return id == GameConstants.MAIN_CHARACTER_MALE || id == GameConstants.MAIN_CHARACTER_FEMALE;

    }

    public static void setTravellerConstellations(Player player, Element element, int constellationsAmount) {

        Integer[] anemoConstellations = {71,72,73,74,75,76};
        Integer[] geoConstellations = {91,92,93,94,95,96};
        Integer[] electroConstellations = {101,102,103,104,105,106};
        Integer[] dendroConstellations = {111,112,113,114,115,116};
        Integer[] constellations = {};

        switch (element.getNames().get(0)) {

            case "Anemo" -> constellations = anemoConstellations;
            case "Geo" -> constellations = geoConstellations;
            case "Electro" -> constellations = electroConstellations;
            case "Dendro" -> constellations = dendroConstellations;

        }

        if (constellations.length == 0) return;

        try {

            Avatar avatar = player.getTeamManager().getCurrentAvatarEntity().getAvatar();
            avatar.getTalentIdList().clear();

            for (int i = 0; i < constellationsAmount; i++) {

                avatar.getTalentIdList().add(constellations[i]);

            }

            avatar.save();

        } catch (RuntimeException e) {

            player.dropMessage(ShadowUtils.Colour("`rError setting constellations for "
                    + player.getTeamManager().getCurrentAvatarEntity().getAvatar().getAvatarData().getName()
                    + "."));

            Grasscutter.getLogger().error("Error setting constellations for "
                    + player.getUid() + ". ShadowUtils#setTravellerConstellations Line: "
                    + Thread.currentThread().getStackTrace()[0].getLineNumber());

        }

    }

    public static PluginConfig initiateConfiguration(File file) {

        /* Code Snippet from Template */
        GenshinPlugin plugin = GenshinPlugin.getInstance();

        /* Load the configuration. */
        try {
            if(!file.exists() && !file.createNewFile()) {
                plugin.getLogger().error("Failed to create config file.");
            } else {
                try (FileWriter writer = new FileWriter(file)) {
                    InputStream configStream = plugin.getResource("config.json");
                    if(configStream == null) {
                        plugin.getLogger().error("Failed to save default config file.");
                    } else {
                        writer.write(new BufferedReader(
                                new InputStreamReader(configStream)).lines().collect(Collectors.joining("\n"))
                        ); writer.close();

                        plugin.getLogger().info("Saved default config file.");
                    }
                }
            }

            // Put the configuration into an instance of the config class.
            return Grasscutter.getGsonFactory().fromJson(new FileReader(file), PluginConfig.class);
        } catch (IOException exception) {
            plugin.getLogger().error("Failed to create config file.", exception);
        }

        return null;

    }

}
