package com.tetrun.game.blocks;

import com.badlogic.gdx.physics.box2d.World;

public class IBlock extends Block {
    protected IBlock(World world, float posX, float posY, float sizeX, float sizeY) {
        super(world, posX, posY, sizeX, sizeY);
    }

    @Override
    public void update(float dt) {

    }
}
