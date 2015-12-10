package be.simongenin;

import be.simongenin.entities.BlocEntity;
import be.simongenin.entities.PlayerEntity;
import be.simongenin.systems.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Game extends ApplicationAdapter {

	Engine engine;
	World world;
	
	@Override
	public void create () {

		engine = new Engine();

		world = new World();

		engine.addSystem(new PlayerInputSystem());
		engine.addSystem(new RenderingSystem());

		BlocEntity b1 = new BlocEntity(-2, 1, 0, "stone", 1);
		BlocEntity b2 = new BlocEntity(-2, 0, 0, "stone", 1);
		BlocEntity b3 = new BlocEntity(-1, 0, 0, "stone", 1);
		BlocEntity b4 = new BlocEntity(0, 0, 0, "stone", 1);
		BlocEntity b5 = new BlocEntity(1, 0, 0, "stone", 1);
		BlocEntity b6 = new BlocEntity(2, 0, 0, "stone", 1);
		BlocEntity b7 = new BlocEntity(2, 1, 0, "stone", 1);

		engine.addEntity(b1);
		engine.addEntity(b2);
		engine.addEntity(b3);
		engine.addEntity(b4);
		engine.addEntity(b5);
		engine.addEntity(b6);
		engine.addEntity(b7);

		PlayerEntity player = new PlayerEntity(0, 1);
		engine.addEntity(player);

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		engine.update(Gdx.graphics.getDeltaTime() * 50);

		World.physics.step(1 / 60f, 6, 2);

	}

	@Override
	public void resize(int width, int height) {

		RenderingSystem rs = engine.getSystem(RenderingSystem.class);
		rs.onresize(width, height);

	}
}
