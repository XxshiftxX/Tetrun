package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.entities.IDrawable;
import com.mygdx.game.entities.Mino;
import com.mygdx.game.entities.MinoType;
import com.mygdx.game.entities.Player;
import com.mygdx.game.handlers.GameStateManager;
import com.mygdx.game.handlers.Input;
import com.mygdx.game.handlers.MinoQueue;

import java.util.ArrayList;

public class Play extends GameState {
    public static Play currentPlay;
    public Boolean isMinoMoving = false;
    public World world;
    private Box2DDebugRenderer b2dr;
    private float minoSpeed = 2000;
    private float minoMoveTimeStack = 0;
    private ArrayList<IDrawable> drawables = new ArrayList<IDrawable>();
    private MinoQueue minoQueue = new MinoQueue();

    public Play(GameStateManager gsm)
    {
        super(gsm);

        world = new World(new Vector2(0, -9.8f), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(160, 120);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 5);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        body.createFixture(fdef);

        currentPlay = this;
        minoQueue.DecideMino();
        drawables.add(new Player());
    }

    @Override
    public void handleInput() {

        if(Input.isPressed(Input.BUTTON1)){


            System.out.println("pressed z");
        }
        if(Input.isDown((Input.BUTTON2))){


            System.out.println("hold X");
        }

        if(Input.isPressed(Input.LEFT)){
            minoQueue.GetMinos()[0].Confirm();
        }

        if(Input.isPressed(Input.RIGHT)){
            minoQueue.GetMinos()[0].direction++;
            if(minoQueue.GetMinos()[0].direction >= 4)
                minoQueue.GetMinos()[0].direction = 0;

            minoQueue.GetMinos()[0].Confirm();
        }

        if(Input.isPressed(Input.UP)){

        }

        if(Input.isPressed(Input.DOWN)){

        }

    }

    @Override
    public void update(float dt) {
        world.step(dt, 6, 2);
        minoMoveTimeStack += dt;
        handleInput();

        if(isMinoMoving) isMinoMoving = false;
        if(minoMoveTimeStack > minoSpeed)
            minoMoveTimeStack %= minoSpeed;

        for(Mino mino : minoQueue.GetMinos())
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

        for(Mino mino : minoQueue.GetMinos())
            mino.Render(sb);

        for(IDrawable drawable: drawables)
        {
            drawable.Render(sb);
        }
        sb.end();
        b2dr.render(world, cam.combined);
    }

    @Override
    public void dispose() {
        currentPlay = null;
    }
}
