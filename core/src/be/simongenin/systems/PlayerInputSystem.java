package be.simongenin.systems;

import be.simongenin.components.MovementComponent;
import be.simongenin.utils.Families;
import be.simongenin.utils.Mappers;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class PlayerInputSystem extends EntitySystem {

    // should only have one though
    ImmutableArray<Entity> entities;

    private float speed = 5;

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Families.havePlayerInput);
    }

    @Override
    public void update(float deltaTime) {

        for (Entity entity : entities) {

            MovementComponent mc = Mappers.movementComponents.get(entity);

            mc.x = 0;

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                mc.x = 1 * speed;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                mc.x = -1 * speed;
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                mc.y = 50;
            }

        }

    }
}
