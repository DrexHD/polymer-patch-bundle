package me.drex.ppb.entity;

import eu.pb4.polymer.core.api.entity.PolymerEntity;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;

public record PolyBaseEntity(EntityType<?> entityType) implements PolymerEntity {

    public static PolyBaseEntity createBoat(String path) {
        boolean raft = path.contains("_raft");
        boolean chest = path.contains("_chest");
        EntityType<?> boatEntityType;
        if (raft && chest) {
            boatEntityType = EntityTypes.BAMBOO_CHEST_RAFT;
        } else if (raft) {
            boatEntityType = EntityTypes.BAMBOO_RAFT;
        } else if (chest) {
            boatEntityType = EntityTypes.BIRCH_CHEST_BOAT;
        } else {
            boatEntityType = EntityTypes.BIRCH_BOAT;
        }
        return new PolyBaseEntity(boatEntityType);
    }
    @Override
    public EntityType<?> getPolymerEntityType(PacketContext packetContext) {
        return entityType;
    }
}
