package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entities.IDrawable;
import com.mygdx.game.entities.Mino;
import com.mygdx.game.entities.MinoType;
import com.mygdx.game.entities.Player;
import com.mygdx.game.handlers.GameStateManager;
import com.mygdx.game.handlers.Input;
import com.mygdx.game.handlers.InputProcessor;

import java.util.ArrayList;

public class Game extends ApplicationAdapter {
	public static final int UNIT = 30;
	public static final int V_WIDTH = 1280;
	public static final int V_HEIGHT = 720;
	public static World world;

	private SpriteBatch batch;
	private OrthographicCamera cam;
	private OrthographicCamera hudcam;
	private Texture img;
	private GameStateManager gsm;

	private static final float STEP = 1/60f;
	private float accum;

	@Override
	public void create ()
	{
		Gdx.input.setInputProcessor(new InputProcessor());

		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudcam = new OrthographicCamera();
		hudcam.setToOrtho(false, V_WIDTH, V_HEIGHT);

		gsm = new GameStateManager(this);
	}

	@Override
	public void render () {
		accum += Gdx.graphics.getDeltaTime();
		while(accum >= STEP)
		{
			accum -= STEP;
			gsm.update(STEP);
			gsm.render();
			Input.update();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public SpriteBatch getSpriteBatch() {
		return batch;
	}

	public OrthographicCamera getCamera() {
		return cam;
	}

	public OrthographicCamera getHUDCamera() {
		return hudcam;
	}
}
