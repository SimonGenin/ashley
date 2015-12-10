package be.simongenin.entities;

import be.simongenin.World;
import be.simongenin.components.*;
import be.simongenin.textures.TextureLoader;
import be.simongenin.textures.TextureType;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class PlayerEntity extends Entity {

    // physics based
    private static float SIZE = 1;

    TextureComponent textureComponent;
    PhysicsComponent physicsComponent;
    PlayerInputComponent playerInputComponent;

    public PlayerEntity(float x, float y) {

        super();

        textureComponent = new TextureComponent();
        physicsComponent  = new PhysicsComponent();
        playerInputComponent = new PlayerInputComponent();

        textureComponent.texture = TextureLoader.load("sand", TextureType.BLOC);
        textureComponent.priority = 1;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        bodyDef.fixedRotation = true;

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
        add(physicsComponent);
        add(playerInputComponent);

    }

}
