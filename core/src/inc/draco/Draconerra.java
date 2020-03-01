package inc.draco;

import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.utils.ObjectMap;
import inc.draco.view.GameScreen;
import inc.draco.view.StandardGame;
import inc.draco.view.StandardScreen;

public class Draconerra extends StandardGame {


	@Override
	public void create() {
		Box2D.init();
		screens = new ObjectMap<>();
		screens.put("game", new GameScreen());
		changeScreen("game");
	}

	public static final String TITLE = "Upgration";
	public static final float WORLD_WIDTH = 100;
	public static final float WORLD_HEIGHT = 9f / 16f * WORLD_WIDTH;
	public static final float TIME_STEP = 1/60f;
	public static final int VELOCITY_ITERATIONS = 6;
	public static final int POSITION_ITERATIONS = 2;
}