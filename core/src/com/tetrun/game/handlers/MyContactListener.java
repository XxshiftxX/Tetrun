package com.tetrun.game.handlers;

import com.badlogic.gdx.physics.box2d.*;

public class MyContactListener implements ContactListener{
    private boolean playerOnGround;

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa.getUserData() != null && fa.getUserData().equals("foot"))
        {
            playerOnGround = true;
        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot"))
        {
            playerOnGround = true;
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa.getUserData() != null && fa.getUserData().equals("foot"))
        {
            playerOnGround = false;
        }
        if(fb.getUserData() != null && fb.getUserData().equals("foot"))
        {
            playerOnGround = false;
        }
    }

    public boolean isPlayerOnGround() { return playerOnGround; }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
