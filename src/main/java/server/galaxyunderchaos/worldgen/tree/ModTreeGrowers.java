package server.galaxyunderchaos.worldgen.tree;

import server.galaxyunderchaos.galaxyunderchaos;
import server.galaxyunderchaos.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;
import java.util.Optional;
public class ModTreeGrowers {
    public static final TreeGrower BLBA = new TreeGrower(galaxyunderchaos.MODID + ":blba",
            Optional.empty(), Optional.of(ModConfiguredFeatures.BLBA_KEY), Optional.empty());
}