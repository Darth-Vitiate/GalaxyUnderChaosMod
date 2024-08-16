package server.galaxyunderchaos.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class AncientForceStoneCracked extends Block {
    public AncientForceStoneCracked() {
        super(Properties.of()
                .mapColor(MapColor.STONE)
                .strength(3.0f, 10.0f)
                .requiresCorrectToolForDrops()
                .pushReaction(PushReaction.NORMAL));
    }
}