package server.galaxyunderchaos.worldgen.tree;

import server.galaxyunderchaos.galaxyunderchaos;
import server.galaxyunderchaos.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;
import java.util.Optional;
public class ModTreeGrowers {
    public static final TreeGrower BLBA = new TreeGrower(galaxyunderchaos.MODID + ":blba",
            Optional.empty(), Optional.of(ModConfiguredFeatures.BLBA_KEY), Optional.empty());
    public static final TreeGrower AK_TREE = new TreeGrower(galaxyunderchaos.MODID + ":ak_tree",
            Optional.empty(), Optional.of(ModConfiguredFeatures.AK_TREE_KEY), Optional.empty());
    public static final TreeGrower HEART_BERRY_TREE = new TreeGrower(galaxyunderchaos.MODID + ":heart_berry_tree",
            Optional.empty(), Optional.of(ModConfiguredFeatures.HEART_BERRY_KEY), Optional.empty());
}