package be.simongenin.systems;

import be.simongenin.World;
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
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Comparator;

public class RenderingSystem extends IteratingSystem {

    private final float scale = World.scale;

    private Array<Entity> renderQueue;
    private Comparator<Entity> comparator;

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ScreenViewport viewport;

    private Logger log;
    private Box2DDebugRenderer physicsDebugRenderer;

    public RenderingSystem() {
        super(Families.renderableEntities);

        // Set up the loggers
        log = new Logger(this.getClass().getSimpleName());
        physicsDebugRenderer = new Box2DDebugRenderer();

        // Create the rendering queue
        renderQueue = new Array<>();

        // Create the batch
        batch = new SpriteBatch();

        // Set up the camera and viewport
        camera = new OrthographicCamera(Gdx.graphics.getWidth() / scale, Gdx.graphics.getHeight() / scale);
        // camera.position.set(0, 0, 0);

        // Set up the comparator
        comparator = (e1, e2) -> {

            TextureComponent tcE1 = Mappers.textureComponents.get(e1);
            TextureComponent tcE2 = Mappers.textureComponents.get(e2);

            int pE1 = tcE1.priority;
            int pE2 = tcE2.priority;

            if (pE1 == pE2) return 0;
            else if (pE1 > pE2) return 1;
            else return -1;

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


            batch.draw(ar, tc.x / scale, tc.y / scale, tc.x / scale, tc.y / scale, ar.getRegionWidth() / scale, ar.getRegionHeight() / scale, 1, 1, tc.r);


        }

        // Closure
        batch.end();

        physicsDebugRenderer.render(World.physics, camera.combined);

        renderQueue.clear();

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        // Add every entity to the queue
        renderQueue.add(entity);

    }

    public void onresize(int width, int height) {

        camera.viewportWidth = width / scale;
        camera.viewportHeight = height / scale;
        camera.update();

    }

}
