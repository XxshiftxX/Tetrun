package com.tetrun.game.states;

import static com.tetrun.game.handlers.B2DVars.PPM;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.tetrun.game.handlers.B2DVars;
import com.tetrun.game.handlers.GameStateManager;
import com.tetrun.game.handlers.MyContactListener;
import com.tetrun.game.handlers.MyInput;
import com.tetrun.game.main.Game;

public class Play extends GameState {
    private float mapPos = 0;
    private World world;
    private Box2DDebugRenderer b2dr;

    private OrthographicCamera b2dCam;

    private Body mapBody;
    private Body playerBody;
    private MyContactListener cl;

    public Play(GameStateManager gsm) {
        super(gsm);

        cl = new MyContactListener();

        world = new World(new Vector2(0, -9.18f), true);
        world.setContactListener(cl);
        b2dr = new Box2DDebugRenderer();

        // create platform
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((160 + mapPos) / PPM, 120 / PPM);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        mapBody = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(90 / PPM, 15 / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = B2DVars.BIT_GROUND;
        fixtureDef.filter.maskBits = B2DVars.BIT_PLAYER;
        mapBody.createFixture(fixtureDef).setUserData("Ground");

        // create player
        bodyDef.position.set(160 / PPM, 200 / PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        playerBody = world.createBody(bodyDef);

        shape.setAsBox(15 / PPM, 15 / PPM);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fixtureDef.filter.maskBits = B2DVars.BIT_GROUND;
        playerBody.createFixture(fixtureDef).setUserData("Player");

        // create foot sensor
        shape.setAsBox(2 / PPM, 2 / PPM, new Vector2(0, -15/PPM), 0);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fixtureDef.filter.maskBits = B2DVars.BIT_GROUND;
        fixtureDef.isSensor = true;
        playerBody.createFixture(fixtureDef).setUserData("foot");

        // set up box2d cam
        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false, Game.V_WIDTH / PPM, Game.V_HEIGHT / PPM);
    }

    @Override
    public void handleInput() {
        if(MyInput.isPressed(MyInput.BUTTON1) && cl.isPlayerOnGround())
        {
            playerBody.applyForceToCenter(0, 200, true);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        mapPos += dt * 20;
        mapBody.setTransform((160 + mapPos) / PPM, 120 / PPM, 0);
        System.out.println(mapPos);
        world.step(dt, 6, 2);
    }

    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        b2dr.render(world, b2dCam.combined);
    }

    @Override
    public void dispose() {

    }
}
