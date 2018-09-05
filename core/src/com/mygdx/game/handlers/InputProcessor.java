package com.mygdx.game.handlers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class InputProcessor extends InputAdapter{

    public boolean keyDown(int k){
        if(k == Keys.Z) {
            Input.setKeys(Input.BUTTON1, true);
        }
        if(k == Keys.X) {
            Input.setKeys(Input.BUTTON2, true);
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

        return true;
    }

    public boolean keyUp(int k) {
        if(k == Keys.Z) {
            Input.setKeys(Input.BUTTON1, false);
        }
        if(k == Keys.X) {
            Input.setKeys(Input.BUTTON2, false);
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

        return true;
    }

}
