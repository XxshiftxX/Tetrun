package com.tetrun.game.blocks;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.tetrun.game.handlers.B2DVars;

import static com.tetrun.game.handlers.B2DVars.PPM;

public abstract class Block
{
    protected Vector2 position;
    protected Body body;

    public Body getBody() { return getBody(); }

    protected Block(World world, float posX, float posY, float sizeX, float sizeY)
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
    }

    public abstract void update(float dt);
}
