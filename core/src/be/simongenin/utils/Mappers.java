package be.simongenin.utils;

import be.simongenin.components.*;
import com.badlogic.ashley.core.ComponentMapper;

public final class Mappers {

    public static ComponentMapper<TransformComponent> transformComponents = ComponentMapper.getFor(TransformComponent.class);
    public static ComponentMapper<ResistanceComponent> resistanceComponents = ComponentMapper.getFor(ResistanceComponent.class);
    public static ComponentMapper<TextureComponent> textureComponents = ComponentMapper.getFor(TextureComponent.class);
    public static ComponentMapper<PhysicsComponent> physicsComponent = ComponentMapper.getFor(PhysicsComponent.class);

}
