package be.simongenin.entities;

import be.simongenin.World;
import be.simongenin.components.*;
import be.simongenin.textures.TextureLoader;
import be.simongenin.textures.TextureType;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.BodyDef;

import java.util.ArrayList;

public class PlayerEntity extends Entity {

    TransformComponent transformComponent;
    GravityComponent gravityComponent;
    MovementComponent movementComponent;
    TextureComponent textureComponent;
    PhysicsComponent physicsComponent;
    PlayerInputComponent playerInputComponent;

    public PlayerEntity(int x, int y) {

        super();

        transformComponent = new TransformComponent();
        gravityComponent = new GravityComponent();
        movementComponent = new MovementComponent();
        textureComponent = new TextureComponent();
        physicsComponent  = new PhysicsComponent();
        playerInputComponent = new PlayerInputComponent();

        transformComponent.x = x;
        transformComponent.y = y;

        movementComponent.states = new ArrayList<>();

        textureComponent.texture = TextureLoader.load("sand", TextureType.BLOC);
        textureComponent.priority = 1;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        physicsComponent.body = World.physics.createBody(bodyDef);

        add(transformComponent);
        add(gravityComponent);
        add(movementComponent);
        add(textureComponent);
        add(physicsComponent);
        add(playerInputComponent);

    }

}
