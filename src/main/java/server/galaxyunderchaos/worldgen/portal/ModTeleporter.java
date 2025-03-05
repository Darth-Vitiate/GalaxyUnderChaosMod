package server.galaxyunderchaos.worldgen.portal;

import client.renderer.HyperspaceAnimation;
import client.renderer.HyperspaceManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import server.galaxyunderchaos.galaxyunderchaos;
import server.galaxyunderchaos.worldgen.dimension.ModDimensions;

import java.util.Random;
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

        int safeY = findSafeLandingY(destinationWorld, thisPos.getX(), thisPos.getZ());
        BlockPos destinationPos = new BlockPos(thisPos.getX(), safeY, thisPos.getZ());

        int tries = 0;
        while ((destinationWorld.getBlockState(destinationPos).getBlock() != Blocks.AIR ||
                !destinationWorld.getBlockState(destinationPos).canBeReplaced(Fluids.WATER)) &&
                (destinationWorld.getBlockState(destinationPos.above()).getBlock() != Blocks.AIR ||
                        !destinationWorld.getBlockState(destinationPos.above()).canBeReplaced(Fluids.WATER)) && tries < 25) {
            destinationPos = destinationPos.above(2);
            tries++;
        }

        // Start the hyperspace cutscene
        HyperspaceManager.startHyperspace(entity, destinationWorld, destinationPos, yaw);

        return entity;
    }

    private int findSafeLandingY(ServerLevel world, int x, int z) {
        int y = world.getHeight() - 1;
        while (y > 60) {
            BlockPos pos = new BlockPos(x, y, z);
            if (world.getBlockState(pos).isAir() &&
                    world.getBlockState(pos.below()).isSolid() &&
                    !world.getBlockState(pos.below()).is(Blocks.LAVA) &&
                    !world.getBlockState(pos.below()).is(Blocks.WATER)) {
                return y;
            }
            y--;
        }
        return 75; // Raise the fallback landing height
    }


    private void spawnPortalItem(Entity entity, ServerLevel world) {
        if (world.dimension() == ModDimensions.TYTHON_LEVEL_KEY) {
            entity.spawnAtLocation(galaxyunderchaos.TYTHON_PORTAL_ITEM.get().getDefaultInstance());
        } else if (world.dimension() == ModDimensions.NABOO_LEVEL_KEY) {
            entity.spawnAtLocation(galaxyunderchaos.NABOO_PORTAL_ITEM.get().getDefaultInstance());
        } else if (world.dimension() == ModDimensions.ILUM_LEVEL_KEY) {
            entity.spawnAtLocation(galaxyunderchaos.ILUM_PORTAL_ITEM.get().getDefaultInstance());
        } else if (world.dimension() == ModDimensions.MUSTAFAR_LEVEL_KEY) {
            entity.spawnAtLocation(galaxyunderchaos.MUSTAFAR_PORTAL_ITEM.get().getDefaultInstance());
        } else if (world.dimension() == ModDimensions.DANTOOINE_LEVEL_KEY) {
            entity.spawnAtLocation(galaxyunderchaos.DANTOOINE_PORTAL_ITEM.get().getDefaultInstance());
        } else if (world.dimension() == ModDimensions.OSSUS_LEVEL_KEY) {
            entity.spawnAtLocation(galaxyunderchaos.OSSUS_PORTAL_ITEM.get().getDefaultInstance());
        } else if (world.dimension() == ModDimensions.MALACHOR_LEVEL_KEY) {
            entity.spawnAtLocation(galaxyunderchaos.MALACHOR_PORTAL_ITEM.get().getDefaultInstance());
        } else if (world.dimension() == ModDimensions.KORRIBAN_LEVEL_KEY) {
            entity.spawnAtLocation(galaxyunderchaos.KORRIBAN_PORTAL_ITEM.get().getDefaultInstance());
        }
    }
}
