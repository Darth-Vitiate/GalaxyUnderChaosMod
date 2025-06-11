package server.galaxyunderchaos.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import server.galaxyunderchaos.entity.ModBlockEntities;
import server.galaxyunderchaos.entity.ModEntityTypes;

public class TreeWallHangingSign extends WallHangingSignBlock {

    public TreeWallHangingSign(WoodType woodType) {
        super(woodType,
                Properties.of()
                                .mapColor(MapColor.WOOD)
                                .strength(1.0F)
                                .noOcclusion());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.AK_HANGING_SIGN_BE.get().create(pos, state);
    }
}