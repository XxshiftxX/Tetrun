package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.tetrun;
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
    private Vector2[] shape;

    public Mino(Texture texture, MinoType minoType, Vector2 pos) {
        this.texture = texture;
        this.minoType = minoType;

        switch(minoType)
        {
            case L:
                shape = new Vector2[]{
                    new Vector2(0, -1),
                    new Vector2(0, 1),
                    new Vector2(1, -1)
                };
                break;
            case J:
                shape = new Vector2[]{
                        new Vector2(0, -1),
                        new Vector2(0, 1),
                        new Vector2(-1, -1)
                };
                break;
        }
        position = pos;
    }

    @Override
    public void Update() {

    }

    @Override
    public void Render(SpriteBatch sb) {
        sb.draw(texture, position.x * tetrun.UNIT, position.y * tetrun.UNIT, tetrun.UNIT, tetrun.UNIT);

        // 중심축 기준으로 shape에 있는 나머지 블럭을 Draw
        for(Vector2 v: shape)
            sb.draw(texture, (position.x + v.x) * tetrun.UNIT, (position.y + v.y) * tetrun.UNIT, tetrun.UNIT, tetrun.UNIT);
    }

    public void Spin()
    {
        throw new NotImplementedException();
    }
}
