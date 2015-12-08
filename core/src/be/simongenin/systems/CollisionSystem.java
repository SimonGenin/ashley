package be.simongenin.systems;

import be.simongenin.components.CollisionComponent;
import be.simongenin.components.MovementComponent;
import be.simongenin.components.TransformComponent;
import be.simongenin.enums.MovementState;
import be.simongenin.utils.Families;
import be.simongenin.utils.Mappers;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Logger;

public class CollisionSystem extends EntitySystem {

    ImmutableArray<Entity> collidableEntities;
    ImmutableArray<Entity> movableAndCollidableEntities;

    Logger log;

    public CollisionSystem() {
        log = new Logger(this.getClass().getSimpleName());
    }

    @Override
    public void addedToEngine(Engine engine) {
        collidableEntities = engine.getEntitiesFor(Families.collidable);
        movableAndCollidableEntities = engine.getEntitiesFor(Families.movableAndCollidableEntities);
    }

    @Override
    public void update(float deltaTime) {


        for (Entity movableAndCollidableEntity : movableAndCollidableEntities) {

            TransformComponent tc = Mappers.transformComponents.get(movableAndCollidableEntity);
            CollisionComponent cc = Mappers.collisionComponents.get(movableAndCollidableEntity);
            MovementComponent  mc = Mappers.movementComponents.get(movableAndCollidableEntity);


            for (Entity collidableEntity : collidableEntities) {

                if (movableAndCollidableEntity.equals(collidableEntity)) continue;

                CollisionComponent ccOther = Mappers.collisionComponents.get(collidableEntity);

                while (cc.bounds.overlaps(ccOther.bounds)) {

                    for (MovementState state : mc.states) {

                        switch (state) {
                            case IDLE:
                                log.error("Collision while idle");
                                break;
                            case RIGHT:
                                log.debug("Collision while right");
                                tc.x -= 1;
                                break;
                            case LEFT:
                                log.debug("Collision while left");
                                tc.x += 1;
                                break;
                            case UP:
                                log.debug("Collision while up");
                                tc.y -= 1;
                                mc.y = 0;
                                break;
                            case DOWN:
                                log.debug("Collision while down");
                                tc.y +=1 ;
                                mc.y = 0;
                                break;
                        }

                    }

                }

            }

        }

    }



}
