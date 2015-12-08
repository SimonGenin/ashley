package be.simongenin.entities;

import be.simongenin.components.CollisionComponent;
import be.simongenin.components.TransformComponent;
import be.simongenin.components.ResistanceComponent;
import be.simongenin.components.TextureComponent;
import be.simongenin.textures.TextureLoader;
import be.simongenin.textures.TextureType;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;

/**
 * A bloc is made of
 * position
 * texture
 * resistance
 */
public class BlocEntity extends Entity {

    TransformComponent positionComponent;
    TextureComponent textureComponent;
    ResistanceComponent resistanceComponent;
    CollisionComponent collisionComponent;

    public BlocEntity(float x, float y, float r, String texture, float resistance) {
        super();

        positionComponent = new TransformComponent();
        textureComponent = new TextureComponent();
        resistanceComponent = new ResistanceComponent();
        collisionComponent = new CollisionComponent();

        positionComponent.x = x;
        positionComponent.y = y;
        positionComponent.r = r;

        textureComponent.texture = TextureLoader.load(texture, TextureType.BLOC);
        textureComponent.priority = 0;

        collisionComponent.bounds = new Rectangle(x, y, 128, 128);

        resistanceComponent.resistance = resistance;

        add(positionComponent);
        add(textureComponent);
        add(resistanceComponent);
        add(collisionComponent);

    }

    public BlocEntity(String texture, float resistance) {
        this(0, 0, 0, texture, resistance);
    }

}