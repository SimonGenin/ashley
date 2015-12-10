package be.simongenin;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;

public class World {

    public static com.badlogic.gdx.physics.box2d.World physics;
    public static final int scale = 128;

    public World() {
        Box2D.init();
        physics = new com.badlogic.gdx.physics.box2d.World(new Vector2(0f, -10f), true);
    }

}
