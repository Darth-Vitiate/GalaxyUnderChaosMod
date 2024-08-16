package server.galaxyunderchaos.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AncientForceStone extends Block {
    private static final Logger LOGGER = LogManager.getLogger();

    public AncientForceStone() {
        super(BlockBehaviour.Properties.of()
                .strength(3.0f, 10.0f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE)
                .pushReaction(PushReaction.NORMAL));
    }
}