package be.simongenin.utils;

import be.simongenin.components.*;
import com.badlogic.ashley.core.Family;

public class Families {

    public static Family movableEntities = Family.all(TransformComponent.class, MovementComponent.class).get();
    public static Family affectedByGravity = Family.all(TransformComponent.class, GravityComponent.class).get();
    public static Family renderableEntities = Family.all(TransformComponent.class, TextureComponent.class).get();
    public static Family collidable = Family.all(CollisionComponent.class).get();
    public static Family movableAndCollidableEntities = Family.all(MovementComponent.class, CollisionComponent.class).get();
    public static Family havePlayerInput = Family.all(PlayerInputComponent.class).get();
}
