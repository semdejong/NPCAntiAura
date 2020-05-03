package com.Semdej.NPCAntiAura.Math;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class circularCoordinatesMath {

    public double X (double radial, double radius, boolean ccw){

        double returning = 0;

        if(ccw)
            returning = -Math.cos(radial)*radius;

        returning = Math.cos(radial)*radius;

        return returning;
    }

    public double Y (double radial, double radius, boolean ccw){
        double returning = Math.sin(radial)*radius;
        return returning;
    }



}
