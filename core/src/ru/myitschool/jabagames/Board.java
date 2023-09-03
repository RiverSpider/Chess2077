package ru.myitschool.jabagames;


import com.badlogic.gdx.utils.Array;

import java.util.Arrays;

public class Board {
    Chessman[][] chessmen;
    boolean isWhiteMove;
    King blackKing, whiteKing;

    public Board() {
        chessmen = new Chessman[8][8];
    }

    public void addChessman(Chessman chessman) {
        if (chessmen[chessman.getX()][chessman.getY()] == null) {
            chessmen[chessman.getX()][chessman.getY()] = chessman;
        }
    }

    public void moveChessman(Chessman chessman, int[] coords) {
        boolean contains = false;
        for (int[] move :
                chessman.getAvailableMoves(this)) {
            if (Arrays.equals(move, coords)) {
                contains = true;
                break;
            }
        }
        if (contains) {
            King enemyKing = isWhiteMove ? blackKing : whiteKing;
            King allyKing = isWhiteMove ? whiteKing : blackKing;
            if (chessman.toString().equals("King") && (chessman.getX() - coords[0] == 2)) {
                chessmen[3][chessman.getY()] = chessmen[0][chessman.getY()];
                chessmen[0][chessman.getY()].setCoord(Chessman.addMove(3, chessman.getY()));
                chessmen[0][chessman.getY()] = null;
                chessmen[3][chessman.getY()].moved();
            } else if (chessman.toString().equals("King") && (chessman.getX() - coords[0] == -2)) {
                chessmen[5][chessman.getY()] = chessmen[7][chessman.getY()];
                chessmen[7][chessman.getY()].setCoord(Chessman.addMove(5, chessman.getY()));
                chessmen[7][chessman.getY()] = null;
                chessmen[5][chessman.getY()].moved();
            }
            chessmen[chessman.getX()][chessman.getY()] = null;
            chessman.setCoord(coords);
            chessmen[coords[0]][coords[1]] = chessman;
            chessman.moved();
            boolean hasAnyMove = false;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (getChessman(i, j) != null && getChessman(i, j).isWhite() != chessman.isWhite() && getChessman(i, j).getAvailableMoves(this).size != 0) {
                        hasAnyMove = true;
                    }
                }
            }
            if (!hasAnyMove && enemyKing.contains(chessman.getAvailableMoves(this), Chessman.addMove(enemyKing.getX(), enemyKing.getY()))) {
                if (chessman.isWhite()) {
                    //Максим пишет победу белых
                } else {
                    //Максим пишет победу черных
                }
            } else if (!hasAnyMove) {
                //Максим пишет ничью
            }
        }
        isWhiteMove = !isWhiteMove;
    }


    public Chessman getChessman(int x, int y) {
        return chessmen[x][y];
    }

    public Chessman getChessman(int[] coords) {
        return chessmen[coords[0]][coords[1]];
    }

    public boolean isKingChecked(Chessman chessman, int[] move) {
        King allyKing = isWhiteMove ? whiteKing : blackKing;
        int[] chessmanStartCoords = {chessman.getX(), chessman.getY()};
        Chessman enemyChessman = null;
        if (chessmen[move[0]][move[1]] != null) {
            enemyChessman = chessmen[move[0]][move[1]];
        }
        chessmen[chessman.getX()][chessman.getY()] = null;
        chessman.setCoord(move);
        chessmen[move[0]][move[1]] = chessman;
        boolean isAllyKingChecked;
        isAllyKingChecked = chessman.contains(allyKing.checkedCells(this), Chessman.addMove(allyKing.getX(), allyKing.getY()));
        chessmen[chessman.getX()][chessman.getY()] = enemyChessman;
        chessman.setCoord(Chessman.addMove(chessmanStartCoords[0], chessmanStartCoords[1]));
        chessmen[chessmanStartCoords[0]][chessmanStartCoords[1]] = chessman;
        return isAllyKingChecked;
    }
}

