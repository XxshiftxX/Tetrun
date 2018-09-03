package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;

public class Player implements IDrawable {
    private Texture texture;
    private TextureRegion[] animSprites;
    private Vector2 pos;
    private float timeSum = 0.0f;
    private float cycleTime = 0.05f;
    private int animIndex = 0;

    private static final int width = 4;

    public Player()
    {
        this.texture = new Texture(Gdx.files.internal("Sprite/Character_blue.png"));
        animSprites = TextureRegion.split(texture, 737 / width, 283)[0];
        pos = new Vector2(1, 10);
    }

    @Override
    public void Update(float deltaTime) {
        System.out.println(deltaTime);
        timeSum += deltaTime;
        if(timeSum >= cycleTime)
        {
            timeSum = 0.0f;
            ++animIndex;
            if(animIndex == width)
                animIndex = 0;
        }
    }

    @Override
    public void Render(SpriteBatch sb) {
        sb.draw(animSprites[animIndex], pos.x * Game.UNIT, pos.y * Game.UNIT, Game.UNIT * 3, Game.UNIT*3);
    }
}
