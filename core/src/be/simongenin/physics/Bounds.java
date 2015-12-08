package be.simongenin.physics;

import be.simongenin.components.TransformComponent;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Bounds {

    public float x;
    public float y;

    public float width;
    public float height;

    private CollapsingInfo info;

    public Bounds(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        info = new CollapsingInfo();
    }

    public Bounds(TransformComponent transform, float width, float height) {
        this(transform.x, transform.y, width, height);
    }

    public Bounds(TransformComponent transform, TextureRegion texture) {
        this(transform.x, transform.y, texture.getRegionWidth(), texture.getRegionHeight());
    }

    public void updatePosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public CollapsingInfo collaps(Bounds bounds) {
        // TODO
        return info;
    }

    public class CollapsingInfo {

        public boolean isCollapsed;
        public Direction collapsingDirection;

    }

}
