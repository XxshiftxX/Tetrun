package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IDrawable
{
    void Update(float deltaTime);
    void Render(SpriteBatch sb);
}