package server.galaxyunderchaos.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GroundSaberStandBlockEntity extends BlockEntity {

    private ItemStack saber = ItemStack.EMPTY;

    public GroundSaberStandBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SABER_STAND_BE.get(), pos, state);
    }

    // ----------------------------
    // Inventory helpers
    // ----------------------------

    public ItemStack getItem() {
        return saber;
    }

    public boolean isEmpty() {
        return saber.isEmpty();
    }

    public void setItem(ItemStack stack) {
        this.saber = stack;
        setChanged();
        syncToClient();
    }

    public ItemStack removeItem() {
        ItemStack out = saber.copy();
        this.saber = ItemStack.EMPTY;
        setChanged();
        syncToClient();
        return out;
    }

    private void syncToClient() {
        Level level = this.level;
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    // ----------------------------
    // Serialization (1.21.1 API)
    // ----------------------------

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);

        if (!saber.isEmpty()) {
            tag.put("Saber", saber.save(provider, new CompoundTag()));
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);

        if (tag.contains("Saber")) {
            saber = ItemStack.parseOptional(provider, tag.getCompound("Saber"));
        } else {
            saber = ItemStack.EMPTY;
        }
    }

    // Explicitly wire BE <-> client updates

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, provider);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider provider) {
        loadAdditional(tag, provider);
    }
}
