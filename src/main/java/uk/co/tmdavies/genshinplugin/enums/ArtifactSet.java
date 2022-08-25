package uk.co.tmdavies.genshinplugin.enums;

public enum ArtifactSet {

    /* TODO: Fill out all IDs */
    /* ID ORDER: Flower, Feather, Sands, Goblet, Circlet. */

    /* 1★ */
    INITIATE("Initiate", 0),

    /* 1★-3★ */
    ADVENTURER("Adventurer", 0),
    LUCKY_DOG("Lucky Dog", 0),
    TRAVELLING_DOCTOR("Travelling Doctor", 0),

    /* 3★-4★ */
    RESOLUTION_OF_SOJOURNER("Resolution of Sojourner", 0),
    TINY_MIRACLE("Tiny Miracle", 0),
    BERSERKER("Berserker", 0),
    INSTRUCTOR("Instructor", 0),
    THE_EXILE("The Exile", 0),
    DEFENDERS_WILL("Defender's Will", 0),
    BRAVE_HEART("Brave Heart", 0),
    MARTIAL_ARTIST("Martial Artist", 0),
    GAMBLER("Gambler", 0),
    SCHOLAR("Scholar", 0),

    /* 3★-4★ Single Piece */
    PRAYERS_OF_WISDOM("Prayers of Wisdom", 0),
    PRAYERS_OF_DESTINY("Prayers of Destiny", 0),
    PRAYERS_OF_ILLUMINATION("Prayers of Illumination", 0),
    PRAYERS_OF_SPRINGTIME("Prayers of Springtime", 0),

    /* 4★-5★ */
    GLADIATORS_FINALE("Gladiator's Finale", 0),
    WANDERERS_TROUPE("Wanderer's Troupe", 0),
    NOBLESSE_OBLIGE("Noblesse Oblige", 0),
    BLOODSTAINED_CHIVALRY("Bloodstained Chivalry", 23344, 23342, 23345, 23341, 23343),
    MAIDEN_BELOVED("Maiden Beloved", 0),
    VIRIDESCENT_VENERER("Viridescent Venerer", 0),
    ARCHAIC_PETRA("Archaic Petra", 0),
    RETRACING_BOLIDE("Retracing Bolide", 0),
    THUNDERSOOTHER("Thundersoother", 0),
    THUNDERING_FURY("Thundering Fury", 0),
    CRIMSON_WITCH_OF_FLAMES("Crimson Witch of Flames", 0),
    BLIZZARD_STRAYER("Blizzard Strayer", 0),
    HEART_OF_DEPTH("Heart of Depth", 0),
    TENACITY_OF_THE_MILLELITH("Tenacity of the Millelith", 0),
    PALE_FLAME("Pale Flame", 0),
    SHIMENAWAS_REMINISCENCE("Shimenawa's Reminiscence", 0),
    EMBLEM_OF_SEVERED_FATE("Emblem of Severed Fate", 0),
    HUSK_OF_OPULENT_DREAMS("Husk of Opulent Dreams", 0),
    OCEAN_HUED_CLAM("Ocean-Hued Clam", 0),
    VERMILLION_HEREAFTER("Vermillion Hereafter", 0),
    ECHOES_OF_AN_OFFERING("Echoes of an Offering", 0),
    DEEPWOOD_MEMORIES("Deepwood Memories", 0),
    GILDED_DREAMS("Gilded Dreams", 0);

    private final String name;
    private final int[] ids;

    ArtifactSet(String name, int... ids) {

        this.name = name;
        this.ids = ids;

    }

    /* Grabs the Artifact Set Name */
    public String getName() {

        return this.name;

    }

    /* Grabs all the IDs for that set. */
    public int[] getIDs() {

        return this.ids;

    }

    /* For Single Artifact Sets. For easier use. */
    public int getID() {

        return this.ids[0];

    }

    /* Grabs the ID for the flower of the set. */
    public int getFlowerID() {

        return this.ids[0];

    }

    /* Grabs the ID for the feather of the set. */
    public int getFeatherID() {

        return this.ids[1];

    }

    /* Grabs the ID for the sands of the set. */
    public int getSandsID() {

        return this.ids[2];

    }

    /* Grabs the ID for the goblet of the set. */
    public int getGobletID() {

        return this.ids[3];

    }

    /* Grabs the ID for the circlet of the set. */
    public int getCircletID() {

        return this.ids[4];

    }

}
