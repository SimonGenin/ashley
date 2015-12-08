package be.simongenin.systems;

import be.simongenin.components.TextureComponent;
import be.simongenin.components.TransformComponent;
import be.simongenin.utils.Families;
import be.simongenin.utils.Mappers;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;

import java.util.Comparator;

public class RenderingSystem extends IteratingSystem {

    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    private Logger log;

    public RenderingSystem() {
        super(Families.renderableEntities);

        // Set ut the logger
        log = new Logger(this.getClass().getSimpleName());

        // Create the rendering queue
        renderQueue = new Array<>();

        // Create the batch
        batch = new SpriteBatch();

        // Set up the camera
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);

        // Set up the comparator
        comparator = new Comparator<Entity>() {
            @Override
            public int compare(Entity e1, Entity e2) {

                TextureComponent tcE1 = Mappers.textureComponents.get(e1);
                TextureComponent tcE2 = Mappers.textureComponents.get(e2);

                int pE1 = tcE1.priority;
                int pE2 = tcE2.priority;

                if (pE1 == pE2) return 0;
                else if (pE1 > pE2) return 1;
                else return -1;
            }
        };

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        // Sort with the z priority
        renderQueue.sort(comparator);

        // Update the camera
        camera.update();

        // Set up the batch for the frame
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // loop through the entities
        for (Entity entity : renderQueue) {

            TransformComponent tc = Mappers.transformComponents.get(entity);
            TextureComponent tex = Mappers.textureComponents.get(entity);
            TextureAtlas.AtlasRegion ar = (TextureAtlas.AtlasRegion) tex.texture;

            // Avoid a crash
            if (ar == null) {
                log.error("Null pointer instead of an AtlasRegion");
                continue;
            }


            batch.draw(ar, tc.x, tc.y, tc.x, tc.y, ar.getRegionWidth(), ar.getRegionHeight(), 1, 1, tc.r);


        }

        // Closure
        batch.end();
        renderQueue.clear();

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        // Add every entity to the queue
        renderQueue.add(entity);

    }

    public void onresize(int width, int height) {

        camera.viewportWidth = width;
        camera.viewportHeight = height;

        camera.update();

    }

}
