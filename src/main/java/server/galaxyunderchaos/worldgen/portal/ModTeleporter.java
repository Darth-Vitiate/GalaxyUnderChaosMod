package server.galaxyunderchaos.worldgen.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import server.galaxyunderchaos.galaxyunderchaos;
import server.galaxyunderchaos.worldgen.dimension.ModDimensions;

import java.util.function.Function;

public class ModTeleporter {
    private final BlockPos thisPos;
    private final boolean insideDimension;

    public ModTeleporter(BlockPos pos, boolean insideDim) {
        this.thisPos = pos;
        this.insideDimension = insideDim;
    }

    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destinationWorld, float yaw, Function<Entity, Entity> repositionEntity) {
        entity = repositionEntity.apply(entity);
        int y = 61;

        if (!insideDimension) {
            y = thisPos.getY();
        }

        BlockPos destinationPos = new BlockPos(thisPos.getX(), y, thisPos.getZ());

        int tries = 0;
        while ((destinationWorld.getBlockState(destinationPos).getBlock() != Blocks.AIR ||
                !destinationWorld.getBlockState(destinationPos).canBeReplaced(Fluids.WATER)) &&
                (destinationWorld.getBlockState(destinationPos.above()).getBlock() != Blocks.AIR ||
                        !destinationWorld.getBlockState(destinationPos.above()).canBeReplaced(Fluids.WATER)) && tries < 25) {
            destinationPos = destinationPos.above(2);
            tries++;
        }

        entity.setPos(destinationPos.getX() + 0.5, destinationPos.getY(), destinationPos.getZ() + 0.5);
        entity.setDeltaMovement(Vec3.ZERO);
        entity.setYRot(yaw);

        if (insideDimension) {
            if (destinationWorld.dimension() == ModDimensions.TYTHON_LEVEL_KEY) {
                entity.spawnAtLocation(galaxyunderchaos.TYTHON_PORTAL_ITEM.get().getDefaultInstance());
            } else if (destinationWorld.dimension() == ModDimensions.NABOO_LEVEL_KEY) {
                entity.spawnAtLocation(galaxyunderchaos.NABOO_PORTAL_ITEM.get().getDefaultInstance());
            } else if (destinationWorld.dimension() == ModDimensions.ILUM_LEVEL_KEY) {
                entity.spawnAtLocation(galaxyunderchaos.ILUM_PORTAL_ITEM.get().getDefaultInstance());
            } else if (destinationWorld.dimension() == ModDimensions.MUSTAFAR_LEVEL_KEY) {
                entity.spawnAtLocation(galaxyunderchaos.MUSTAFAR_PORTAL_ITEM.get().getDefaultInstance());
            }
        }

        return entity;
    }
}