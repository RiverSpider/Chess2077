package ru.myitschool.jabagames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Knight extends Chessman {
    public Knight(int x, int y, boolean white) {
        super(x, y, white);
        whiteTexture = new Texture("whiteKnight.png");
        blackTexture = new Texture("blackKnight.png");
    }

    @Override
    public Array<int[]> getAvailableMoves(Board board) {
        Array<int[]> availableMoves = new Array<>();
        for (int i = -2; i < 3; i++) {
            for (int j = -2; j < 3; j++) {
                if (i != j && i != 0 && j != 0 && i != -j){
                    if (getX()+i < 8 && getY()+j < 8 && getX()+i>=0 && getY() + j >= 0 && (board.getChessman(getX() + i, getY() + j) == null || board.getChessman(getX() + i, getY() + j).isWhite() != isWhite())){
                        if (!board.isKingChecked(this, Chessman.addMove(getX()+i, getY()+j))) {
                            availableMoves.add(Chessman.addMove(getX() + i, getY() + j));
                        }
                    }
                }
            }
        }
        setAvailableMoves(availableMoves);
        return availableMoves;
    }

    @Override
    public Array<int[]> getMoves(Board board) {
        Array<int[]> moves = new Array<>();
        for (int i = -2; i < 3; i++) {
            for (int j = -2; j < 3; j++) {
                if (i != j && i != 0 && j != 0 && i !=-j){
                    moves.add(Chessman.addMove(getX()+i, getY()+j));
                }
            }
        }
        return moves;
    }

    @Override
    public String toString() {
        return "Knight";
    }
}
