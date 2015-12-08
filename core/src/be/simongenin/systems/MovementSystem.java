package be.simongenin.systems;

import be.simongenin.components.MovementComponent;
import be.simongenin.components.TransformComponent;
import be.simongenin.enums.MovementState;
import be.simongenin.utils.Families;
import be.simongenin.utils.Mappers;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;

public class MovementSystem extends IteratingSystem {

    ImmutableArray<Entity> entities;

    public MovementSystem() {
        super(Families.movableEntities);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent tc = Mappers.transformComponents.get(entity);
        MovementComponent mc = Mappers.movementComponents.get(entity);

        tc.x += mc.x * deltaTime;
        tc.y += mc.y * deltaTime;

        calculateStates(tc, mc);

    }

    private void calculateStates(TransformComponent tc, MovementComponent mc) {

        mc.states.clear();

        if (tc.x == 0 && tc.y == 0)
        {
            mc.states.add(MovementState.IDLE);
        }

        if (tc.x > 0)
        {
            mc.states.add(MovementState.RIGHT);
        }

        if (tc.x < 0)
        {
            mc.states.add(MovementState.LEFT);
        }

        if (tc.y > 0)
        {
            mc.states.add(MovementState.UP);
        }

        if (tc.y < 0)
        {
            mc.states.add(MovementState.DOWN);
        }

    }

}
