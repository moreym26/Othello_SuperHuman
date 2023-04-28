package com.example.othello_superhuman.players;


import android.os.Handler;
import android.util.Log;

import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.othello_superhuman.actionMessage.OthelloMoveAction;
import com.example.othello_superhuman.infoMessage.OthelloState;
import com.example.GameFramework.players.GameComputerPlayer;

import java.util.Random;

//GOD-MODE AI



public class OthelloComputerPlayer2 extends GameComputerPlayer {
    public OthelloComputerPlayer2(String name) {
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
            sleep(3);
            int[] a = this.godAIMove(othelloState);
            if (a[0] != -1 && a[1] != -1) {
                game.sendAction(new OthelloMoveAction(this, a[0], a[1]));
            }
        }
    }

    public int[] godAIMove(OthelloState os) {
        boolean haveMoved = false;
        Random random = new Random(100);

        if (!os.isBlackTurn && !os.isDumb) {//if its the computers turn
            // Iterate through the board until you find an empty spot that is a valid move,
            // Then place piece
            if (50 > random.nextInt()) {//if the random number is less than 50, start searching from the top of the board
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        //corners
                        if (haveMoved == false && os.isValidMove(i, j) && ((i == 0 && j == 0) || (i == 0 && j == 8) || (i == 8 && j == 0) || (i == 8 && j == 8))) {
                            //flip(i, j);
                            //board[i][j] = 'w';//puts white piece
                            int[] a = {i, j};
                            return a;
                            //haveMoved = true;
                            //break;
                        } else if (haveMoved == false && os.isValidMove(i, j) && (i == 0 || j == 0)) {
                            //flip(i, j);
                            //board[i][j] = 'w';//puts white piece
                            int[] a = {i, j};
                            return a;
                            //haveMoved = true;
                            //break;
                        } else if (haveMoved == false && os.isValidMove(i, j)) {
                            //flip(i, j);
                            //board[i][j] = 'w';//puts white piece
                            int[] a = {i, j};
                            return a;
                            //haveMoved = true;
                            //break;
                        }
                    }
                }
            }
            else {//if the random number is greater than 50, start searching from the bottom of the board
                for (int i = 8; i >0; i--) {
                    for (int j = 8; j > 0; j--) {
                        //corners
                        if (haveMoved == false && os.isValidMove(i, j) && ((i == 0 && j == 0) || (i == 0 && j == 8) || (i == 8 && j == 0) || (i == 8 && j == 8))) {
                            //flip(i, j);
                            //board[i][j] = 'w';//puts white piece
                            int[] a = {i, j};
                            return a;
                            //haveMoved = true;
                            //break;
                        } else if (haveMoved == false && os.isValidMove(i, j) && (i == 0 || j == 0)) {
                            //flip(i, j);
                            //board[i][j] = 'w';//puts white piece
                            int[] a = {i, j};
                            return a;
                            //haveMoved = true;
                            ////break;
                        } else if (haveMoved == false && os.isValidMove(i, j)) {
                            //flip(i, j);
                            //board[i][j] = 'w';//puts white piece
                            int[] a = {i, j};
                            return a;
                            //haveMoved = true;
                            //break;
                        }
                    }
                }
            }

        }
        else {
            //if its not the computers turn, don't do anything
        }
        return null;
    }
}



