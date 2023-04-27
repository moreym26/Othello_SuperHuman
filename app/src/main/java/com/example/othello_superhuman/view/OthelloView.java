package com.example.othello_superhuman.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import com.example.othello_superhuman.infoMessage.OthelloState;

public class OthelloView extends SurfaceView {

    private OthelloState gameState;

    int startX;
    int startY;
    int endX;
    int endY;

    Paint black = new Paint();
    Paint white = new Paint();
    Paint green = new Paint();
    Paint sage = new Paint();
    Paint othello = new Paint();
    Paint brownBox = new Paint();

    Paint lightGrey = new Paint();

    public OthelloView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gameState = new OthelloState();

        black.setColor(0xFF000000);
        white.setColor(0xFFFFFFFF);
        green.setColor(0xFF29AB70);
        sage.setColor(0xff65a765);
        othello.setColor(Color.WHITE);
        othello.setStyle(Paint.Style.FILL);
        brownBox.setColor(0xFFceb396);
        lightGrey.setColor(0x10D3D3D3);

        setBackgroundColor(Color.WHITE);

    }
    public void setGameState(OthelloState state){
        this.gameState = state;
    }

    public OthelloState getGameState(){ return gameState;}

    public void onDraw(Canvas c) {
        super.onDraw(c);
        startX = 400;
        startY = 100;
        endX = 1200;
        endY = 100;

        if (!gameState.gameOver){
            c.drawRect(400, 100, 1200, 900, green);
            //vertical lines
            for (int i = 0; i < 9; i++) {
                c.drawLine(startX, startY, endX, endY, black);
                startY += 100;
                endY += 100;
            }

            startX = 400;
            startY = 100;
            endX = 400;
            endY = 900;

            //horizontal lines
            for (int i = 0; i < 9; i++) {
                c.drawLine(startX, startY, endX, endY, black);
                startX += 100;
                endX += 100;
            }

            c.drawCircle(750, 450, 49, white);
            c.drawCircle(850, 550, 49, white);
            c.drawCircle(850, 450, 49, black);
            c.drawCircle(750, 550, 49, black);


            //The tiny circles on the board
            c.drawCircle(600, 300, 8, black);
            c.drawCircle(1000, 300, 8, black);
            c.drawCircle(600, 700, 8, black);
            c.drawCircle(1000, 700, 8, black);


            //Check the board and update it with the current state of the game
            char[][] state = gameState.getBoard();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (state[i][j] == 'b') {
                        c.drawCircle(450 + (100 * j), 150 + (100 * i), 49, black);
                    } else if (state[i][j] == 'w') {
                        c.drawCircle(450 + (100 * j), 150 + (100 * i), 49, white);
                    } else if (state[i][j] == 'e') {
                        c.drawCircle(450 + (100 * j), 150 + (100 * i), 49, green);
                    }
                    //Put piece counter
                    c.drawText("Black Pieces:" + gameState.numBlackPieces , 400, 1000, black);
                    c.drawText("White Pieces:" + gameState.numWhitePieces , 400, 1050, black);
                    //Draw grey circles for possible move
                    for (int l = 0; l < 8; l++) {
                        for (int m = 0; m < 8; m++) {
                            if (gameState.isValidMove(l, m)) {
                                c.drawCircle(450 + (100 * m), 150 + (100 * l), 15, lightGrey);
                            }
                        }
                    }
                }
            }
            if(gameState.goAgain) {
                black.setTextSize(50);
                c.drawText("Out of moves! Touch screen again to make White go!", 25, 50, black);
            }
        }
        else{

            //Game End Screen
            c.drawRect(400, 100, 1200, 900, sage);
            black.setTextSize(125);
            //GameOverMessage
            c.drawText("Game Over!", 480, 240, black);
            black.setTextSize(75);
            if(gameState.isTie){
                c.drawText("Tie. No Winner!", 595, 310, black);
            }
            else{
                if(gameState.blackWinner) {
                    c.drawText("Black Wins!", 595, 310, black);
                }
                else {
                    c.drawText("White Wins!", 595, 310, black);
                }
            }
            //Score
            String blackS = ""+gameState.numBlackPieces;
            String whiteS = ""+gameState.numWhitePieces;
            c.drawCircle(625, 425 , 100, black);
            black.setTextSize(40);
            c.drawText(blackS, 613, 560, black);
            c.drawCircle(975, 425 , 100, white);
            c.drawText(whiteS, 963, 560, black);

            //Buttons
            c.drawRect(525, 600, 725, 675, brownBox);
            c.drawRect(875, 600, 1075, 675, brownBox);
            black.setTextSize(50);
            c.drawText("Exit", 575, 655, black);
            black.setTextSize(50);
            c.drawText("Restart", 895, 655, black);
        }
    }
    protected void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
        }
    }

}
