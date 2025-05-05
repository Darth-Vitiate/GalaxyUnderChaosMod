package server.galaxyunderchaos.lightsaber;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;
import server.galaxyunderchaos.galaxyunderchaos;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Stores which forms have been unlocked & which is currently selected.
 */
public class LightsaberFormCapability implements INBTSerializable<CompoundTag> {
    public static final EntityCapability<LightsaberFormCapability, Void> CAPABILITY =
            EntityCapability.createVoid(
                    ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "lightsaber_form"),
                    LightsaberFormCapability.class
            );

    private final Set<String> unlockedForms = ConcurrentHashMap.newKeySet();
    private @Nullable String selectedForm = null;

    public void unlockForm(String form)      { unlockedForms.add(form); }
    public boolean hasForm(String form)      { return unlockedForms.contains(form); }
    public void setSelectedForm(String form) { if (unlockedForms.contains(form)) selectedForm = form; }
    @Nullable public String getSelectedForm() { return selectedForm; }
    public Set<String> getUnlockedForms()    { return Collections.unmodifiableSet(unlockedForms); }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        ListTag list = new ListTag();
        for (String s : unlockedForms) {
            list.add(StringTag.valueOf(s));
        }
        tag.put("UnlockedForms", list);
        if (selectedForm != null) {
            tag.putString("SelectedForm", selectedForm);
        }
        return tag;    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        unlockedForms.clear();
        ListTag list = nbt.getList("UnlockedForms", Tag.TAG_STRING);
        for (int i = 0; i < list.size(); i++) {
            unlockedForms.add(list.getString(i));
        }
        this.selectedForm = nbt.contains("SelectedForm", Tag.TAG_STRING)
                ? nbt.getString("SelectedForm")
                : null;
    }
}
