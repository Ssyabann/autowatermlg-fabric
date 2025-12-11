package com.example.autowater;

import net.fabricmc.fabric.api.event.player.PlayerTickCallback;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.text.LiteralText;

public class PlayerFallHandler {

    private static final double FALL_TRIGGER = -0.6;
    private static final int SAFE_HEIGHT = 5;

    public static void register() {
        PlayerTickCallback.EVENT.register(PlayerFallHandler::tick);
    }

    private static void tick(PlayerEntity player) {
        if (player == null || player.world == null || player.world.isClient) return;

        if (player.getVelocity().y < FALL_TRIGGER) {
            if (player.getStackInHand(Hand.MAIN_HAND).getItem() == Items.WATER_BUCKET) {
                int blocksBelow = 0;
                while (blocksBelow <= SAFE_HEIGHT) {
                    if (!player.world.getBlockState(player.getBlockPos().down(blocksBelow)).isAir()) break;
                    blocksBelow++;
                }

                if (blocksBelow <= SAFE_HEIGHT) {
                    player.world.setBlockState(player.getBlockPos().down(), Blocks.WATER.getDefaultState());
                    player.setStackInHand(Hand.MAIN_HAND, Items.BUCKET.getDefaultStack());
                    player.sendMessage(new LiteralText("AutoWaterMLG: Water placed!"), true);
                }
            }
        }
    }
}
