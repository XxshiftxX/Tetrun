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
import com.mygdx.game.handlers.Input;
import com.mygdx.game.handlers.Key;
import com.mygdx.game.handlers.MinoQueue;

import java.util.ArrayList;

public class Play extends GameState {
    private BitmapFont font = new BitmapFont();
    private ArrayList<IDrawable> drawables = new ArrayList<IDrawable>();
    private MinoQueue minoQueue = new MinoQueue();


    boolean isUpPressed = false;
    boolean isDownPressed = false;
    boolean isLeftPressed = false;
    boolean isRightPressed = false;
    boolean isSpacePressed = false;
    boolean isZPressed = false;
    boolean isXPressed = false;
    boolean isCPressed = false;

    public Play(GameStateManager gsm)
    {
        super(gsm);

        minoQueue.DecideMino();
        //drawables.add(new Mino(new Texture(Gdx.files.internal("badlogic.jpg")), MinoType.J, new Vector2(3, 10)));
        drawables.add(new Player());
    }

    @Override
    public void handleInput() {

        if(Input.isDown(Input.C) && !isCPressed){
            Player player = (Player)drawables.get(0);
            player.Jump();
            isCPressed = true;
        }
        else if(!Input.isDown(Input.C))
            isCPressed = false;

        if(Input.isDown(Input.Z) && !isZPressed){
            minoQueue.GetNowMino().Move(Key.UP);
            isZPressed = true;
        }
        else if(!Input.isDown(Input.Z))
            isZPressed = false;

        if(Input.isDown(Input.X) && !isXPressed){
            minoQueue.GetNowMino().Move(Key.DOWN);
            isXPressed = true;
        }
        else if(!Input.isDown(Input.X))
            isXPressed = false;

        if(Input.isDown((Input.SPACEBAR)) && !isSpacePressed) {
            minoQueue.Deploy();
            isSpacePressed = true;
        }
        else if(!Input.isDown(Input.SPACEBAR))
            isSpacePressed = false;

        if(Input.isDown(Input.LEFT) && !isLeftPressed){
            minoQueue.GetNowMino().Move(Key.LEFT);
            isLeftPressed = true;
        }
        else if(!Input.isDown(Input.LEFT))
            isLeftPressed = false;

        if(Input.isDown(Input.RIGHT) && !isRightPressed){
            minoQueue.GetNowMino().Move(Key.RIGHT);
            isRightPressed = true;
        }
        else if(!Input.isDown(Input.RIGHT))
            isRightPressed = false;

        if(Input.isDown(Input.UP) && !isUpPressed){
            minoQueue.GetNowMino().Rotation(1);
            isUpPressed = true;
        }
        else if(!Input.isDown(Input.UP))
            isUpPressed = false;

        if(Input.isDown(Input.DOWN) && !isDownPressed ){
            minoQueue.GetNowMino().Rotation(-1);
            isDownPressed = true;
        }
        else if(!Input.isDown(Input.DOWN))
            isDownPressed = false;
    }

    @Override
    public void update(float dt) {

        handleInput();

        minoQueue.GetNowMino().Update(dt);
        for(Mino mino : minoQueue.GetMinos())
            mino.Update(dt);
        for(Mino mino : minoQueue.GetMinoFloor())
            mino.Update(dt);
        for(IDrawable drawable: drawables)
            drawable.Update(dt);
    }

    @Override
    public void render() {
        Gdx.gl20.glClear(0x00004000);
        Gdx.gl20.glClearColor(0.2f, 0, 0, 1);
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        minoQueue.GetNowMino().Render(sb);
        for(Mino mino : minoQueue.GetMinos()) {
            mino.Render(sb);
        }
        for(Mino mino : minoQueue.GetMinoFloor()) {
            mino.Render(sb);
        }

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
