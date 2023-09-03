package ru.myitschool.jabagames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Rook extends Chessman {

    public Rook(int x, int y, boolean white) {
        super(x, y, white);
        blackTexture = new Texture("blackRook.png");
        whiteTexture = new Texture("whiteRook.png");
    }

    @Override
    public Array<int[]> getAvailableMoves(Board board) {
        Array<int[]> availableMoves = new Array<>();
        for (int x = getX(); x < 7; x++) {
            if (!board.isKingChecked(this, Chessman.addMove(x+1, getY()))){
                continue;
            }
            if (board.getChessman(x+1, getY()) != null){
                if (board.getChessman(x+1, getY()).isWhite() != isWhite()){
                    availableMoves.add(Chessman.addMove(x+1, getY()));
                }
                break;
            }
            availableMoves.add(Chessman.addMove(x+1, getY()));
        }
        for (int x = getX(); x > 0; x--) {
            if (!board.isKingChecked(this, Chessman.addMove(x-1, getY()))){
                continue;
            }
            if (board.getChessman(x-1, getY()) != null){
                if (board.getChessman(x-1, getY()).isWhite() != isWhite()){
                    availableMoves.add(Chessman.addMove(x-1, getY()));
                }
                break;
            }
            availableMoves.add(Chessman.addMove(x-1, getY()));
        }
        for (int y = getY(); y < 7; y++) {
            if (!board.isKingChecked(this, Chessman.addMove(getX(), y+1))){
                continue;
            }
            if (board.getChessman(getX(), y+1) != null){
                if (board.getChessman(getX(), y+1).isWhite() != isWhite()){
                    availableMoves.add(Chessman.addMove(getX(), y+1));
                }
                break;
            }
            availableMoves.add(Chessman.addMove(getX(), y+1));
        }
        for (int y = getY(); y > 0; y--) {
            if (!board.isKingChecked(this, Chessman.addMove(getX(), y-1))){
                continue;
            }
            if (board.getChessman(getX(), y-1) != null){
                if (board.getChessman(getX(), y-1).isWhite() != isWhite()){
                    availableMoves.add(Chessman.addMove(getX(), y-1));
                }
                break;
            }
            availableMoves.add(Chessman.addMove(getX(), y-1));
        }
        setAvailableMoves(availableMoves);
        return availableMoves;
    }

    @Override
    public Array<int[]> getMoves(Board board) {
        Array<int[]> moves = new Array<>();
        for (int x = getX(); x < 7; x++) {
            if (board.getChessman(x+1, getY()) != null){
                moves.add(Chessman.addMove(x+1, getY()));
                break;
            }
            moves.add(Chessman.addMove(x+1, getY()));
        }
        for (int x = getX(); x > 0; x--) {
            if (board.getChessman(x-1, getY()) != null){
                moves.add(Chessman.addMove(x-1, getY()));
                break;
            }
            moves.add(Chessman.addMove(x-1, getY()));
        }
        for (int y = getY(); y < 7; y++) {
            if (board.getChessman(getX(), y+1) != null){
                moves.add(Chessman.addMove(getX(), y+1));
                break;
            }
            moves.add(Chessman.addMove(getX(), y+1));
        }
        for (int y = getY(); y > 0; y--) {
            if (board.getChessman(getX(), y-1) != null){
                moves.add(Chessman.addMove(getX(), y-1));
                break;
            }
            moves.add(Chessman.addMove(getX(), y-1));
        }
        return moves;
    }

    @Override
    public String toString() {
        return "Rook";
    }
}
