package com.example.autowater;

import net.fabricmc.api.ModInitializer;

public class AutoWaterMLG implements ModInitializer {
    public static final String MOD_ID = "autowatermlg";

    @Override
    public void onInitialize() {
        System.out.println("[AutoWaterMLG] Mod aktif!");
        PlayerFallHandler.register();
    }
}
