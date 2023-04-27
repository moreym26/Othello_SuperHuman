package com.example.othello_godmaster.view;

import com.example.GameMainActivity;
import com.example.LocalGame;
import com.example.gameConfiguration.GameConfig;
import com.example.gameConfiguration.GamePlayerType;
import com.example.infoMessage.GameState;
import com.example.othello_godmaster.infoMessage.OthelloState;

import java.util.ArrayList;

public class OthelloMainActivity extends GameMainActivity {
    public static final int PORT_NUMBER = 5213;
    @Override
    public GameConfig createDefaultConfig() {
        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Create a game configuration class for Tic-tac-toe
        GameConfig defaultConfig = new GameConfig(playerTypes, 2,2, "Tic-Tac-Toe", PORT_NUMBER);

        // Add the default players
        defaultConfig.addPlayer("Human", 0); // yellow-on-blue GUI
        defaultConfig.addPlayer("Computer", 3); // dumb computer player

        // Set the initial information for the remote player
        defaultConfig.setRemoteData("Remote Player", "", 1); // red-on-yellow GUI

        //done!
        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame(GameState gameState) {
        if(gameState == null)
            return new OthelloLocalGame();
        return new OthelloLocalGame((OthelloState) gameState);
    }




    //create default config
    //create local game
    //look at tictacto to
}
