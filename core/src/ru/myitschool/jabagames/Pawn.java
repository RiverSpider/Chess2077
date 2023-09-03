package ru.myitschool.jabagames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Pawn extends Chessman {
    public Pawn(int x, int y, boolean white) {
        super(x, y, white);
        whiteTexture = new Texture("whitePawn.png");
        blackTexture = new Texture("blackPawn.png");
    }

    @Override
    public Array<int[]> getAvailableMoves(Board board) {
        Array<int[]> availableMoves = new Array<>();
        int move = isWhite()?1:-1;
        if (getY()+move >= 0 && getY()+move < 8 && board.getChessman(getX(), getY()+move) == null){
            if (!board.isKingChecked(this, Chessman.addMove(getX(), getY()+move))) {
                availableMoves.add(Chessman.addMove(getX(), getY() + move));
                if (!isMoved() && getY() + move * 2 >= 0 && getY() + move * 2 < 8 && board.getChessman(getX(), getY() + move * 2) == null) {
                    if (!board.isKingChecked(this, Chessman.addMove(getX(), getY() + move * 2))) {
                        availableMoves.add(Chessman.addMove(getX(), getY() + move * 2));
                    }
                }
            }
        }
        if (getX() + 1 < 8 && getY() + move < 8 && getY() + move >= 0 && board.getChessman(getX()+1, getY()+move) != null && board.getChessman(getX()+1, getY()+move).isWhite() != isWhite()){
            if (!board.isKingChecked(this, Chessman.addMove(getX()+1, getY()+move))) {
                availableMoves.add(Chessman.addMove(getX() + 1, getY() + move));
            }
        }
        if (getX() - 1 >= 0 && getY() + move < 8 && getY() + move >= 0 && board.getChessman(getX()-1, getY()+move) != null && board.getChessman(getX()-1, getY()+move).isWhite() != isWhite()){
            if(!board.isKingChecked(this, Chessman.addMove(getX()-1, getX()+move))) {
                availableMoves.add(Chessman.addMove(getX() - 1, getY() + move));
            }
        }
        setAvailableMoves(availableMoves);
        return availableMoves;
    }

    @Override
    public Array<int[]> getMoves(Board board) {
        Array<int[]> moves = new Array<>();
        int move = isWhite()?1:-1;
        if (getX() + 1 < 8 && getY() + move < 8 && getY() + move >= 0){
            moves.add(Chessman.addMove(getX()+1, getY()+move));
        }
        if (getX() - 1 >= 0 && getY() + move < 8 && getY() + move >= 0){
            moves.add(Chessman.addMove(getX()-1, getY()+move));
        }
        return moves;
    }

    @Override
    public String toString() {
        return "Pawn";
    }
}
