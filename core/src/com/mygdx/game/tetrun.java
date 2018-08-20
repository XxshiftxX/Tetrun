package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.IDrawable;
import com.mygdx.game.entities.Mino;
import com.mygdx.game.entities.MinoType;

import java.util.ArrayList;

public class tetrun extends ApplicationAdapter {
	public static final int UNIT = 32;

	private SpriteBatch batch;
	private Texture img;

	private ArrayList<IDrawable> drawables = new ArrayList<IDrawable>();

	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		drawables.add(new Mino(new Texture(Gdx.files.internal("badlogic.jpg")), MinoType.L, new Vector2(10, 10)));
		drawables.add(new Mino(new Texture(Gdx.files.internal("badlogic.jpg")), MinoType.L, new Vector2(7, 8)));
		drawables.add(new Mino(new Texture(Gdx.files.internal("badlogic.jpg")), MinoType.J, new Vector2(3, 10)));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		for (IDrawable mino: drawables) {
			mino.Render(batch);
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
