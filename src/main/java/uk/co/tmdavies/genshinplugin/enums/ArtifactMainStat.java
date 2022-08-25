package uk.co.tmdavies.genshinplugin.enums;

public enum ArtifactMainStat {

    /* Flower */
    FLAT_HP(4780),

    /* Feather */
    FLAT_ATTACK(311),

    /* Sands */
    PERCENTAGE_ENERGY_RECHARGE(51.8),

    /* Goblet */
    PERCENTAGE_PYRO_DAMAGE_BONUS(46.6),
    PERCENTAGE_HYDRO_DAMAGE_BONUS(46.6),
    PERCENTAGE_ELECTRO_DAMAGE_BONUS(46.6),
    PERCENTAGE_CRYO_DAMAGE_BONUS(46.6),
    PERCENTAGE_DENDRO_DAMAGE_BONUS(46.6),
    PERCENTAGE_ANEMO_DAMAGE_BONUS(46.6),
    PERCENTAGE_GEO_DAMAGE_BONUS(46.6),
    PERCENTAGE_PHYSICAL_DAMAGE_BONUS(58.3),

    /* Circlet */
    PERCENTAGE_CRITICAL_RATE(31.1),
    PERCENTAGE_CRITICAL_DAMAGE(62.2),
    PERCENTAGE_HEALING_BONUS(35.9),

    /* Universal */
    PERCENTAGE_HP(46.6),
    PERCENTAGE_ATTACK(46.6),
    PERCENTAGE_DEFENCE(58.3),
    FLAT_ELEMENTAL_MASTERY(186.5);

    private final double maxValue;

    ArtifactMainStat(double maxValue) {

        this.maxValue = maxValue;

    }

    public double getMaxValue() {

        return maxValue;

    }
}
