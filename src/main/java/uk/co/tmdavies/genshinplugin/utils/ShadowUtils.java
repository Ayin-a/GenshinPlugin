package uk.co.tmdavies.genshinplugin.utils;

import emu.grasscutter.Grasscutter;
import uk.co.tmdavies.genshinplugin.GenshinPlugin;
import uk.co.tmdavies.genshinplugin.enums.Colour;
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
