package com.mygdx.game.handlers;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Mino;


// 순서는 U -> R -> D -> L
public class MinoContainer {

    public Vector2[][][] minoPattern =
            {
                    { //T
                            {new Vector2(-1, 0), new Vector2(0, 0), new Vector2(0, 1), new Vector2(1, 0)},
                            {new Vector2(0, 1), new Vector2( 0, 0), new Vector2(1, 0), new Vector2(0, -1)},
                            {new Vector2(-1, 0), new Vector2( 0, 0), new Vector2(1, 0), new Vector2(0, -1)},
                            {new Vector2(-1, 0), new Vector2( 0, 0), new Vector2(0, 1), new Vector2(0, -1)},
                    },
                    { // L
                            {new Vector2(0, 2), new Vector2(0, 1), new Vector2(0, 0), new Vector2(1, 0)},
                            {new Vector2(-1, -1), new Vector2(-1, 0), new Vector2(0, 0), new Vector2(1, 0)},
                            {new Vector2(-1, 1), new Vector2(0, 1), new Vector2(0, 0), new Vector2(0, -1)},
                            {new Vector2(-1, 0), new Vector2(0, 0), new Vector2(1, 0), new Vector2(1, 1)},
                    },
                    { // J
                            {new Vector2(-1, -1), new Vector2(0, -1), new Vector2(0, 0), new Vector2(0, 1)},
                            {new Vector2(-1, 1), new Vector2(-1, 0), new Vector2(0, 0), new Vector2(1, 0)},
                            {new Vector2(0, 1), new Vector2(1, 1), new Vector2(0, 0), new Vector2(0, -1)},
                            {new Vector2(-1, 0), new Vector2(0, 0), new Vector2(1, 0), new Vector2(1, -1)},
                    },
                    { // S
                            {new Vector2(-1, 1), new Vector2(-1, 0), new Vector2(0, 0), new Vector2(0, -1)},
                            {new Vector2(-1, -1), new Vector2(0, -1), new Vector2(0, 0), new Vector2(1, 0)},
                            {new Vector2(-1, 1), new Vector2(-1, 0), new Vector2(0, 0), new Vector2(0, -1)},
                            {new Vector2(-1, -1), new Vector2(0, -1), new Vector2(0, 0), new Vector2(1, 0)},
                    },
                    { // Z
                            {new Vector2(1, 1), new Vector2(1, 0), new Vector2(0, 0), new Vector2(0, -1)},
                            {new Vector2(-1, 0), new Vector2(0, 0), new Vector2(0, -1), new Vector2(1, -1)},
                            {new Vector2(1, 1), new Vector2(1, 0), new Vector2(0, 0), new Vector2(0, -1)},
                            {new Vector2(-1, 0), new Vector2(0, 0), new Vector2(0, -1), new Vector2(1, -1)},
                    },
                    { // O
                            {new Vector2(0, 0), new Vector2(-1, 0), new Vector2(0, 1), new Vector2(-1, 1)},
                            {new Vector2(0, 0), new Vector2(-1, 0), new Vector2(0, 1), new Vector2(-1, 1)},
                            {new Vector2(0, 0), new Vector2(-1, 0), new Vector2(0, 1), new Vector2(-1, 1)},
                            {new Vector2(0, 0), new Vector2(-1, 0), new Vector2(0, 1), new Vector2(-1, 1)},
                    },
                    { // I
                            {new Vector2(0, -2), new Vector2(0, -1), new Vector2(0, 0), new Vector2(0, 1)},
                            {new Vector2(-2, 0), new Vector2(-1, 0), new Vector2(0, 0), new Vector2(1, 0)},
                            {new Vector2(0, -2), new Vector2(0, -1), new Vector2(0, 0), new Vector2(0, 1)},
                            {new Vector2(-2, 0), new Vector2(-1, 0), new Vector2(0, 0), new Vector2(1, 0)},
                    },
            };
}

