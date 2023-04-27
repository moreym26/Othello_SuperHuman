package com.example.othello_superhuman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.LocalGame;
import com.example.GameFramework.gameConfiguration.GameConfig;
import com.example.GameFramework.gameConfiguration.GamePlayerType;
import com.example.GameFramework.infoMessage.GameState;
import com.example.othello_superhuman.R;
import com.example.othello_superhuman.players.OthelloHumanPlayer1;
import com.example.othello_superhuman.players.OthelloComputerPlayer1;
import com.example.othello_superhuman.players.OthelloComputerPlayer2;
import com.example.GameFramework.players.GamePlayer;
import com.example.othello_superhuman.infoMessage.OthelloState;
import com.example.othello_superhuman.view.OthelloLocalGame;

import java.util.ArrayList;

public class MainActivity extends GameMainActivity {
    public static final int PORT_NUMBER = 5213;

    @Override
    public GameConfig createDefaultConfig() {
        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // yellow-on-blue GUI
        playerTypes.add(new GamePlayerType("Local Human Player (blue-yellow)") {
            public GamePlayer createPlayer(String name) {
                return new OthelloHumanPlayer1(name, R.layout.ttt_human_player1);
            }
        });

        // red-on-yellow GUI
        playerTypes.add(new GamePlayerType("Local Human Player (yellow-red)") {
            public GamePlayer createPlayer(String name) {
                return new OthelloHumanPlayer1(name, R.layout.ttt_human_player1_flipped);
            }
        });

        // note that most games don't require a second human player class
        playerTypes.add(new GamePlayerType("Local Human Player (game of 33)") {
            public GamePlayer createPlayer(String name) {
                return new OthelloHumanPlayer1(name, R.layout.ttt_human_player2);
            }
        });

        // dumb computer player
        playerTypes.add(new GamePlayerType("Computer Player (dumb)") {
            public GamePlayer createPlayer(String name) {
                return new OthelloComputerPlayer1(name);
            }
        });

        // smarter computer player
        playerTypes.add(new GamePlayerType("Computer Player (smart)") {
            public GamePlayer createPlayer(String name) {
                return new OthelloComputerPlayer2(name);
            }
        });

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

}