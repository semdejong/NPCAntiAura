package com.Semdej.NPCAntiAura.Holders;

import org.apache.logging.log4j.core.config.plugins.ResolverUtil;

import java.util.HashMap;
import java.util.UUID;

public class hashMapHolder {

    public HashMap<UUID, UUID> playerBot;
    public HashMap<UUID, Integer> hitBot;

    public void loadHashMaps(){

        playerBot = new HashMap<>();
        hitBot = new HashMap<>();

    }

}
