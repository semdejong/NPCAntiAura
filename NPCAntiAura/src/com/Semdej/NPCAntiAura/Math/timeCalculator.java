package com.Semdej.NPCAntiAura.Math;

import org.bukkit.entity.Player;

public class timeCalculator {

    public double seconds(long speed, double time){

        double speedToFraction = speed / 20f;

        double amountOfTurns = time / speedToFraction;

        return amountOfTurns;

    }

}
