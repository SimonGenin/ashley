package be.simongenin.utils;

import be.simongenin.components.*;
import com.badlogic.ashley.core.Family;

public class Families {

    public static Family renderableEntities = Family.all(TextureComponent.class).one(TransformComponent.class, PhysicsComponent.class).get();
    public static Family havePlayerInput = Family.all(PlayerInputComponent.class, PhysicsComponent.class).get();

}
