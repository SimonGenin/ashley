package be.simongenin.systems;

import be.simongenin.components.GravityComponent;
import be.simongenin.components.MovementComponent;
import be.simongenin.utils.Families;
import be.simongenin.utils.Mappers;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;

public class GravitySystem extends IteratingSystem {

    public GravitySystem() {
        super(Families.affectedByGravity);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        MovementComponent mc = Mappers.movementComponents.get(entity);
        GravityComponent gc = Mappers.gravityComponents.get(entity);

        // If terminal velocity not reached
        if (mc.y > gc.force) {
            mc.y += ((gc.force / 2) * deltaTime);

            // If terminal velocity overtaken, let's put it back to the terminal velocity
            if (mc.y < gc.force) {
                mc.y = gc.force;
            }

        }

    }
}
