package com.tetrun.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.tetrun.game.main.Game;

public class Player extends B2DSprite {
    private int numPoint;
    private int totalPoint;

    public Player(Body body)
    {
        super(body);

        Texture tex = Game.res.getTexture("player");
        TextureRegion[] sprites = TextureRegion.split(tex, 110, 80)[0];

        setAnimation(sprites, 1/12f);
    }

    public void collectPoint() { numPoint++; }
    public int getNumPoints() { return numPoint; }
    public void setTotalPoints(int totalPoint) { this.totalPoint = totalPoint; }
    public int getTotalPoints() { return totalPoint; }
}
