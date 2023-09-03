package ru.myitschool.jabagames;

import com.badlogic.gdx.Game;

public class MainGame extends Game {
    public MainScreen mainScreen;

    @Override
    public void create() {
        mainScreen = new MainScreen(this);
        setScreen(mainScreen);
    }
}
