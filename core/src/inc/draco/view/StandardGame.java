package inc.draco.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * <p>Abstract class that contains code used for every game
 * such as:</p>
 * <p>It also needs some methods implemented, such as:</p>
 * <ul> getScreens(): a method that returns the {@link ObjectMap}<{@link String}, {@link Screen}> that
 *     contains the screens for the game </ul>
 */
public abstract class StandardGame extends Game {
    protected ObjectMap<String, Screen> screens;
    protected boolean debug = false;

    public StandardGame() {
//        screens = getScreens();
    }

    @Override
    public abstract void create();

//    protected abstract ObjectMap<String, Screen> getScreens();

    public boolean changeScreen(String name)    {
        setScreen(screens.get(name));
        if (debug) System.out.println(name);
        return true;
    }
}
