package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.Game;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Mino implements IDrawable
{
    // 테트리미노 종류
    private MinoType minoType;
    // 블럭 텍스쳐
    private Texture texture;
    // 블럭 위치
    public Vector2 position;
    // 블럭 모양
    private Vector2[][] shape = new Vector2[4][4];
    private int direction = 0;          // 블럭 방향
    private int color = 0;              // 블럭 색상
    private boolean isPutted = false;   // 블럭이 놓여졌는지 확인

    public Mino()
    {
        texture = new Texture(Gdx.files.internal("badlogic.jpg"));
        position = new Vector2();
    }

    public Mino(Texture texture, MinoType minoType, Vector2 pos) {
        this.texture = texture;
        this.minoType = minoType;
        position = pos;

        //Confirm();
    }

    public Vector2[][] getShape() {
        return shape;
    }


    public void SetPosition(float x, float y)
    {
        this.position.x = x;
        this.position.y = y;
    }

    @Override
    public void Update(float deltaTIme) {
        if(isPutted)
            position.x += 0.1f;
    }

    @Override
    public void Render(SpriteBatch sb) {
//        // 중심축 기준으로 shape에 있는 나머지 블럭을 Draw
        for (Vector2 v : shape[direction]) {
            sb.draw(texture, (position.x + v.x) * Game.UNIT, (position.y + v.y) * Game.UNIT, Game.UNIT, Game.UNIT);
        }
    }

    // MinoContainter로 부터 Mino 원형을 지정받기 위한 Setter
    public void SetMino(Vector2 mino[][])
    {
        for(int i = 0; i < 4; ++i)
        {
            shape[i] = mino[i].clone();
        }
    }

    public void Confirm()
    {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Game.UNIT, Game.UNIT);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position.x * Game.UNIT, position.y * Game.UNIT);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        Body body = Game.world.createBody(bodyDef);
        body.createFixture(fixtureDef);
    }

    public void Spin()
    {
        throw new NotImplementedException();
    }
}
