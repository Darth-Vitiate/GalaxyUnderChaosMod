package server.galaxyunderchaos.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.material.PushReaction;

public class DarkTempleStoneWall extends WallBlock {
    public DarkTempleStoneWall() {
        super(Properties.of()
                .strength(3.0f, 10.0f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE)
                .pushReaction(PushReaction.NORMAL));
    }
}