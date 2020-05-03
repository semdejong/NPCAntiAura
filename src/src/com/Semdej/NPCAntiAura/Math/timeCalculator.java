package com.Semdej.NPCAntiAura.Math;

public class timeCalculator {

    public double seconds(long speed, double time){

        double speedToFraction = speed / 20;

        double amountOfTurns = time / speedToFraction;

        return amountOfTurns;

    }

}
