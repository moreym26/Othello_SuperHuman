package com.example.othello_godmaster.view;

import com.example.Game;
import com.example.LocalGame;
import com.example.actionMessage.GameAction;
import com.example.othello_godmaster.actionMessage.OthelloMoveAction;
import com.example.othello_godmaster.infoMessage.OthelloState;
import com.example.players.GamePlayer;
import com.example.utilities.GameTimer;
import com.example.utilities.Tickable;

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
        if(!((OthelloState)state).isBlackTurn && playerIdx == 1 && ((OthelloState)(state)).moveAvailable()){
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

        char[][] board = state.getBoard();
        //isValid move variation. Look in OthelloState for details
        if (!(board[row][col] == 'e')) {
            return false;
        }
        else {
            boolean isEmpty = false;
            int i;
            int j;
            if (state.isBlackTurn) {
                //Check above the piece
                if (row - 1 != -1) {
                    //Check if space in direction is opposite color
                    if (board[row - 1][col] == 'w') {
                        while(!isEmpty) { //Until an empty space or end of board is reached, go through the direction on board
                            for (i = row - 1; i > -1; i--) {
                                if(board[i][col] == 'b' && !isEmpty){ //If at some point, a space in the direction has a black piece, there is a valid move for the direction
                                    state.flip(row, col);
                                    return true;
                                }
                                else if(board[i][col] == 'e' || i == 0){ //Stop at empty space or end of board
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check below the piece
                if (row + 1 != 8) {
                    if (board[row + 1][col] == 'w') {
                        while(!isEmpty) {
                            for (i = row + 1; i < 8; i++) {
                                if (board[i][col] == 'b'&& !isEmpty) {
                                    state.flip(row, col);
                                    return true;
                                }
                                else if (board[i][col] == 'e' || i == 7) {
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check to the left of the piece
                if (col - 1 != -1) {
                    if (board[row][col - 1] == 'w') {
                        while(!isEmpty) {
                            for (i = col - 1; i > -1; i--) {
                                if (board[row][i] == 'b'&& !isEmpty) {
                                    state.flip(row, col);
                                    return true;
                                }
                                else if (board[row][i] == 'e' || i ==0 ) {
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check to the right of the piece
                if (col + 1 != 8) {
                    if (board[row][col + 1] == 'w') {
                        while(!isEmpty){
                            for (i = col + 1; i < 8; i++) {
                                if (board[row][i] == 'b'&& !isEmpty) {
                                    state.flip(row, col);
                                    return true;
                                }
                                else if(board[row][i] == 'e' || i == 7){
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check diagonally top left of the piece
                if (col - 1 != -1 && row - 1 != -1) {
                    if (board[row - 1][col - 1] == 'w') {
                        i = row - 1;
                        j = col - 1;
                        while(!isEmpty){
                            while (i > -1 && j > -1) {
                                if (board[i][j] == 'b' && !isEmpty) {
                                    state.flip(row, col);
                                    return true;
                                }
                                else if(board[i][j] == 'e' || i == 0 || j ==0){
                                    isEmpty = true;
                                }
                                i--;
                                j--;
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check diagonally bottom right of the piece
                if (col + 1 != 8 && row + 1 != 8) {
                    if (board[row + 1][col + 1] == 'w') {
                        i = row + 1;
                        j = col + 1;
                        while (i < 8 && j < 8 && !isEmpty) {
                            if (board[i][j] == 'b' && !isEmpty) {
                                state.flip(row, col);
                                return true;
                            }
                            else if(board[i][j] == 'e' || i == 7 || j ==7){
                                isEmpty = true;
                            }
                            i++;
                            j++;
                        }
                    }
                }
                isEmpty = false;
                //Check diagonally bottom left of the piece
                if (col - 1 != -1 && row + 1 != 8) {
                    if (board[row + 1][col - 1] == 'w') {
                        i = row + 1;
                        j = col - 1;
                        while (i < 8 && j > -1 && !isEmpty) {
                            if (board[i][j] == 'b' && !isEmpty) {
                                state.flip(row, col);
                                return true;
                            }
                            else if(board[i][j] == 'e' || i == 7 || j == 0){
                                isEmpty = true;
                            }
                            i++;
                            j--;
                        }

                    }
                }
                isEmpty = false;
                //Check diagonally top right of the piece
                if (col + 1 != 8 && row - 1 != -1) {
                    if (board[row - 1][col + 1] == 'w') {
                        i = row - 1;
                        j = col + 1;
                        while (i > -1 && j < 8 && !isEmpty) {
                            if (board[i][j] == 'b' && !isEmpty) {
                                state.flip(row, col);
                                return true;
                            }
                            else if(board[i][j] == 'e' || i == 0 || j == 7){
                                isEmpty = true;
                            }
                            i--;
                            j++;
                        }

                    }
                }
            }
            else {
                //If it is whites turn
                //Check above the piece
                if (row - 1 != -1) {
                    //Check if space in direction is opposite color
                    if (board[row - 1][col] == 'b') {
                        while(!isEmpty) { //Until an empty space is reached, go through the direction on board
                            for (i = row - 1; i > -1; i--) {
                                if(board[i][col] == 'w'&& !isEmpty){ //If at some point, a space in the direction has a black piece, there is a valid move for the direction
                                    state.flip(row, col);
                                    return true;
                                }
                                else if(board[i][col] == 'e' || i == 0){ //Stop at empty space
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check below the piece
                if (row + 1 != 8) {
                    if (board[row + 1][col] == 'b') {
                        while(!isEmpty) {
                            for (i = row + 1; i < 8; i++) {
                                if (board[i][col] == 'w'&& !isEmpty) {
                                    state.flip(row, col);
                                    return true;
                                }
                                else if (board[i][col] == 'e' || i == 7) {
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check to the left of the piece
                if (col - 1 != -1) {
                    if (board[row][col - 1] == 'b') {
                        while(!isEmpty) {
                            for (i = col - 1; i > -1; i--) {
                                if (board[row][i] == 'w'&& !isEmpty) {
                                    state.flip(row, col);
                                    return true;
                                }
                                else if (board[row][i] == 'e' || i == 0) {
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check to the right of the piece
                if (col + 1 != 8) {
                    if (board[row][col + 1] == 'b') {
                        while(!isEmpty){
                            for (i = col + 1; i < 8; i++) {
                                if (board[row][i] == 'w'&& !isEmpty) {
                                    state.flip(row, col);
                                    return true;
                                }
                                else if(board[row][i] == 'e' || i == 7){
                                    isEmpty = true;
                                }
                            }
                        }
                    }
                }
                isEmpty = false;
                //Check diagonally top left of the piece
                if (col - 1 != -1 && row - 1 != -1) {
                    if (board[row - 1][col - 1] == 'b') {
                        i = row - 1;
                        j = col - 1;
                        while (i > -1 && j > -1 && !isEmpty) {
                            if (board[i][j] == 'w' && !isEmpty) {
                                state.flip(row, col);
                                return true;
                            }
                            else if(board[i][j] == 'e' || i== 8 || j ==0){
                                isEmpty = true;
                            }
                            i--;
                            j--;
                        }

                    }
                }
                isEmpty = false;
                //Check diagonally bottom right of the piece
                if (col + 1 != 8 && row + 1 != 8) {
                    if (board[row + 1][col + 1] == 'b') {
                        i = row + 1;
                        j = col + 1;

                        while (i < 8 && j < 8 && !isEmpty) {
                            if (board[i][j] == 'w' && !isEmpty) {
                                state.flip(row, col);
                                return true;
                            }
                            else if(board[i][j] == 'e' || i== 7 || j ==7){
                                isEmpty = true;
                            }
                            i++;
                            j++;
                        }

                    }
                }
                isEmpty = false;
                //Check diagonally bottom left of the piece
                if (col - 1 != -1 && row + 1 != 8) {
                    if (board[row + 1][col - 1] == 'b') {
                        i = row + 1;
                        j = col - 1;

                        while (i < 8 && j > -1 && !isEmpty) {
                            if (board[i][j] == 'w' && !isEmpty) {
                                state.flip(row, col);
                                return true;
                            }
                            else if(board[i][j] == 'e' || i==7 || j ==0){
                                isEmpty = true;
                            }
                            i++;
                            j--;
                        }

                    }
                }
                isEmpty = false;
                //Check diagonally top right of the piece
                if (col + 1 != 8 && row - 1 != -1) {
                    if (board[row - 1][col + 1] == 'b') {
                        i = row - 1;
                        j = col + 1;

                        while (i > -1 && j < 8 && !isEmpty) {
                            if (board[i][j] == 'w' && !isEmpty) {
                                state.flip(row, col);
                                return true;
                            }
                            else if(board[i][j] == 'e' || i ==0 || j==7){
                                isEmpty = true;
                            }
                            i--;
                            j++;
                        }

                    }
                }
            }
        }
        return false;
    }
    //tict
    //dont forget start
    //Tag for logging

}
