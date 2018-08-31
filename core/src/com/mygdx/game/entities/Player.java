package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player implements IDrawable {

    private long preUpdateTime = 0;

    @Override
    public void Update(long deltaTime) {
        System.out.println(deltaTime);
    }

    @Override
    public void Render(SpriteBatch sb) {

    }
}
