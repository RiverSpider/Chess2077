package ru.myitschool.jabagames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

import java.util.Arrays;


public class King extends Chessman {
    boolean isChecked;

    public King(int x, int y, boolean white) {
        super(x, y, white);
        whiteTexture = new Texture("whiteKing.png");
        blackTexture = new Texture("blackKing.png");
        isChecked = false;
    }

    @Override
    public Array<int[]> getAvailableMoves(Board board) {
        Array<int[]> availableMoves = new Array<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((i != 0 || j != 0) && getX() + i < 8 && getY() + j < 8 && getX() + i >= 0 && getY() + j >= 0 && (board.getChessman(getX() + i, getY() + j) == null || board.getChessman(getX() + i, getY() + j).isWhite() != isWhite())) {
                    availableMoves.add(Chessman.addMove(getX() + i, getY() + j));
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getChessman(i, j) != null && board.getChessman(i, j).isWhite() != isWhite() && board.getChessman(i, j).toString().equals("Rook") && !board.getChessman(i, j).isMoved() && !isMoved()) {
                    if (contains(checkedCells(board), Chessman.addMove(getX()>i?getX()-1:getX()+1, getY()))){
                        continue;
                    }
                    if (getX()> i && board.getChessman(i+1, getY()) == null && board.getChessman(i+2, getY()) == null && board.getChessman(i+3, getY()) == null) {
                        availableMoves.add(Chessman.addMove(2, getY()));
                    }else if (getX() < i && board.getChessman(getX()+1, getY()) == null && board.getChessman(getX()+2, getY()) == null){
                        availableMoves.add(Chessman.addMove(6, getY()));
                    }
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getChessman(i, j) != null && board.getChessman(i, j).isWhite() != isWhite()) {
                    int k = 0;
                    while (k < availableMoves.size) {
                        if (contains(board.getChessman(i, j).getMoves(board), availableMoves.get(k))) {
                            availableMoves.removeIndex(k);
                            k--;
                        }
                        k++;
                    }
                }
            }
        }
        setAvailableMoves(availableMoves);
        return availableMoves;
    }

    public Array<int[]> getMoves(Board board) {
        Array<int[]> moves = new Array<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 || j != 0) {
                    moves.add(Chessman.addMove(getX() + i, getY() + j));
                }
            }
        }
        return moves;
    }

    public Array<int[]> checkedCells(Board board) {
        Array<int[]> checkedCells = new Array<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getChessman(i, j) != null && board.getChessman(i, j).isWhite() != isWhite()) {
                    for (int[] move :
                            board.getChessman(i, j).getMoves(board)) {
                        if (move[0] == getX() && move[1] == getY())
                            System.out.println(board.getChessman(i, j));
                        checkedCells.add(move);
                    }
                }
            }
        }
        return checkedCells;
    }

    @Override
    public String toString() {
        return "King";
    }
}
