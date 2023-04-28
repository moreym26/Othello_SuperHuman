package com.example.othello_superhuman.players;

import android.view.MotionEvent;
import android.view.View;

import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.players.GameHumanPlayer;
import com.example.GameFramework.utilities.Logger;
import com.example.othello_superhuman.R;
import com.example.othello_superhuman.actionMessage.OthelloMoveAction;
import com.example.othello_superhuman.infoMessage.OthelloState;
import com.example.othello_superhuman.view.OthelloView;

public class OthelloHumanPlayer1 extends GameHumanPlayer implements View.OnTouchListener {
    //Tag for logging
    private static final String TAG = "OthelloHumanPlayer1";

    // the surface view
    private OthelloView surfaceView;

    private int layoutId;

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public OthelloHumanPlayer1(String name, int layoutId) {
        super(name);
        this.layoutId = layoutId;
    }

    @Override
    public void receiveInfo(GameInfo info) {
        if(!(info instanceof OthelloState)){
            this.flash(200, 10);
            return;
        }
        else{
            surfaceView.setGameState((OthelloState)info);
            surfaceView.invalidate();
        }
    }

    @Override
    protected void gameIsOver(String msg) {
        surfaceView.getGameState().gameOver = true;
        surfaceView.invalidate();
        //super.gameIsOver(msg);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // ignore if not an "up" event

        OthelloState gs = ((OthelloState)(game.getGameState()));

        int x = (int)event.getX();
        int y = (int)event.getY();
        int row = -1;
        int col = -1;

        //if its out of bounds return false
        if (x < 400 || y > 1200 || x < 100 || y > 900) {
            return false;
        }
        //X coordinates
        if (x > 400 && x < 500) {
            col = 0;
        } else if (x < 600) {
            col = 1;
        } else if (x < 700) {
            col = 2;
        } else if (x < 800) {
            col = 3;
        } else if (x < 900) {
            col = 4;
        } else if (x < 1000) {
            col = 5;
        } else if (x < 1100) {
            col = 6;
        } else if (x < 1200) {
            col = 7;
        }
        //Y coordinates
        if (y > 100 && y < 200) {
            row = 0;
        } else if (y < 300) {
            row = 1;
        } else if (y < 400) {
            row = 2;
        } else if (y < 500) {
            row = 3;
        } else if (y < 600) {
            row = 4;
        } else if (y < 700) {
            row = 5;
        } else if (y < 800) {
            row = 6;
        } else if (y < 900) {
            row = 7;
        }
        if (row != -1 && col != -1){
            if(gs.isValidMove(row, col)){
                this.game.sendAction(new OthelloMoveAction(this, row, col));
            }
        }

        return false;

    }

    @Override
    public View getTopView() {
        return null;
    }



    @Override
    public void setAsGui(GameMainActivity activity) {

        // Load the layout resource for the new configuration
        activity.setContentView(layoutId);

        // set the surfaceView instance variable
        surfaceView = activity.findViewById(R.id.View);
        Logger.log("set listener","OnTouch");
        surfaceView.setOnTouchListener(this);
    }
    //holds the ui




}
