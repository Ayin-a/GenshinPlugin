package uk.co.tmdavies.genshinplugin.enums;

public enum Colour {

    /* Default Colours */
    RED("r", "#FF0000"),
    GREEN("g", "#00FF00"),
    BLUE("b", "#0000FF"),

    /* Elemental Colours */
    ELEMENTAL_PYRO("ep", "#F9A871"),
    ELEMENTAL_HYDRO("eh", "#14DCF6"),
    ELEMENTAL_ELECTRO("ee", "#D9B5FB"),
    ELEMENTAL_CRYO("ec", "#B0F2E4"),
    ELEMENTAL_DENDRO("ed", "#A0D44E"),
    ELEMENTAL_ANEMO("ea", "#A6EDCD"),
    ELEMENTAL_GEO("eg", "#DBC534"),
    ELEMENTAL_PHYSICAL("ep", "#FFFFFF"),

    /* Reset */
    RESET("!", "#FFFFFF"),

    /* Colour Symbol */
    SYMBOL("`", "");

    private final String colourCode;
    private final String hexCode;

    Colour(String colourCode, String hexCode) {

        this.colourCode = colourCode;
        this.hexCode = hexCode;

    }

    public String getColourCode() {

        return colourCode;

    }

    public String getHexCode() {

        return hexCode;

    }
}
