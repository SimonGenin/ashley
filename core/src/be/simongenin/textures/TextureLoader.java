package be.simongenin.textures;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TextureLoader {

    public static TextureAtlas.AtlasRegion load(String textureName, TextureType type) {

        TextureAtlas atlas = null;
        TextureAtlas.AtlasRegion region = null;

        if (type == TextureType.BLOC) {

            atlas = TextureAtlases.blocAtlas;
            region = atlas.findRegion(textureName);

        }

        return region;

    }

}
