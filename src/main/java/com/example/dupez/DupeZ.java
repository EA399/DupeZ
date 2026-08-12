package com.example.dupez;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.server.command.CommandManager;

import java.util.UUID;

public class DupeZ implements ModInitializer {

    // Nur dein Account
    private static final UUID OWNER =
        UUID.fromString("810d921a-1349-46a6-b131-dca08c820731");

    @Override
    public void onInitialize() {

        CommandRegistrationCallback.EVENT.register(
            (dispatcher, registryAccess, environment) -> {

                dispatcher.register(
                    CommandManager.literal("dupe")
                        .then(
                            CommandManager.argument(
                                "amount",
                                IntegerArgumentType.integer(1, 64)
                            )
                            .executes(ctx -> {

                                ServerPlayerEntity player =
                                    ctx.getSource().getPlayer();

                                // UUID überprüfen
                                if (!player.getUuid().equals(OWNER)) {
                                    player.sendMessage(
                                        Text.literal(
                                            "§cKeine Berechtigung."
                                        ),
                                        false
                                    );
                                    return 0;
                                }

                                int amount =
                                    IntegerArgumentType.getInteger(
                                        ctx,
                                        "amount"
                                    );

                                ItemStack item =
                                    player.getMainHandStack();

                                if (item.isEmpty()) {
                                    player.sendMessage(
                                        Text.literal(
                                            "§cHalte ein Item in der Hand."
                                        ),
                                        false
                                    );
                                    return 0;
                                }

                                ItemStack copy =
                                    item.copyWithCount(amount);

                                player.dropItem(
                                    copy,
                                    false,
                                    false
                                );

                                player.sendMessage(
                                    Text.literal(
                                        "§aDu hast " +
                                        amount +
                                        "x " +
                                        item.getName().getString() +
                                        " erzeugt."
                                    ),
                                    false
                                );

                                return 1;
                            })
                        )
                );
            }
        );
    }
}
