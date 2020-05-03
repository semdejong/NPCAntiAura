package com.Semdej.NPCAntiAura.Handlers;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

import static com.comphenix.protocol.PacketType.Play.Server.*;

public class entityHider implements Listener {
    protected Table<Integer, Integer, Boolean> observerEntityMap = HashBasedTable.create();

    // Packets that update remote player entities
    private static final PacketType[] ENTITY_PACKETS = {
            ENTITY_EQUIPMENT, BED, ANIMATION, NAMED_ENTITY_SPAWN,
            COLLECT, SPAWN_ENTITY, SPAWN_ENTITY_LIVING, SPAWN_ENTITY_PAINTING, SPAWN_ENTITY_EXPERIENCE_ORB,
            ENTITY_VELOCITY, REL_ENTITY_MOVE, ENTITY_LOOK, ENTITY_MOVE_LOOK, ENTITY_MOVE_LOOK,
            ENTITY_TELEPORT, ENTITY_HEAD_ROTATION, ENTITY_STATUS, ATTACH_ENTITY, ENTITY_METADATA,
            ENTITY_EFFECT, REMOVE_ENTITY_EFFECT, BLOCK_BREAK_ANIMATION

            // We don't handle DESTROY_ENTITY though
    };

    public enum Policy {
        WHITELIST,

        BLACKLIST,
    }

    private ProtocolManager manager;

    // Listeners
    private Listener bukkitListener;
    private PacketAdapter protocolListener;

    // Current policy
    protected final Policy policy;
    public entityHider(Plugin plugin, Policy policy) {
        Preconditions.checkNotNull(plugin, "plugin cannot be NULL.");

        // Save policy
        this.policy = policy;
        this.manager = ProtocolLibrary.getProtocolManager();

        // Register events and packet listener
        plugin.getServer().getPluginManager().registerEvents(
                bukkitListener = constructBukkit(), plugin);
        manager.addPacketListener(
                protocolListener = constructProtocol(plugin));
    }


    protected boolean setVisibility(Player observer, int entityID, boolean visible) {
        switch (policy) {
            case BLACKLIST:
                // Non-membership means they are visible
                return !setMembership(observer, entityID, !visible);
            case WHITELIST:
                return setMembership(observer, entityID, visible);
            default :
                throw new IllegalArgumentException("Unknown policy: " + policy);
        }
    }

    protected boolean setMembership(Player observer, int entityID, boolean member) {
        if (member) {
            return observerEntityMap.put(observer.getEntityId(), entityID, true) != null;
        } else {
            return observerEntityMap.remove(observer.getEntityId(), entityID) != null;
        }
    }

    protected boolean getMembership(Player observer, int entityID) {
        return observerEntityMap.contains(observer.getEntityId(), entityID);
    }

    protected boolean isVisible(Player observer, int entityID) {
        // If we are using a whitelist, presence means visibility - if not, the opposite is the case
        boolean presence = getMembership(observer, entityID);

        return policy == Policy.WHITELIST ? presence : !presence;
    }

    protected void removeEntity(Entity entity, boolean destroyed) {
        int entityID = entity.getEntityId();

        for (Map<Integer, Boolean> maps : observerEntityMap.rowMap().values()) {
            maps.remove(entityID);
        }
    }

    protected void removePlayer(Player player) {
        // Cleanup
        observerEntityMap.rowMap().remove(player.getEntityId());
    }

    private Listener constructBukkit() {
        return new Listener() {
            @EventHandler
            public void onEntityDeath(EntityDeathEvent e) {
                removeEntity(e.getEntity(), true);
            }

            @EventHandler
            public void onChunkUnload(ChunkUnloadEvent e) {
                for (Entity entity : e.getChunk().getEntities()) {
                    removeEntity(entity, false);
                }
            }

            @EventHandler
            public void onPlayerQuit(PlayerQuitEvent e) {
                removePlayer(e.getPlayer());
            }
        };
    }

    private PacketAdapter constructProtocol(Plugin plugin) {
        return new PacketAdapter(plugin, ENTITY_PACKETS) {
            @Override
            public void onPacketSending(PacketEvent event) {
                int entityID = event.getPacket().getIntegers().read(0);

                // See if this packet should be cancelled
                if (!isVisible(event.getPlayer(), entityID)) {
                    event.setCancelled(true);
                }
            }
        };
    }

    public final boolean toggleEntity(Player observer, Entity entity) {
        if (isVisible(observer, entity.getEntityId())) {
            return hideEntity(observer, entity);
        } else {
            return !showEntity(observer, entity);
        }
    }

    public final boolean showEntity(Player observer, Entity entity) {
        validate(observer, entity);
        boolean hiddenBefore = !setVisibility(observer, entity.getEntityId(), true);

        // Resend packets
        if (manager != null && hiddenBefore) {
            manager.updateEntity(entity, Arrays.asList(observer));
        }
        return hiddenBefore;
    }

    public final boolean hideEntity(Player observer, Entity entity) {
        validate(observer, entity);
        boolean visibleBefore = setVisibility(observer, entity.getEntityId(), false);

        if (visibleBefore) {
            PacketContainer destroyEntity = new PacketContainer(ENTITY_DESTROY);
            destroyEntity.getIntegerArrays().write(0, new int[] { entity.getEntityId() });

            // Make the entity disappear
            try {
                manager.sendServerPacket(observer, destroyEntity);
            } catch (InvocationTargetException e) {
                throw new RuntimeException("Cannot send server packet.", e);
            }
        }
        return visibleBefore;
    }

    public final boolean canSee(Player observer, Entity entity) {
        validate(observer, entity);

        return isVisible(observer, entity.getEntityId());
    }

    // For valdiating the input parameters
    private void validate(Player observer, Entity entity) {
        Preconditions.checkNotNull(observer, "observer cannot be NULL.");
        Preconditions.checkNotNull(entity, "entity cannot be NULL.");
    }

    public Policy getPolicy() {
        return policy;
    }

    public void close() {
        if (manager != null) {
            HandlerList.unregisterAll(bukkitListener);
            manager.removePacketListener(protocolListener);
            manager = null;
        }
    }
}