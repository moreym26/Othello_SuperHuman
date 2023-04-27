package com.example.othello_superhuman.players;


import android.os.Handler;
import android.util.Log;

import com.example.othello_superhuman.actionMessage.OthelloMoveAction;
import com.example.othello_superhuman.infoMessage.OthelloState;

import edu.up.cs301.game.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.game.GameFramework.infoMessage.IllegalMoveInfo;
import edu.up.cs301.game.GameFramework.infoMessage.NotYourTurnInfo;
import edu.up.cs301.game.GameFramework.players.GameComputerPlayer;
import edu.up.cs301.game.GameFramework.utilities.GameTimer;

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

        sleep(3);
            int[] a = othelloState.dumbAIMove();
            game.sendAction(new OthelloMoveAction(this, a[0], a[1]));
        }

    @Override
    public void tick(GameTimer timer) {

    }
}




