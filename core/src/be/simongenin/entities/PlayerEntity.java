package be.simongenin.entities;

import be.simongenin.components.*;
import be.simongenin.textures.TextureLoader;
import be.simongenin.textures.TextureType;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class PlayerEntity extends Entity {

    TransformComponent transformComponent;
    GravityComponent gravityComponent;
    MovementComponent movementComponent;
    TextureComponent textureComponent;
    CollisionComponent collisionComponent;
    PlayerInputComponent playerInputComponent;


    public PlayerEntity(int x, int y) {

        super();

        transformComponent = new TransformComponent();
        gravityComponent = new GravityComponent();
        movementComponent = new MovementComponent();
        textureComponent = new TextureComponent();
        collisionComponent  = new CollisionComponent();
        playerInputComponent = new PlayerInputComponent();

        transformComponent.x = x;
        transformComponent.y = y;

        movementComponent.states = new ArrayList<>();

        textureComponent.texture = TextureLoader.load("sand", TextureType.BLOC);
        textureComponent.priority = 1;

        collisionComponent.bounds = new Rectangle(x, y, 128, 128);


        add(transformComponent);
        add(gravityComponent);
        add(movementComponent);
        add(textureComponent);
        add(collisionComponent);
        add(playerInputComponent);

    }

}
