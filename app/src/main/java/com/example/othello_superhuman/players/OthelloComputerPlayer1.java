package com.example.othello_superhuman.players;


import android.os.Handler;
import android.util.Log;

import com.example.GameFramework.infoMessage.GameState;
import com.example.othello_superhuman.actionMessage.OthelloMoveAction;
import com.example.othello_superhuman.infoMessage.OthelloState;

import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.GameFramework.players.GameComputerPlayer;
import com.example.GameFramework.utilities.GameTimer;

//EASY AI



public class OthelloComputerPlayer1 extends GameComputerPlayer {
    public OthelloComputerPlayer1(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        if (info instanceof NotYourTurnInfo) return;
        //Ignore illegal move info too
        if (info instanceof IllegalMoveInfo) return;
        //just in case there is any other types of info, ignore it
        if(!(info instanceof OthelloState)){
            return;
        }

        OthelloState othelloState = new OthelloState((OthelloState) info);
        if(!(((OthelloState)(game.getGameState())).isBlackTurn)) {
            sleep(1);
            int[] a = this.dumbAIMove(othelloState);
            if (a[0] != -1 && a[1] != -1) {
                game.sendAction(new OthelloMoveAction(this, a[0], a[1]));
            }
        }
    }

    /*
     * Dumb AI moves
     * 100x100
     * 700x200
     * 1700x1200
     */
    public int[] dumbAIMove(OthelloState os) {
        boolean haveMoved = false;
        if (!os.isBlackTurn) {//if its the computers turn
            // Iterate through the board until you find an empty spot that is a valid move,
            // Then place piece
            for (int i = 0; i<8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (haveMoved == false && os.isValidMove(i, j)) {
                        //flip(i, j);
                        //board[i][j] = 'w';//puts white piece
                        haveMoved = true;
                        int[] a = {i, j};
                        return a;
                    }
                }
            }

        }
        else {
            //if its not the computers turn, don't do anything
        }
        int[] a = {-1, -1};
        return a;
    }

}

