// File: LightsaberFormCommand.java
package server.galaxyunderchaos.lightsaber;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Set;

/** `/lightsaberform list|unlock <form>|switch <form>` */
public final class LightsaberFormCommand {
    private LightsaberFormCommand() {}

    public static void register(CommandDispatcher<CommandSourceStack> d) {
        d.register(Commands.literal("lightsaberform")
                .requires(src -> src.hasPermission(2))
                .then(Commands.literal("list")
                        .executes(ctx -> {
                            ServerPlayer p = (ServerPlayer)ctx.getSource().getEntity();
                            if (p != null) {
                                var forms = p.getCapability(LightsaberFormCapability.CAPABILITY).getUnlockedForms();
                                ctx.getSource().sendSuccess(
                                        () -> Component.literal("Unlocked: "+String.join(", ", forms)), false);
                            }
                            return 1;
                        }))
                .then(Commands.literal("unlock")
                        .then(Commands.argument("form", StringArgumentType.string())
                                .executes(ctx -> {
                                    ServerPlayer p = (ServerPlayer)ctx.getSource().getEntity();
                                    if (p != null) LightsaberFormCapabilityManager.unlockForm(p, StringArgumentType.getString(ctx, "form"));
                                    return 1;
                                })))
                .then(Commands.literal("switch")
                        .then(Commands.argument("form", StringArgumentType.string())
                                .executes(ctx -> {
                                    ServerPlayer p = (ServerPlayer)ctx.getSource().getEntity();
                                    if (p != null) LightsaberFormCapabilityManager.changeForm(p, StringArgumentType.getString(ctx, "form"));
                                    return 1;
                                })))
        );
    }
}
