package uk.co.tmdavies.genshinplugin.enums;

public enum ArtifactSubStat {

    FLAT_HP(298.75),
    FLAT_ATTACK(19.45),
    FLAT_DEFENCE(23.15),

    PERCENTAGE_HP(5.83),
    PERCENTAGE_ATTACK(5.83),
    PERCENTAGE_DEFENCE(7.29),

    ELEMENTAL_MASTERY(23.31),
    ENERGY_RECHARGE(6.48),

    PERCENTAGE_CRITICAL_RATE(3.89),
    PERCENTAGE_CRITICAL_DAMAGE(7.77);

    private final double highestValue;
    private final double maxValue;

    ArtifactSubStat(double highestValue) {

        this.highestValue = highestValue;
        this.maxValue = highestValue*5;

    }

    public double getMaxValue() {

        return maxValue;

    }
}
