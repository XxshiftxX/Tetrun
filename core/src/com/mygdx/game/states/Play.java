package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.IDrawable;
import com.mygdx.game.entities.Mino;
import com.mygdx.game.entities.MinoType;
import com.mygdx.game.entities.Player;
import com.mygdx.game.handlers.GameStateManager;

import java.util.ArrayList;

public class Play extends GameState {
    private BitmapFont font = new BitmapFont();
    private ArrayList<IDrawable> drawables = new ArrayList<IDrawable>();

    public Play(GameStateManager gsm)
    {
        super(gsm);

        drawables.add(new Mino(new Texture(Gdx.files.internal("badlogic.jpg")), MinoType.L, new Vector2(10, 10)));
        drawables.add(new Mino(new Texture(Gdx.files.internal("badlogic.jpg")), MinoType.L, new Vector2(7, 8)));
        drawables.add(new Mino(new Texture(Gdx.files.internal("badlogic.jpg")), MinoType.J, new Vector2(3, 10)));
        drawables.add(new Player());
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        for(IDrawable drawable: drawables)
            drawable.Update(dt);
    }

    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        for(IDrawable drawable: drawables)
        {
            drawable.Render(sb);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
