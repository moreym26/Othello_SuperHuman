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
            sleep(1);
            int[] a = this.godAIMove(othelloState);
            if (a[0] != -1 && a[1] != -1 && a != null) {
                game.sendAction(new OthelloMoveAction(this, a[0], a[1]));
            }
        }
    }

    public int[] godAIMove(OthelloState os) {
        Random random = new Random(100);
        //Prioritize corner spots (guarantees a spot that won't be taken)
        //Then edges (gets a spot that is less likely to get captured)
        //If none of those options are available, find a spot in the board
        //by search top to bottom or vice versa.
        if (!os.isBlackTurn) {//if its the computers turn
            //Check the corners
            if(os.isValidMove(0,0)){
                int[] a = {0,0};
                return a;
            }
            if(os.isValidMove(0,7)){
                int[] a = {0,7};
                return a;
            }
            if(os.isValidMove(7,0)){
                int[] a = {7,0};
                return a;
            }
            if(os.isValidMove(7,7)){
                int[] a = {7,7};
                return a;
            }
            //Then check the edges
            for(int i = 0; i < 8; i++){ //Left Side
                if(os.isValidMove(i, 0)){
                    int[] a = {i, 0};
                    return a;
                }
            }
            for(int i = 0; i < 8; i++){//Right Side
                if(os.isValidMove(i, 7)){
                    int[] a = {i, 7};
                    return a;
                }
            }
            for(int i = 0; i < 8; i++){//Top Side
                if(os.isValidMove(0, i)){
                    int[] a = {0, i};
                    return a;
                }
            }
            for(int i = 0; i < 8; i++){//Bottom Side
                if(os.isValidMove(7, i)){
                    int[] a = {7, i};
                    return a;
                }
            }

            //Otherwise
            // Iterate through the board until you find an empty spot that is a valid move,
            // Then place piece
            if (50 > random.nextInt()) {//if the random number is less than 50, start searching from the top of the board
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if(os.isValidMove(i, j)){
                            int[] a = {i, j};
                            return a;
                        }
                    }
                }
            }
            else {//if the random number is greater than 50, start searching from the bottom of the board
                for (int i = 7; i >-1; i--) {
                    for (int j = 7; j > -1; j--) {
                        if(os.isValidMove(i, j)){
                            int[] a = {i, j};
                            return a;
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