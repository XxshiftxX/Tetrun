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

        return true;
    }

    public boolean keyUp(int k) {
        if(k == Keys.Z) {
            Input.setKeys(Input.BUTTON1, false);
        }
        if(k == Keys.X) {
            Input.setKeys(Input.BUTTON2, false);
        }

        return true;
    }

}
