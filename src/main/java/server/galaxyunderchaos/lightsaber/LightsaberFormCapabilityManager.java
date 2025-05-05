package server.galaxyunderchaos.lightsaber;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/** Helper methods for reading / mutating the player’s lightsaber‑form capability. */
public final class LightsaberFormCapabilityManager {

    private LightsaberFormCapabilityManager() {
    }

    /* ------------------------------------------------------------------ */
    /*  Internal helpers                                                  */
    /* ------------------------------------------------------------------ */

    private static @Nullable LightsaberFormCapability get(ServerPlayer player) {
        return player.getCapability(LightsaberFormCapability.CAPABILITY);
    }

    /**
     * Send the full state to the client and re‑apply attribute effects.
     */
    private static void sync(ServerPlayer player, LightsaberFormCapability cap) {
        List<String> unlocked = new ArrayList<>(cap.getUnlockedForms());   // copy → list
        String selected = cap.getSelectedForm() == null ? "" : cap.getSelectedForm();

        LightsaberFormNetworking.sendToPlayer(
                player, new SyncLightsaberFormPacket(unlocked, selected));

        if (!selected.isEmpty()) {
            LightsaberFormEffects.applyEffects(player, selected);
        } else {
            LightsaberFormEffects.clearEffects(player);
        }
    }

    /* ------------------------------------------------------------------ */
    /*  Public API                                                        */
    /* ------------------------------------------------------------------ */

    /**
     * Push the current capability state to the client.
     */
    public static void syncCapability(ServerPlayer player) {
        LightsaberFormCapability cap = get(player);
        if (cap != null) sync(player, cap);
    }

    /**
     * Permanently unlock a form for this player.
     */
    // in LightsaberFormCapabilityManager
    public static void unlockForm(ServerPlayer player, String formId) {
        LightsaberForm form = LightsaberForm.fromId(formId);
        if (form == null) return;

        LightsaberFormCapability cap = get(player);
        if (cap != null) {
            String disp = form.getDisplayName();
            cap.unlockForm(disp);
            if (cap.getSelectedForm() == null) cap.setSelectedForm(disp);
            sync(player, cap);
            player.sendSystemMessage(Component.literal("Unlocked lightsaber form: " + disp), false);
        }
    }

    public static void changeForm(ServerPlayer player, String formId) {
        LightsaberForm form = LightsaberForm.fromId(formId);
        if (form == null) return;

        LightsaberFormCapability cap = get(player);
        if (cap != null && cap.hasForm(form.getDisplayName())) {
            cap.setSelectedForm(form.getDisplayName());
            sync(player, cap);
        }
    }
}
