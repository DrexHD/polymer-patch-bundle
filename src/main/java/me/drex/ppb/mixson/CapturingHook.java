package me.drex.ppb.mixson;

import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.ramixin.mixson.hooks.StandardHook;
import net.ramixin.mixson.util.Index;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CapturingHook extends StandardHook {

    public Map<Identifier, Resource> overrides = new  HashMap<>();

    public CapturingHook(Map<Identifier, Resource> attachedResources) {
        super(attachedResources);
    }

    @Override
    public void insert(@NotNull Index index, List<Resource> resources, String fileExt, boolean overwrite) {
        if(resources.isEmpty())
            throw new IllegalArgumentException("Cannot insert empty resource list");
        overrides.put(index.id().withSuffix(fileExt), resources.getFirst());
    }
}
