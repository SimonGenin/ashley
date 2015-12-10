package be.simongenin.systems;

import be.simongenin.components.PhysicsComponent;
import be.simongenin.utils.Families;
import be.simongenin.utils.Mappers;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class PlayerInputSystem extends EntitySystem {

    private static final float MAX_VELOCITY = 1.0f;
    // should only have one though
    ImmutableArray<Entity> entities;

    private float speed = 0.80f;
    private float jumpForce = 2.8f;

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Families.havePlayerInput);
    }

    @Override
    public void update(float deltaTime) {

        for (Entity entity : entities) {

            PhysicsComponent pc = Mappers.physicsComponent.get(entity);

            Vector2 vel = pc.body.getLinearVelocity();
            Vector2 pos = pc.body.getPosition();

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && vel.x > -MAX_VELOCITY) {
                pc.body.applyLinearImpulse(-speed, 0, pos.x, pos.y, true);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && vel.x < MAX_VELOCITY) {
               pc.body.applyLinearImpulse(speed, 0, pos.x, pos.y, true);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                pc.body.applyLinearImpulse(0, jumpForce, pos.x, pos.y, true);
            }

        }

    }
}
