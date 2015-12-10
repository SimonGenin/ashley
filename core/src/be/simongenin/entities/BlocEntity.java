package be.simongenin.entities;

import be.simongenin.World;
import be.simongenin.components.PhysicsComponent;
import be.simongenin.components.ResistanceComponent;
import be.simongenin.components.TextureComponent;
import be.simongenin.textures.TextureLoader;
import be.simongenin.textures.TextureType;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * A bloc is made of
 * position
 * texture
 * resistance
 */
public class BlocEntity extends Entity {

    // physics based
    private static float SIZE = 1;

    TextureComponent textureComponent;
    ResistanceComponent resistanceComponent;
    PhysicsComponent physicsComponent;

    public BlocEntity(float x, float y, float r, String texture, float resistance) {

        super();

        textureComponent = new TextureComponent();
        resistanceComponent = new ResistanceComponent();
        physicsComponent = new PhysicsComponent();

        textureComponent.texture = TextureLoader.load(texture, TextureType.BLOC);
        textureComponent.priority = 0;

        resistanceComponent.resistance = resistance;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);
        bodyDef.angle = (float) Math.toRadians(r);

        Body body = World.physics.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(SIZE / 2, SIZE / 2, new Vector2(SIZE / 2, SIZE / 2), 0);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 1.0f;
        fixtureDef.restitution = 0.0f;

        body.createFixture(fixtureDef);

        shape.dispose();

        physicsComponent.body = body;

        add(textureComponent);
        add(resistanceComponent);
        add(physicsComponent);

    }

    public BlocEntity(String texture, float resistance) {
        this(0, 0, 0, texture, resistance);
    }

}