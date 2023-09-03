package ru.myitschool.jabagames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Bishop extends Chessman {
    public Bishop(int x, int y, boolean white) {
        super(x, y, white);
        blackTexture = new Texture("blackBishop.png");
        whiteTexture = new Texture("whiteBishop.png");
    }

    @Override
    public Array<int[]> getAvailableMoves(Board board) {
        Array<int[]> availableMoves = new Array<>();
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
        return "Bishop";
    }
}
