package com.tetrun.game.states;

import static com.tetrun.game.handlers.B2DVars.BIT_GROUND;
import static com.tetrun.game.handlers.B2DVars.BIT_PLAYER;
import static com.tetrun.game.handlers.B2DVars.PPM;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.tetrun.game.handlers.*;
import com.tetrun.game.main.Game;

import java.util.ArrayList;
import java.util.List;

public class Play extends GameState {
    private World world;
    private Box2DDebugRenderer b2dr;

    private OrthographicCamera b2dCam;

    private ArrayList<Body> mapBodys = new ArrayList<Body>();
    private Body playerBody;
    private MyContactListener cl;

    private TiledMap tileMap;
    private float tileSize;
    private OrthoCachedTiledMapRenderer tmr;

    public Play(GameStateManager gsm) {
        super(gsm);

        // ContractListener 생성
        cl = new MyContactListener();

        // World 생성
        world = new World(new Vector2(0, -9.18f), true);
        world.setContactListener(cl);
        b2dr = new Box2DDebugRenderer();

        // Player 생성
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(250 / PPM, 200 / PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        playerBody = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(15 / PPM, 15 / PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fixtureDef.filter.maskBits = B2DVars.BIT_GROUND;
        playerBody.createFixture(fixtureDef).setUserData("Player");

        // Player 지면 센서 생성
        shape.setAsBox(14 / PPM, 2 / PPM, new Vector2(0, -15/PPM), 0);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fixtureDef.filter.maskBits = B2DVars.BIT_GROUND;
        fixtureDef.isSensor = true;
        playerBody.createFixture(fixtureDef).setUserData("foot");

        // Flatform 생성
        // createFlatform(30, 60, 60, 15);
        // createFlatform(200, 30, 60, 15);

        // box2d 카메라 설정
        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false, Game.V_WIDTH / PPM, Game.V_HEIGHT / PPM);


        // ---------------------------------------------------------------------

        tileMap = new TmxMapLoader().load("maps/blocks.tmx");
        tmr = new OrthoCachedTiledMapRenderer(tileMap);

        TiledMapTileLayer layer =
                (TiledMapTileLayer) tileMap.getLayers().get("layer1");

        tileSize = layer.getTileWidth();

        for(int x = 0; x < layer.getWidth(); x++)
        {
            for(int y = 0; y < layer.getHeight(); y++)
            {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);

                if(cell == null)
                    continue;
                if(cell.getTile() == null)
                    continue;

                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set(
                        (x + 0.5f) * tileSize / PPM,
                        (y + 0.5f) * tileSize / PPM
                );

                ChainShape cs = new ChainShape();
                Vector2[] v = new Vector2[3];
                v[0] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
                v[1] = new Vector2(-tileSize / 2 / PPM, tileSize / 2 / PPM);
                v[2] = new Vector2(tileSize / 2 / PPM, tileSize / 2 / PPM);

                cs.createChain(v);
                fixtureDef.friction = 0;
                fixtureDef.shape = cs;
                fixtureDef.filter.categoryBits = BIT_GROUND;
                fixtureDef.filter.maskBits = BIT_PLAYER;
                fixtureDef.isSensor = false;
                Body b = world.createBody(bodyDef);
                b.createFixture(fixtureDef);
                mapBodys.add(b);
            }
        }
    }

    @Override
    public void handleInput() {
        // BUTTON1 (z) 입력 구현
        if(MyInput.isPressed(MyInput.BUTTON1) && cl.isPlayerOnGround())
        {
            playerBody.applyForceToCenter(0, 200, true);
        }
        if(MyInput.isPressed(MyInput.BUTTON2))
        {
            createFlatform(40, 40, 60, 15);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

        // 맵 이동
        for(Body b: mapBodys)
        {
            b.setTransform(b.getTransform().getPosition().x - dt, b.getTransform().getPosition().y, 0);
        }

        // player 물리 계산 실행
        playerBody.setAwake(true);
        if(playerBody.getPosition().x != 250 / PPM)
            System.out.println("게임 오버!");
        world.step(dt, 6, 2);
    }

    @Override
    public void render() {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tmr.setView(cam);
        tmr.render();

        b2dr.render(world, b2dCam.combined);
    }

    @Override
    public void dispose()
    {

    }

    private void createFlatform(float posX, float posY, float sizeX, float sizeY)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(posX / PPM, posY / PPM);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sizeX / PPM, sizeY / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = B2DVars.BIT_GROUND;
        fixtureDef.filter.maskBits = B2DVars.BIT_PLAYER;
        body.createFixture(fixtureDef).setUserData("Ground");
        mapBodys.add(body);
    }
}
