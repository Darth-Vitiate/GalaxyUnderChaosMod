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

    public void unlockForm(String form) {
        unlockedForms.add(form);
        galaxyunderchaos.LOGGER.debug("Unlocked form: {}", form);
    }

    public boolean hasForm(String form) {
        boolean result = unlockedForms.contains(form);
        galaxyunderchaos.LOGGER.debug("Checking if form '{}' is unlocked: {}", form, result);
        return result;
    }

    public void setSelectedForm(String form) {
        if (unlockedForms.contains(form)) {
            this.selectedForm = form;
            galaxyunderchaos.LOGGER.debug("Selected form set to: {}", form);
        } else {
            galaxyunderchaos.LOGGER.warn("Tried to select form '{}' which is not unlocked", form);
        }
    }

    @Nullable
    public String getSelectedForm() {
        galaxyunderchaos.LOGGER.debug("Getting selected form: {}", selectedForm);
        return selectedForm;
    }

    public Set<String> getUnlockedForms() {
        return Collections.unmodifiableSet(unlockedForms);
    }

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

        galaxyunderchaos.LOGGER.debug("Serialized capability: {}", tag);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        galaxyunderchaos.LOGGER.debug("Deserializing LightsaberFormCapability from NBT: {}", nbt);

        unlockedForms.clear();
        ListTag list = nbt.getList("UnlockedForms", Tag.TAG_STRING);
        for (int i = 0; i < list.size(); i++) {
            String form = list.getString(i);
            unlockedForms.add(form);
            galaxyunderchaos.LOGGER.debug("Unlocked form added: {}", form);
        }

        if (nbt.contains("SelectedForm", Tag.TAG_STRING)) {
            selectedForm = nbt.getString("SelectedForm");
            galaxyunderchaos.LOGGER.debug("Selected form set to: {}", selectedForm);
        } else {
            selectedForm = null;
            galaxyunderchaos.LOGGER.debug("Selected form set to null");
        }

        galaxyunderchaos.LOGGER.debug("Post-deserialization state: selected={}, unlocked={}", selectedForm, unlockedForms);
    }
    public boolean isEmpty() {
        return unlockedForms.isEmpty() && (selectedForm == null || selectedForm.isEmpty());
    }

}
