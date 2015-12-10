package be.simongenin.entities;

import be.simongenin.World;
import be.simongenin.components.PhysicsComponent;
import be.simongenin.components.ResistanceComponent;
import be.simongenin.components.TextureComponent;
import be.simongenin.components.TransformComponent;
import be.simongenin.textures.TextureLoader;
import be.simongenin.textures.TextureType;
import com.badlogic.ashley.core.Entity;
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

    private static float SIZE = 128;
    private static float PHYSICS_SIZE = SIZE / World.scale;

    TransformComponent positionComponent;
    TextureComponent textureComponent;
    ResistanceComponent resistanceComponent;
    PhysicsComponent physicsComponent;

    public BlocEntity(float x, float y, float r, String texture, float resistance) {

        super();

        positionComponent = new TransformComponent();
        textureComponent = new TextureComponent();
        resistanceComponent = new ResistanceComponent();
        physicsComponent = new PhysicsComponent();

        positionComponent.x = x;
        positionComponent.y = y;
        positionComponent.r = r;

        textureComponent.texture = TextureLoader.load(texture, TextureType.BLOC);
        textureComponent.priority = 0;

        resistanceComponent.resistance = resistance;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        float offset = PHYSICS_SIZE / 2;
        float scaledXPos = x / World.scale;
        float scaledYPos = y / World.scale;
        bodyDef.position.set(PHYSICS_SIZE * scaledXPos + offset, PHYSICS_SIZE * scaledYPos + offset);

        Body body = World.physics.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PHYSICS_SIZE / 2, PHYSICS_SIZE / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 1.0f;
        fixtureDef.restitution = 0.0f;

        body.createFixture(fixtureDef);

        shape.dispose();

        physicsComponent.body = body;

        add(positionComponent);
        add(textureComponent);
        add(resistanceComponent);
        add(physicsComponent);

    }

    public BlocEntity(String texture, float resistance) {
        this(0, 0, 0, texture, resistance);
    }

}