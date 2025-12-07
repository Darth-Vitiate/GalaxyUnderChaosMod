package server.galaxyunderchaos.data;

import com.google.gson.JsonParser;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import server.galaxyunderchaos.galaxyunderchaos;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModHardcodedRecipeProvider implements DataProvider {
    private final PackOutput packOutput;
    private final Map<String, String> recipeJson = new LinkedHashMap<>();

    public ModHardcodedRecipeProvider(PackOutput packOutput) {
        this.packOutput = packOutput;
        populateRecipes();
    }

    private void populateRecipes() {
        put("acid_forged_plate_by_blasting_chitin_fragments", """
{
  "type": "minecraft:blasting",
  "category": "misc",
  "cookingtime": 100,
  "experience": 0.25,
  "group": "chitin_fragments",
  "ingredient": {
    "item": "galaxyunderchaos:chitin_fragments"
  },
  "result": {
    "id": "galaxyunderchaos:acid_forged_plate"
  }
}
""");
        put("acid_forged_plate_by_smelting_chitin_fragments", """
{
  "type": "minecraft:smelting",
  "category": "misc",
  "cookingtime": 200,
  "experience": 0.25,
  "group": "chitin_fragments",
  "ingredient": {
    "item": "galaxyunderchaos:chitin_fragments"
  },
  "result": {
    "id": "galaxyunderchaos:acid_forged_plate"
  }
}
""");
        put("ak_boat", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "group": "boat",
    "key": {
        "#": {
            "item": "galaxyunderchaos:ak_planks"
        }
    },
    "pattern": [
        "# #",
        "###"
    ],
    "result": {
        "count": 1,
        "id": "galaxyunderchaos:ak_boat"
    }
}
""");
        put("ak_button", """
{
    "type": "minecraft:crafting_shapeless",
    "category": "redstone",
    "group": "wooden_button",
    "ingredients": [
        {
            "item": "galaxyunderchaos:ak_planks"
        }
    ],
    "result": {
        "count": 1,
        "id": "galaxyunderchaos:ak_button"
    }
}
""");
        put("ak_chest_boat", """
{
    "type": "minecraft:crafting_shapeless",
    "category": "misc",
    "group": "chest_boat",
    "ingredients": [
        {
            "item": "minecraft:chest"
        },
        {
            "item": "galaxyunderchaos:ak_boat"
        }
    ],
    "result": {
        "count": 1,
        "id": "galaxyunderchaos:ak_chest_boat"
    }
}
""");
        put("ak_door", """
{
    "type": "minecraft:crafting_shaped",
    "category": "redstone",
    "group": "wooden_door",
    "key": {
        "#": {
            "item": "galaxyunderchaos:ak_planks"
        }
    },
    "pattern": [
        "##",
        "##",
        "##"
    ],
    "result": {
        "count": 3,
        "id": "galaxyunderchaos:ak_door"
    }
}
""");
        put("ak_fence", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "group": "wooden_fence",
    "key": {
        "#": {
            "item": "minecraft:stick"
        },
        "W": {
            "item": "galaxyunderchaos:ak_planks"
        }
    },
    "pattern": [
        "W#W",
        "W#W"
    ],
    "result": {
        "count": 3,
        "id": "galaxyunderchaos:ak_fence"
    }
}
""");
        put("ak_fence_gate", """
{
    "type": "minecraft:crafting_shaped",
    "category": "redstone",
    "group": "wooden_fence_gate",
    "key": {
        "#": {
            "item": "minecraft:stick"
        },
        "W": {
            "item": "galaxyunderchaos:ak_planks"
        }
    },
    "pattern": [
        "#W#",
        "#W#"
    ],
    "result": {
        "count": 1,
        "id": "galaxyunderchaos:ak_fence_gate"
    }
}
""");
        put("ak_hanging_sign", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "group": "hanging_sign",
    "key": {
        "#": {
            "item": "galaxyunderchaos:stripped_ak_log"
        },
        "X": {
            "item": "minecraft:chain"
        }
    },
    "pattern": [
        "X X",
        "###",
        "###"
    ],
    "result": {
        "count": 6,
        "id": "galaxyunderchaos:ak_hanging_sign"
    }
}
""");
        put("ak_planks", """
{
    "type": "minecraft:crafting_shapeless",
    "category": "building",
    "group": "planks",
    "ingredients": [
        {
            "tag": "galaxyunderchaos:ak_logs"
        }
    ],
    "result": {
        "count": 4,
        "id": "galaxyunderchaos:ak_planks"
    }
}
""");
        put("ak_pressure_plate", """
{
    "type": "minecraft:crafting_shaped",
    "category": "redstone",
    "group": "wooden_pressure_plate",
    "key": {
        "#": {
            "item": "galaxyunderchaos:ak_planks"
        }
    },
    "pattern": [
        "##"
    ],
    "result": {
        "count": 1,
        "id": "galaxyunderchaos:ak_pressure_plate"
    }
}
""");
        put("ak_sign", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "group": "wooden_sign",
    "key": {
        "#": {
            "item": "galaxyunderchaos:ak_planks"
        },
        "X": {
            "item": "minecraft:stick"
        }
    },
    "pattern": [
        "###",
        "###",
        " X "
    ],
    "result": {
        "count": 3,
        "id": "galaxyunderchaos:ak_sign"
    }
}
""");
        put("ak_slab", """
{
    "type": "minecraft:crafting_shaped",
    "category": "building",
    "group": "wooden_slab",
    "key": {
        "#": {
            "item": "galaxyunderchaos:ak_planks"
        }
    },
    "pattern": [
        "###"
    ],
    "result": {
        "count": 6,
        "id": "galaxyunderchaos:ak_slab"
    }
}
""");
        put("ak_stairs", """
{
    "type": "minecraft:crafting_shaped",
    "category": "building",
    "group": "wooden_stairs",
    "key": {
        "#": {
            "item": "galaxyunderchaos:ak_planks"
        }
    },
    "pattern": [
        "#  ",
        "## ",
        "###"
    ],
    "result": {
        "count": 4,
        "id": "galaxyunderchaos:ak_stairs"
    }
}
""");
        put("ak_trapdoor", """
{
    "type": "minecraft:crafting_shaped",
    "category": "redstone",
    "group": "wooden_trapdoor",
    "key": {
        "#": {
            "item": "galaxyunderchaos:ak_planks"
        }
    },
    "pattern": [
        "###",
        "###"
    ],
    "result": {
        "count": 2,
        "id": "galaxyunderchaos:ak_trapdoor"
    }
}
""");
        put("ancient_temple_stone_holobook", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "###",
        "GGG",
        "###"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:ancient_temple_stone"
        },
        "G": {
            "item": "galaxyunderchaos:ancient_holobook"
        }
    },
    "result": {
        "id": "galaxyunderchaos:ancient_temple_stone_holobook",
        "count": 1
    }
}
""");
        put("ancient_temple_stone_pillar", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "#",
        "#"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:ancient_temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:ancient_temple_stone_pillar",
        "count": 2
    },
    "group": "misc"
}
""");
        put("ancient_temple_stone_pillar_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:ancient_temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:ancient_temple_stone_pillar",
        "count": 1
    },
    "group": "misc"
}
""");
        put("ancient_temple_stone_slab", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "###"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:ancient_temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:ancient_temple_stone_slab",
        "count": 6
    },
    "group": "misc"
}
""");
        put("ancient_temple_stone_slab_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:ancient_temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:ancient_temple_stone_slab",
        "count": 2
    },
    "group": "misc"
}
""");
        put("ancient_temple_stone_stairs", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "  #",
        " ##",
        "###"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:ancient_temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:ancient_temple_stone_stairs",
        "count": 4
    },
    "group": "misc"
}
""");
        put("ancient_temple_stone_stairs_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:ancient_temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:ancient_temple_stone_stairs",
        "count": 1
    },
    "group": "misc"
}
""");
        put("ancient_temple_stone_wall", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "key": {
        "#": {
            "item": "galaxyunderchaos:ancient_temple_stone"
        }
    },
    "pattern": [
        "###",
        "###"
    ],
    "result": {
        "count": 6,
        "id": "galaxyunderchaos:ancient_temple_stone_wall"
    }
}
""");
        put("ashla_portal", """
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "hyperdrive",
  "key": {
    "#": {
      "item": "minecraft:calcite"
    },
    "X": {
      "item": "galaxyunderchaos:portal_item"
    }
  },
  "pattern": [
    "###",
    "#X#",
    "###"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:ashla_portal"
  }
}
""");
        put("ashla_temple_stone_pillar", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "#",
        "#"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:ashla_temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:ashla_temple_stone_pillar",
        "count": 2
    },
    "group": "misc"
}
""");
        put("ashla_temple_stone_pillar_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:ashla_temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:ashla_temple_stone_pillar",
        "count": 1
    },
    "group": "misc"
}
""");
        put("ashla_temple_stone_slab", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "###"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:ashla_temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:ashla_temple_stone_slab",
        "count": 6
    },
    "group": "misc"
}
""");
        put("ashla_temple_stone_slab_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:ashla_temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:ashla_temple_stone_slab",
        "count": 2
    },
    "group": "misc"
}
""");
        put("ashla_temple_stone_stairs", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "  #",
        " ##",
        "###"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:ashla_temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:ashla_temple_stone_stairs",
        "count": 4
    },
    "group": "misc"
}
""");
        put("ashla_temple_stone_stairs_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:ashla_temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:ashla_temple_stone_stairs",
        "count": 1
    },
    "group": "misc"
}
""");
        put("ashla_temple_stone_wall", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "key": {
        "#": {
            "item": "galaxyunderchaos:ashla_temple_stone"
        }
    },
    "pattern": [
        "###",
        "###"
    ],
    "result": {
        "count": 6,
        "id": "galaxyunderchaos:ashla_temple_stone_wall"
    }
}
""");
        put("bleeding_table", """
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "lightsaber",
  "key": {
    "#": {
      "item": "minecraft:gray_concrete"
    },
    "X": {
      "item": "galaxyunderchaos:sith_holobook"
    },
    "C": {
      "item": "minecraft:black_concrete"
    }
  },
  "pattern": [
    "CCC",
    "#X#",
    "#X#"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:bleeding_table"
  }
}
""");
        put("bogan_portal", """
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "hyperdrive",
  "key": {
    "#": {
      "item": "minecraft:blackstone"
    },
    "X": {
      "item": "galaxyunderchaos:portal_item"
    }
  },
  "pattern": [
    "###",
    "#X#",
    "###"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:bogan_portal"
  }
}
""");
        put("bogan_temple_stone_pillar", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "#",
        "#"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:bogan_temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:bogan_temple_stone_pillar",
        "count": 2
    },
    "group": "misc"
}
""");
        put("bogan_temple_stone_pillar_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:bogan_temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:bogan_temple_stone_pillar",
        "count": 1
    },
    "group": "misc"
}
""");
        put("bogan_temple_stone_slab", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "###"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:bogan_temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:bogan_temple_stone_slab",
        "count": 6
    },
    "group": "misc"
}
""");
        put("bogan_temple_stone_slab_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:bogan_temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:bogan_temple_stone_slab",
        "count": 2
    },
    "group": "misc"
}
""");
        put("bogan_temple_stone_stairs", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "  #",
        " ##",
        "###"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:bogan_temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:bogan_temple_stone_stairs",
        "count": 4
    },
    "group": "misc"
}
""");
        put("bogan_temple_stone_stairs_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:bogan_temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:bogan_temple_stone_stairs",
        "count": 1
    },
    "group": "misc"
}
""");
        put("bogan_temple_stone_wall", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "key": {
        "#": {
            "item": "galaxyunderchaos:bogan_temple_stone"
        }
    },
    "pattern": [
        "###",
        "###"
    ],
    "result": {
        "count": 6,
        "id": "galaxyunderchaos:bogan_temple_stone_wall"
    }
}
""");
        put("chiseled_tython_temple_stone_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:tython_temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:chiseled_tython_temple_stone",
        "count": 1
    },
    "group": "misc"
}
""");
        put("chromium_by_blasting_chromium_deepslate_ore", """
{
  "type": "minecraft:blasting",
  "category": "misc",
  "cookingtime": 100,
  "experience": 0.25,
  "group": "chromium",
  "ingredient": {
    "item": "galaxyunderchaos:chromium_deepslate_ore"
  },
  "result": {
    "id": "galaxyunderchaos:chromium_ingot"
  }
}
""");
        put("chromium_by_blasting_chromium_ore.json", """
{
  "type": "minecraft:blasting",
  "category": "misc",
  "cookingtime": 100,
  "experience": 0.25,
  "group": "chromium",
  "ingredient": {
    "item": "galaxyunderchaos:chromium_ore"
  },
  "result": {
    "id": "galaxyunderchaos:chromium_ingot"
  }
}
""");
        put("chromium_by_smelting_chromium_deepslate_ore", """
{
  "type": "minecraft:smelting",
  "category": "misc",
  "cookingtime": 200,
  "experience": 0.25,
  "group": "chromium",
  "ingredient": {
    "item": "galaxyunderchaos:chromium_deepslate_ore"
  },
  "result": {
    "id": "galaxyunderchaos:chromium_ingot"
  }
}
""");
        put("chromium_by_smelting_chromium_ore", """
{
  "type": "minecraft:smelting",
  "category": "misc",
  "cookingtime": 200,
  "experience": 0.25,
  "group": "chromium",
  "ingredient": {
    "item": "galaxyunderchaos:chromium_ore"
  },
  "result": {
    "id": "galaxyunderchaos:chromium_ingot"
  }
}
""");
        put("cooked_wingmaw_meat_by_blasting_raw_wingmaw_meat", """
{
  "type": "minecraft:blasting",
  "category": "misc",
  "cookingtime": 100,
  "experience": 0.25,
  "group": "raw_wingmaw_meat",
  "ingredient": {
    "item": "galaxyunderchaos:raw_wingmaw_meat"
  },
  "result": {
    "id": "galaxyunderchaos:cooked_wingmaw_meat"
  }
}
""");
        put("cooked_wingmaw_meat_by_smelting_raw_wingmaw_meats", """
{
  "type": "minecraft:smelting",
  "category": "misc",
  "cookingtime": 200,
  "experience": 0.25,
  "group": "raw_wingmaw_meat",
  "ingredient": {
    "item": "galaxyunderchaos:raw_wingmaw_meat"
  },
  "result": {
    "id": "galaxyunderchaos:cooked_wingmaw_meat"
  }
}
""");
        put("dantooine_portal", """
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "hyperdrive",
  "key": {
    "#": {
      "item": "minecraft:stripped_birch_log"
    },
    "X": {
      "item": "galaxyunderchaos:portal_item"
    }
  },
  "pattern": [
    "###",
    "#X#",
    "###"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:dantooine_portal"
  }
}
""");
        put("dark_temple_stone_pillar", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "#",
        "#"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:dark_temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:dark_temple_stone_pillar",
        "count": 2
    },
    "group": "misc"
}
""");
        put("dark_temple_stone_pillar_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:dark_temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:dark_temple_stone_pillar",
        "count": 1
    },
    "group": "misc"
}
""");
        put("dark_temple_stone_slab", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "###"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:dark_temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:dark_temple_stone_slab",
        "count": 6
    },
    "group": "misc"
}
""");
        put("dark_temple_stone_slab_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:dark_temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:dark_temple_stone_slab",
        "count": 2
    },
    "group": "misc"
}
""");
        put("dark_temple_stone_stairs", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "  #",
        " ##",
        "###"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:dark_temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:dark_temple_stone_stairs",
        "count": 4
    },
    "group": "misc"
}
""");
        put("dark_temple_stone_stairs_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:dark_temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:dark_temple_stone_stairs",
        "count": 1
    },
    "group": "misc"
}
""");
        put("dark_temple_stone_wall", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "key": {
        "#": {
            "item": "galaxyunderchaos:dark_temple_stone"
        }
    },
    "pattern": [
        "###",
        "###"
    ],
    "result": {
        "count": 6,
        "id": "galaxyunderchaos:dark_temple_stone_wall"
    }
}
""");
        put("heart_berry_boat", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "group": "boat",
    "key": {
        "#": {
            "item": "galaxyunderchaos:heart_berry_planks"
        }
    },
    "pattern": [
        "# #",
        "###"
    ],
    "result": {
        "count": 1,
        "id": "galaxyunderchaos:heart_berry_boat"
    }
}
""");
        put("heart_berry_button", """
{
    "type": "minecraft:crafting_shapeless",
    "category": "redstone",
    "group": "wooden_button",
    "ingredients": [
        {
            "item": "galaxyunderchaos:heart_berry_planks"
        }
    ],
    "result": {
        "count": 1,
        "id": "galaxyunderchaos:heart_berry_button"
    }
}
""");
        put("heart_berry_chest_boat", """
{
    "type": "minecraft:crafting_shapeless",
    "category": "misc",
    "group": "chest_boat",
    "ingredients": [
        {
            "item": "minecraft:chest"
        },
        {
            "item": "galaxyunderchaos:heart_berry_boat"
        }
    ],
    "result": {
        "count": 1,
        "id": "galaxyunderchaos:heart_berry_chest_boat"
    }
}
""");
        put("heart_berry_door", """
{
    "type": "minecraft:crafting_shaped",
    "category": "redstone",
    "group": "wooden_door",
    "key": {
        "#": {
            "item": "galaxyunderchaos:heart_berry_planks"
        }
    },
    "pattern": [
        "##",
        "##",
        "##"
    ],
    "result": {
        "count": 3,
        "id": "galaxyunderchaos:heart_berry_door"
    }
}
""");
        put("heart_berry_fence", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "group": "wooden_fence",
    "key": {
        "#": {
            "item": "minecraft:stick"
        },
        "W": {
            "item": "galaxyunderchaos:heart_berry_planks"
        }
    },
    "pattern": [
        "W#W",
        "W#W"
    ],
    "result": {
        "count": 3,
        "id": "galaxyunderchaos:heart_berry_fence"
    }
}
""");
        put("heart_berry_fence_gate", """
{
    "type": "minecraft:crafting_shaped",
    "category": "redstone",
    "group": "wooden_fence_gate",
    "key": {
        "#": {
            "item": "minecraft:stick"
        },
        "W": {
            "item": "galaxyunderchaos:heart_berry_planks"
        }
    },
    "pattern": [
        "#W#",
        "#W#"
    ],
    "result": {
        "count": 1,
        "id": "galaxyunderchaos:heart_berry_fence_gate"
    }
}
""");
        put("heart_berry_hanging_sign", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "group": "hanging_sign",
    "key": {
        "#": {
            "item": "galaxyunderchaos:stripped_heart_berry_log"
        },
        "X": {
            "item": "minecraft:chain"
        }
    },
    "pattern": [
        "X X",
        "###",
        "###"
    ],
    "result": {
        "count": 6,
        "id": "galaxyunderchaos:heart_berry_hanging_sign"
    }
}
""");
        put("heart_berry_planks", """
{
    "type": "minecraft:crafting_shapeless",
    "category": "building",
    "group": "planks",
    "ingredients": [
        {
            "tag": "galaxyunderchaos:heart_berry_logs"
        }
    ],
    "result": {
        "count": 4,
        "id": "galaxyunderchaos:heart_berry_planks"
    }
}
""");
        put("heart_berry_pressure_plate", """
{
    "type": "minecraft:crafting_shaped",
    "category": "redstone",
    "group": "wooden_pressure_plate",
    "key": {
        "#": {
            "item": "galaxyunderchaos:heart_berry_planks"
        }
    },
    "pattern": [
        "##"
    ],
    "result": {
        "count": 1,
        "id": "galaxyunderchaos:heart_berry_pressure_plate"
    }
}
""");
        put("heart_berry_sign", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "group": "wooden_sign",
    "key": {
        "#": {
            "item": "galaxyunderchaos:heart_berry_planks"
        },
        "X": {
            "item": "minecraft:stick"
        }
    },
    "pattern": [
        "###",
        "###",
        " X "
    ],
    "result": {
        "count": 3,
        "id": "galaxyunderchaos:heart_berry_sign"
    }
}
""");
        put("heart_berry_slab", """
{
    "type": "minecraft:crafting_shaped",
    "category": "building",
    "group": "wooden_slab",
    "key": {
        "#": {
            "item": "galaxyunderchaos:heart_berry_planks"
        }
    },
    "pattern": [
        "###"
    ],
    "result": {
        "count": 6,
        "id": "galaxyunderchaos:heart_berry_slab"
    }
}
""");
        put("heart_berry_stairs", """
{
    "type": "minecraft:crafting_shaped",
    "category": "building",
    "group": "wooden_stairs",
    "key": {
        "#": {
            "item": "galaxyunderchaos:heart_berry_planks"
        }
    },
    "pattern": [
        "#  ",
        "## ",
        "###"
    ],
    "result": {
        "count": 4,
        "id": "galaxyunderchaos:heart_berry_stairs"
    }
}
""");
        put("heart_berry_trapdoor", """
{
    "type": "minecraft:crafting_shaped",
    "category": "redstone",
    "group": "wooden_trapdoor",
    "key": {
        "#": {
            "item": "galaxyunderchaos:heart_berry_planks"
        }
    },
    "pattern": [
        "###",
        "###"
    ],
    "result": {
        "count": 2,
        "id": "galaxyunderchaos:heart_berry_trapdoor"
    }
}
""");
        put("ilum_portal", """
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "hyperdrive",
  "key": {
    "#": {
      "item": "minecraft:snowball"
    },
    "X": {
      "item": "galaxyunderchaos:portal_item"
    }
  },
  "pattern": [
    "###",
    "#X#",
    "###"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:ilum_portal"
  }
}
""");
        put("korriban_portal", """
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "hyperdrive",
  "key": {
    "#": {
      "item": "minecraft:red_sand"
    },
    "X": {
      "item": "galaxyunderchaos:portal_item"
    }
  },
  "pattern": [
    "###",
    "#X#",
    "###"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:korriban_portal"
  }
}
""");
        put("lightsaber_crafting_table", """
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "lightsaber",
  "key": {
    "#": {
      "item": "minecraft:gray_concrete"
    },
    "X": {
      "item": "galaxyunderchaos:ancient_holobook"
    },
    "C": {
      "item": "minecraft:light_gray_concrete"
    }
  },
  "pattern": [
    "CCC",
    "#X#",
    "#X#"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:lightsaber_crafting_table"
  }
}
""");
        put("malachor_portal", """
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "hyperdrive",
  "key": {
    "#": {
      "item": "minecraft:dead_bush"
    },
    "X": {
      "item": "galaxyunderchaos:portal_item"
    }
  },
  "pattern": [
    "###",
    "#X#",
    "###"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:malachor_portal"
  }
}
""");
        put("mustafar_portal", """
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "hyperdrive",
  "key": {
    "#": {
      "item": "minecraft:basalt"
    },
    "X": {
      "item": "galaxyunderchaos:portal_item"
    }
  },
  "pattern": [
    "###",
    "#X#",
    "###"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:mustafar_portal"
  }
}
""");
        put("naboo_portal", """
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "hyperdrive",
  "key": {
    "#": {
      "item": "galaxyunderchaos:shuura"
    },
    "X": {
      "item": "galaxyunderchaos:portal_item"
    }
  },
  "pattern": [
    "###",
    "#X#",
    "###"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:naboo_portal"
  }
}
""");
        put("navigation_computer", """
{
  "type": "minecraft:crafting_shaped",
  "group": "hyperdrive",
  "category": "equipment",
  "key": {
    "#": {
      "item": "minecraft:redstone"
    },
    "R": {
      "item": "minecraft:glass"
    },
    "C": {
      "item": "galaxyunderchaos:titanium_ingot"
    },
    "X": {
      "item": "minecraft:compass"
    }
  },
  "pattern": [
    "CCC",
    "#X#",
    "#R#"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:navigation_computer"
  }
}
""");
        put("ossus_portal", """
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "hyperdrive",
  "key": {
    "#": {
      "item": "minecraft:bamboo"
    },
    "X": {
      "item": "galaxyunderchaos:portal_item"
    }
  },
  "pattern": [
    "###",
    "#X#",
    "###"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:ossus_portal"
  }
}
""");
        put("portal_item", """
{
  "type": "minecraft:crafting_shaped",
  "pattern": [
    "TTT",
    "#X#",
    "#R#"
  ],
  "key": {
    "#": {
      "item": "galaxyunderchaos:titanium_chromium_ingot"
    },
    "R": {
      "item": "minecraft:netherite_ingot"
    },
    "T": {
      "item": "minecraft:diamond"
    },
    "X": {
      "item": "galaxyunderchaos:navigation_computer"
    }
  },
  "result": {
    "id": "galaxyunderchaos:portal_item",
    "count": 1
  }
}

""");
        put("temple_guard_boots", """
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "P": {
      "item": "minecraft:gold_ingot"
    },
    "S": {
      "item": "galaxyunderchaos:temple_guard_fabric"
    }
  },
  "pattern": [
    "S S",
    "P P"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:temple_guard_boots"
  }
}
""");
        put("temple_guard_chestplate", """
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "P": {
      "item": "minecraft:gold_ingot"
    },
    "S": {
      "item": "galaxyunderchaos:temple_guard_fabric"
    }
  },
  "pattern": [
    "P P",
    "SPS",
    "SSS"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:temple_guard_chestplate"
  }
}
""");
        put("temple_guard_helmet", """
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "P": {
      "item": "minecraft:gold_ingot"
    },
    "S": {
      "item": "galaxyunderchaos:temple_guard_fabric"
    }
  },
  "pattern": [
    "SSS",
    "P P"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:temple_guard_helmet"
  }
}
""");
        put("temple_guard_leggings", """
{
  "type": "minecraft:crafting_shaped",
  "category": "misc",
  "key": {
    "P": {
      "item": "minecraft:gold_ingot"
    },
    "S": {
      "item": "galaxyunderchaos:temple_guard_fabric"
    }
  },
  "pattern": [
    "SSS",
    "S S",
    "P P"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:temple_guard_leggings"
  }
}
""");
        put("temple_stone_holobook", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "###",
        "GGG",
        "###"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:temple_stone"
        },
        "G": {
            "item": "galaxyunderchaos:jedi_holobook"
        }
    },
    "result": {
        "id": "galaxyunderchaos:temple_stone_holobook",
        "count": 1
    }
}
""");
        put("temple_stone_pillar", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "#",
        "#"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:temple_stone_pillar",
        "count": 2
    },
    "group": "misc"
}
""");
        put("temple_stone_pillar_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:temple_stone_pillar",
        "count": 1
    },
    "group": "misc"
}
""");
        put("temple_stone_slab", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "###"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:temple_stone_slab",
        "count": 6
    },
    "group": "misc"
}
""");
        put("temple_stone_slab_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:temple_stone_slab",
        "count": 2
    },
    "group": "misc"
}
""");
        put("temple_stone_stairs", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "  #",
        " ##",
        "###"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:temple_stone_stairs",
        "count": 4
    },
    "group": "misc"
}
""");
        put("temple_stone_stairs_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:temple_stone_stairs",
        "count": 1
    },
    "group": "misc"
}
""");
        put("temple_stone_wall", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "key": {
        "#": {
            "item": "galaxyunderchaos:temple_stone"
        }
    },
    "pattern": [
        "###",
        "###"
    ],
    "result": {
        "count": 6,
        "id": "galaxyunderchaos:temple_stone_wall"
    }
}
""");
        put("titanium_by_blasting_titanium_deepslate_ore.json", """
{
  "type": "minecraft:blasting",
  "category": "misc",
  "cookingtime": 100,
  "experience": 0.25,
  "group": "titanium",
  "ingredient": {
    "item": "galaxyunderchaos:titanium_deepslate_ore"
  },
  "result": {
    "id": "galaxyunderchaos:titanium_ingot"
  }
}
""");
        put("titanium_by_blasting_titanium_ore", """
{
  "type": "minecraft:blasting",
  "category": "misc",
  "cookingtime": 100,
  "experience": 0.25,
  "group": "titanium",
  "ingredient": {
    "item": "galaxyunderchaos:titanium_ore"
  },
  "result": {
    "id": "galaxyunderchaos:titanium_ingot"
  }
}
""");
        put("titanium_by_smelting_titanium_deepslate_ore", """
{
  "type": "minecraft:smelting",
  "category": "misc",
  "cookingtime": 200,
  "experience": 0.25,
  "group": "titanium",
  "ingredient": {
    "item": "galaxyunderchaos:titanium_deepslate_ore"
  },
  "result": {
    "id": "galaxyunderchaos:titanium_ingot"
  }
}
""");
        put("titanium_by_smelting_titanium_ore", """
{
  "type": "minecraft:smelting",
  "category": "misc",
  "cookingtime": 200,
  "experience": 0.25,
  "group": "titanium",
  "ingredient": {
    "item": "galaxyunderchaos:titanium_ore"
  },
  "result": {
    "id": "galaxyunderchaos:titanium_ingot"
  }
}
""");
        put("titanium_chromium_ingot", """
{
  "type": "minecraft:crafting_shaped",
  "group": "hyperdrive",
  "category": "equipment",
  "key": {
    "#": {
      "item": "galaxyunderchaos:chromium_ingot"
    },
    "X": {
      "item": "galaxyunderchaos:titanium_ingot"
    }
  },
  "pattern": [
    "X",
    "#"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:titanium_chromium_ingot"
  }
}
""");
        put("tython_portal", """
{
  "type": "minecraft:crafting_shaped",
  "category": "equipment",
  "group": "hyperdrive",
  "key": {
    "#": {
      "item": "minecraft:mossy_cobblestone"
    },
    "X": {
      "item": "galaxyunderchaos:portal_item"
    }
  },
  "pattern": [
    "###",
    "#X#",
    "###"
  ],
  "result": {
    "count": 1,
    "id": "galaxyunderchaos:tython_portal"
  }
}
""");
        put("tython_temple_stone_pillar", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "#",
        "#"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:tython_temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:tython_temple_stone_pillar",
        "count": 2
    },
    "group": "misc"
}
""");
        put("tython_temple_stone_pillar_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:tython_temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:tython_temple_stone_pillar",
        "count": 1
    },
    "group": "misc"
}
""");
        put("tython_temple_stone_slab", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "###"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:tython_temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:tython_temple_stone_slab",
        "count": 6
    },
    "group": "misc"
}
""");
        put("tython_temple_stone_slab_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:tython_temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:tython_temple_stone_slab",
        "count": 2
    },
    "group": "misc"
}
""");
        put("tython_temple_stone_stairs", """
{
    "type": "minecraft:crafting_shaped",
    "pattern": [
        "  #",
        " ##",
        "###"
    ],
    "key": {
        "#": {
            "item": "galaxyunderchaos:tython_temple_stone"
        }
    },
    "result": {
        "id": "galaxyunderchaos:tython_temple_stone_stairs",
        "count": 4
    },
    "group": "misc"
}
""");
        put("tython_temple_stone_stairs_stonecutter", """
{
    "type": "minecraft:stonecutting",
    "ingredient": {
        "item": "galaxyunderchaos:tython_temple_stone"
    },
    "result": {
        "id": "galaxyunderchaos:tython_temple_stone_stairs",
        "count": 1
    },
    "group": "misc"
}
""");
        put("tython_temple_stone_wall", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "key": {
        "#": {
            "item": "galaxyunderchaos:tython_temple_stone"
        }
    },
    "pattern": [
        "###",
        "###"
    ],
    "result": {
        "count": 6,
        "id": "galaxyunderchaos:tython_temple_stone_wall"
    }
}
""");
        put("wingmaw_blade", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "group": "sticks",
    "key": {
        "#": {
            "item": "galaxyunderchaos:wingmaw_fang"
        }
    },
    "pattern": [
        "  #",
        " # ",
        "#  "
    ],
    "result": {
        "count": 1,
        "id": "galaxyunderchaos:wingmaw_blade"
    }
}
""");
        put("wingmaw_dagger", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "group": "sticks",
    "key": {
        "#": {
            "item": "galaxyunderchaos:wingmaw_blade"
        },
        "x": {
            "item": "galaxyunderchaos:wingmaw_hilt"
        },
        "a": {
            "item": "galaxyunderchaos:wingmaw_fang"
        }
    },
    "pattern": [
        "   ",
        "a# ",
        "xa "
    ],
    "result": {
        "count": 1,
        "id": "galaxyunderchaos:wingmaw_dagger"
    }
}
""");
        put("wingmaw_hilt", """
{
    "type": "minecraft:crafting_shaped",
    "category": "misc",
    "group": "sticks",
    "key": {
        "#": {
            "item": "galaxyunderchaos:wingmaw_feather"
        },
        "x": {
            "item": "galaxyunderchaos:wingmaw_hide"
        },
        "a": {
            "item": "minecraft:stick"
        }
    },
    "pattern": [
        " ax",
        "a#a",
        "xa "
    ],
    "result": {
        "count": 1,
        "id": "galaxyunderchaos:wingmaw_hilt"
    }
}
""");
    }

    private void put(String name, String json) {
        recipeJson.put(name, json);
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        List<CompletableFuture<?>> writes = new ArrayList<>();
        recipeJson.forEach((name, json) -> writes.add(saveRecipe(cache, name, json)));
        return CompletableFuture.allOf(writes.toArray(CompletableFuture[]::new));
    }

    private CompletableFuture<?> saveRecipe(CachedOutput cache, String name, String json) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "recipe/" + name);
        Path path = packOutput.getOutputFolder()
                .resolve("data")
                .resolve(id.getNamespace())
                .resolve(id.getPath() + ".json");
        return DataProvider.saveStable(cache, JsonParser.parseString(json), path);
    }

    @Override
    public String getName() {
        return "GalaxyUnderChaos Manual Recipes";
    }
}
