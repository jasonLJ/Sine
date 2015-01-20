package us.rescyou.sine.desktop;

import us.rescyou.sine.Main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Sine";
		config.width = 360;
		config.height = 640;
		new LwjglApplication(new Main(), config);
	}
}
