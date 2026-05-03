package me.drex.ppb.entity;

import eu.pb4.polymer.core.api.entity.PolymerEntity;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;
import net.minecraft.world.entity.EntityType;

public record PolyBaseEntity(EntityType<?> entityType) implements PolymerEntity {

    public static PolyBaseEntity createBoat(String path) {
        boolean raft = path.contains("_raft");
        boolean chest = path.contains("_chest");
        EntityType<?> boatEntityType;
        if (raft && chest) {
            boatEntityType = EntityType.BAMBOO_CHEST_RAFT;
        } else if (raft) {
            boatEntityType = EntityType.BAMBOO_RAFT;
        } else if (chest) {
            boatEntityType = EntityType.BIRCH_CHEST_BOAT;
        } else {
            boatEntityType = EntityType.BIRCH_BOAT;
        }
        return new PolyBaseEntity(boatEntityType);
    }
    @Override
    public EntityType<?> getPolymerEntityType(PacketContext packetContext) {
        return entityType;
    }
}
