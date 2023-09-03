package ru.myitschool.jabagames.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.myitschool.jabagames.Chess;
import ru.myitschool.jabagames.MainGame;
import ru.myitschool.jabagames.MainScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Chess.SCR_WIDTH;
		config.height = Chess.SCR_HEIGHT;
		new LwjglApplication(new MainGame(), config);
	}
}
