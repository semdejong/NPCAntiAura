package com.Semdej.NPCAntiAura.Math;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class circularCoordinatesMath {

    public double X (double radial, double radius, boolean ccw){

        if(ccw)
            return -Math.cos(radial)*radius;

        return Math.cos(radial)*radius;
    }

    public double Y (double radial, double radius, boolean ccw){
        return Math.sin(radial)*radius;
    }



}
