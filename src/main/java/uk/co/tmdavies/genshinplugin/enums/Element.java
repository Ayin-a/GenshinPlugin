package uk.co.tmdavies.genshinplugin.enums;

import java.util.List;

public enum Element {

    PYRO(502, 702, "Pyro", "Fire"),
    HYDRO(503, 703, "Hydro", "Water"),
    ELECTRO(507, 707, "Electro", "Electric"),
    CRYO(505, 705, "Cryo", "Ice"),
    DENDRO(508, 708, "Dendro", "Life", "Leaf"),
    ANEMO(504, 704, "Anemo", "Wind"),
    GEO(506, 706, "Geo", "Rock"),
    PHYSICAL(501, 701, "Physical"),
    ELEMENTLESS(501, 701,"Elementless", "None");

    int maleId;
    int femaleId;
    List<String> names;

    Element(int maleId, int femaleId, String... names) {

        this.maleId = maleId;
        this.femaleId = femaleId;
        this.names = List.of(names);

    }

    public List<String> getNames() {

        return names;

    }

    public int getMaleId() {

        return maleId;

    }

    public int getFemaleId() {

        return femaleId;

    }

    public static Element getFromString(String string) {

        for (Element elements : Element.values()) {

            if (elements.getNames().contains(string)) return elements;

        }

        return null;

    }

}
