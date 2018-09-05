package com.mygdx.game.handlers;

import com.mygdx.game.Game;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.Play;

import java.util.Stack;

public class GameStateManager {
    private Game game;

    private Stack<GameState> gameStates;

    private static final int PLAY = 10;

    public GameStateManager(Game game)
    {
        this.game = game;
        gameStates = new Stack<GameState>();
        pushState(PLAY);
    }

    public void update(float dt)
    {
        gameStates.peek().update(dt);
    }

    public Game game() { return game; }

    public void render()
    {
        gameStates.peek().render();
    }

    private GameState getState(int state)
    {
        if(state == PLAY) return new Play(this);
        return null;
    }

    public void setState(int state)
    {
        popState();
        pushState(state);
    }

    public void pushState(int state)
    {
        gameStates.push(getState(state));
    }

    public void popState()
    {
        GameState g = gameStates.pop();
        g.dispose();
    }
}
