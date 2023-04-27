package com.example.othello_superhuman.actionMessage;


import edu.up.cs301.game.GameFramework.actionMessage.GameAction;
import edu.up.cs301.game.GameFramework.players.GamePlayer;

public class OthelloMoveAction extends GameAction {
    //Tag for logging
    private static final String TAG = "TTTMoveAction";
    private static final long serialVersionUID = -2242980258970485343L;

    private int row;
    private int col;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public OthelloMoveAction(GamePlayer player, int row, int col) {
        super(player);

        this.row = row;
        this.col = col;
    }
    /**
     * get the object's row
     *
     * @return the row selected
     */
    public int getRow() { return row; }

    /**
     * get the object's column
     *
     * @return the column selected
     */
    public int getCol() { return col; }
}
