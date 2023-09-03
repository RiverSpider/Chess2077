package ru.myitschool.jabagames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Queen extends Chessman {
    public Queen(int x, int y, boolean white) {
        super(x, y, white);
        whiteTexture = new Texture("whiteQueen.png");
        blackTexture = new Texture("blackQueen.png");
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
        for (int i = 1; getX() + i < 8 && getY() + i < 8; i++) {
            if (board.isKingChecked(this, Chessman.addMove(getX()+i, getY()+i))){
                continue;
            }
            if(board.getChessman(getX() + i, getY() + i) != null){
                if (board.getChessman(getX()+i, getY()+i).isWhite() != isWhite()){
                    availableMoves.add(Chessman.addMove(getX()+i, getY()+i));
                }
                break;
            }
            availableMoves.add(Chessman.addMove(getX()+i, getY()+i));
        }
        for (int i = 1; getX()-i >= 0 && getY()-i >= 0;i++) {
            if (board.isKingChecked(this, Chessman.addMove(getX()-i, getY()-i))){
                continue;
            }
            if(board.getChessman(getX()-i, getY()-i) != null){
                if (board.getChessman(getX()-i, getY()-i).isWhite() != isWhite()){
                    availableMoves.add(Chessman.addMove(getX()-i, getY()-i));
                }
                break;
            }
            availableMoves.add(Chessman.addMove(getX()-i, getY()-i));
        }
        for (int i = 1; getX()+i < 8 && getY()-i >= 0; i++) {
            if (board.isKingChecked(this, Chessman.addMove(getX()+i, getY()-i))){
                continue;
            }
            if(board.getChessman(getX()+i, getY()-i) != null){
                if (board.getChessman(getX()+i, getY()-i).isWhite() != isWhite()){
                    availableMoves.add(Chessman.addMove(getX()+i, getY()-i));
                }
                break;
            }
            availableMoves.add(Chessman.addMove(getX()+i, getY()-i));
        }
        for (int i = 1; getX()-i>=0 && getY()+i < 8; i++) {
            if (board.isKingChecked(this, Chessman.addMove(getX()-i, getY()+i))){
                continue;
            }
            if(board.getChessman(getX()-i, getY()+i) != null){
                if (board.getChessman(getX()-i, getY()+i).isWhite() != isWhite()){
                    availableMoves.add(Chessman.addMove(getX()-i, getY()+i));
                }
                break;
            }
            availableMoves.add(Chessman.addMove(getX()-i, getY()+i));
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
        for (int i = 1; getX() + i < 8 && getY() + i < 8; i++) {
            if(board.getChessman(getX() + i, getY() + i) != null){
                moves.add(Chessman.addMove(getX()+i, getY()+i));
                break;
            }
            moves.add(Chessman.addMove(getX()+i, getY()+i));
        }
        for (int i = 1; getX()-i >= 0 && getY()-i >= 0;i++) {
            if(board.getChessman(getX()-i, getY()-i) != null){
                moves.add(Chessman.addMove(getX()-i, getY()-i));
                break;
            }
            moves.add(Chessman.addMove(getX()-i, getY()-i));
        }
        for (int i = 1; getX()+i < 8 && getY()-i >= 0; i++) {
            if(board.getChessman(getX()+i, getY()-i) != null){
                moves.add(Chessman.addMove(getX()+i, getY()-i));
                break;
            }
            moves.add(Chessman.addMove(getX()+i, getY()-i));
        }
        for (int i = 1; getX()-i>=0 && getY()+i < 8; i++) {
            if(board.getChessman(getX()-i, getY()+i) != null){
                moves.add(Chessman.addMove(getX()-i, getY()+i));
                break;
            }
            moves.add(Chessman.addMove(getX()-i, getY()+i));
        }
        return moves;
    }

    @Override
    public String toString() {
        return "Queen";
    }
}
