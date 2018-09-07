package com.mygdx.game.handlers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class InputProcessor extends InputAdapter{

    public boolean keyDown(int k){
        if(k == Keys.Z) {
            Input.setKeys(Input.Z, true);
        }
        if(k == Keys.X) {
            Input.setKeys(Input.X, true);
        }
        if(k == Keys.LEFT) {
            Input.setKeys(Input.LEFT, true);
        }
        if(k == Keys.UP) {
            Input.setKeys(Input.UP, true);
        }
        if(k == Keys.RIGHT) {
            Input.setKeys(Input.RIGHT, true);
        }
        if(k == Keys.DOWN) {
            Input.setKeys(Input.DOWN, true);
        }
        if(k == Keys.SPACE) {
            Input.setKeys(Input.SPACEBAR, true);
        }
        if(k == Keys.C) {
            Input.setKeys(Input.C, true);
        }
        return true;
    }

    public boolean keyUp(int k) {
        if(k == Keys.Z) {
            Input.setKeys(Input.Z, false);
        }
        if(k == Keys.X) {
            Input.setKeys(Input.X, false);
        }
        if(k == Keys.LEFT) {
            Input.setKeys(Input.LEFT, false);
        }
        if(k == Keys.UP) {
            Input.setKeys(Input.UP, false);
        }
        if(k == Keys.RIGHT) {
            Input.setKeys(Input.RIGHT, false);
        }
        if(k == Keys.DOWN) {
            Input.setKeys(Input.DOWN, false);
        }
        if(k == Keys.SPACE) {
            Input.setKeys(Input.SPACEBAR, false);
        }
        if(k == Keys.C) {
            Input.setKeys(Input.C, true);
        }

        return true;
    }

}
