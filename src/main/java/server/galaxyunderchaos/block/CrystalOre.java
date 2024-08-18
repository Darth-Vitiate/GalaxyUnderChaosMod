package server.galaxyunderchaos.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.PushReaction;

public class CrystalOre extends Block {
    public CrystalOre() {
        super(Properties.of()
                .strength(4.0f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.AMETHYST)
                .lightLevel((state) -> 12)
                .pushReaction(PushReaction.NORMAL));
    }
}
