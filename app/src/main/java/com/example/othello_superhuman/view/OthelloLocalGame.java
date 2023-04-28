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
            if(state.numWhitePieces> state.numBlackPieces){
                return "White Wins!!\n" + "White pieces: " + state.numWhitePieces + "\n" + "Black pieces: " + state.numBlackPieces + "\n";
            }
            if(state.numWhitePieces < state.numBlackPieces) {
                return "Black Wins!!\n" + "White pieces: " + state.numWhitePieces + "\n" + "Black pieces: " + state.numBlackPieces + "\n";

            }
            if(state.numBlackPieces == state.numWhitePieces){
                return "It's a Tie! No one wins!\n" + "White pieces: " + state.numWhitePieces + "\n" + "Black pieces: " + state.numBlackPieces + "\n";
            }
            //GameOverMessage
//            c.drawText("Game Over!", 480, 240, black);
//            black.setTextSize(75);
//            if(gameState.isTie){
//                c.drawText("Tie. No Winner!", 595, 310, black);
//            }
//            else{
//                if(gameState.blackWinner) {
//                    c.drawText("Black Wins!", 595, 310, black);
//                }
//                else {
//                    c.drawText("White Wins!", 595, 310, black);
//                }
//            }
//            //Score
//            String blackS = ""+gameState.numBlackPieces;
//            String whiteS = ""+gameState.numWhitePieces;
//            c.drawCircle(625, 425 , 100, black);
//            black.setTextSize(40);
//            c.drawText(blackS, 613, 560, black);
//            c.drawCircle(975, 425 , 100, white);
//            c.drawText(whiteS, 963, 560, black);
//
//            //Buttons
//            c.drawRect(525, 600, 725, 675, brownBox);
//            c.drawRect(875, 600, 1075, 675, brownBox);
//            black.setTextSize(50);
//            c.drawText("Exit", 575, 655, black);
//            black.setTextSize(50);
//            c.drawText("Restart", 895, 655, black);


//            return "Game is Over";
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
