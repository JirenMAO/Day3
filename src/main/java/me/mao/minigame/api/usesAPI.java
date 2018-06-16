package me.mao.minigame.api;

import me.mao.minigame.Core;

public interface usesAPI {

    Core instance = null;

    static Core getInstance() {
        return instance;
    }

    void onEnable();

    void onDisable();

    void registerCommands();

    void registerListeners();

    void loadDepends();

}
