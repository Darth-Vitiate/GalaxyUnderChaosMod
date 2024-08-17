package server.galaxyunderchaos.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class CrystalBlock extends Block {
    public CrystalBlock() {
        super(Properties.ofFullCopy(Blocks.STONE)
                .mapColor(MapColor.STONE)
                .strength(3.0f, 10.0f)
                .requiresCorrectToolForDrops()
                .pushReaction(PushReaction.NORMAL));
    }
}