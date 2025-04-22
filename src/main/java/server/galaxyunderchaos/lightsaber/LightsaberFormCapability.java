package server.galaxyunderchaos.lightsaber;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraftforge.common.util.INBTSerializable;
import java.util.HashSet;
import java.util.Set;

public class LightsaberFormCapability implements INBTSerializable<CompoundTag> {
    private final Set<String> unlockedForms = new HashSet<>();
    private String selectedForm = "";

    public void unlockForm(String form) {
        unlockedForms.add(form);
    }

    public boolean hasForm(String form) {
        return unlockedForms.contains(form);
    }

    public void setSelectedForm(String form) {
        if (unlockedForms.contains(form)) {
            this.selectedForm = form;
        }
    }

    public String getSelectedForm() {
        return selectedForm;
    }

    public Set<String> getUnlockedForms() {
        return unlockedForms;
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        ListTag formList = new ListTag();
        for (String form : unlockedForms) {
            CompoundTag formTag = new CompoundTag();
            formTag.putString("form", form);
            formList.add(formTag);
        }
        tag.put("UnlockedForms", formList);
        tag.putString("SelectedForm", selectedForm);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag compoundTag) {
        unlockedForms.clear();
        if (compoundTag.contains("UnlockedForms")) {
            ListTag formList = compoundTag.getList("UnlockedForms", 10);
            for (int i = 0; i < formList.size(); i++) {
                CompoundTag formTag = formList.getCompound(i);
                unlockedForms.add(formTag.getString("form"));
            }
        }
        selectedForm = compoundTag.contains("SelectedForm") ? compoundTag.getString("SelectedForm") : "";
    }
}