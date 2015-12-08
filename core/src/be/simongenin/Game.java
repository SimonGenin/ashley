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
	
	@Override
	public void create () {

		engine = new Engine();

		engine.addSystem(new RenderingSystem());
		engine.addSystem(new MovementSystem());
		engine.addSystem(new GravitySystem());
		engine.addSystem(new CollisionSystem());
		engine.addSystem(new PlayerInputSystem());

		BlocEntity b = new BlocEntity(128, 128, 0, "dirt", 1);
		BlocEntity b2 = new BlocEntity(0, 0, 0, "stone", 1);
		engine.addEntity(b);
		engine.addEntity(b2);

		PlayerEntity player = new PlayerEntity(50, 150);
		engine.addEntity(player);


	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		engine.update(Gdx.graphics.getDeltaTime() * 50);

	}

	@Override
	public void resize(int width, int height) {

		RenderingSystem rs = engine.getSystem(RenderingSystem.class);
		rs.onresize(width, height);

	}
}