package com.example.othello_superhuman.view;

import com.example.GameFramework.Game;
import com.example.GameFramework.LocalGame;
import com.example.GameFramework.actionMessage.GameAction;
import com.example.othello_superhuman.actionMessage.OthelloMoveAction;
import com.example.othello_superhuman.infoMessage.OthelloState;
import com.example.GameFramework.players.GamePlayer;
import com.example.GameFramework.utilities.GameTimer;
import com.example.GameFramework.utilities.Tickable;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
public class OthelloLocalGame extends LocalGame {


    public OthelloLocalGame(){
        super();

        super.state = new OthelloState();
    }

    public OthelloLocalGame(OthelloState othelloState){
        super();

        super.state = new OthelloState(othelloState);
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new OthelloState(((OthelloState) state)));
    }

    @Override
    protected boolean canMove(int playerIdx) {
        //Player id 0 is black. Player id 1 is white
        if(((OthelloState)state).isBlackTurn && playerIdx == 0 && ((OthelloState)(state)).moveAvailable()){
            return true;
        }
        if(!(((OthelloState)state).isBlackTurn) && playerIdx == 1 && ((OthelloState)(state)).moveAvailable()){
            return true;
        }
        else
            return false;
    }

    @Override
    protected String checkIfGameOver() {
        OthelloState state = (OthelloState) super.state;
        state.endGame();
        if(state.gameOver){
            return "Game is Over";
        }
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        OthelloMoveAction oa = (OthelloMoveAction) action;
        OthelloState state = (OthelloState) super.state;

        int row = oa.getRow();
        int col = oa.getCol();

        if(state.isValidMove(row, col)){
            if(state.isBlackTurn) {
                state.dumbMakeMove('b', row, col);
            }
            else {
                state.dumbMakeMove('w', row, col);
            }
            state.isBlackTurn = !(state.isBlackTurn);
            if(!(state.moveAvailable())){
                state.isBlackTurn = !(state.isBlackTurn);
            }
            super.state = state;

            return true;
        }
        else
            return false;
    }
    //tict
    //dont forget start
    //Tag for logging

}
