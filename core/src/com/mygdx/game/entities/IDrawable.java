package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IDrawable
{
    void Update(long deltaTime);
    void Render(SpriteBatch sb);
}