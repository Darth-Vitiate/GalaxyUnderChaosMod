package server.galaxyunderchaos.item;

import net.minecraft.world.item.Item;

public class HiltItem extends Item {
    private final String color;

    public HiltItem(String color, Properties properties) {
        super(properties);
        this.color = color; // Store the hilt's associated color
    }

    public String getColor() {
        return color;
    }
}
