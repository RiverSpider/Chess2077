package ru.myitschool.jabagames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

import java.util.Arrays;

public abstract class Chessman {
    private int x, y;
    private static int size;
    private boolean white;

    private Array<int[]> availableMoves;
    Texture whiteTexture;

    private boolean moved;

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    @Override
    public String toString() {
        return "Chessman{" +
                "x=" + x +
                ", y=" + y +
                ", white=" + white +
                ", whiteTexture=" + whiteTexture +
                ", blackTexture=" + blackTexture +
                '}';
    }

    Texture blackTexture;

    public Chessman(int x, int y, boolean white) {
        this.x = x;
        this.y = y;
        this.size = Chess.SCR_HEIGHT/8;
        this.white = white;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWhite() {
        return white;
    }

    public void setCoord(int[] coords) {
        this.x = coords[0];
        this.y = coords[1];
    }

    public static int getSize() {
        return size;
    }

    public abstract Array<int[]> getAvailableMoves(Board board);

    public static int[] addMove(int x, int y){
        int[] move = new int[2];
        move[0] = x;
        move[1] = y;
        return move;
    }

    public boolean contains(Array<int[]> moves, int[] coords){
        for (int[] move :
                moves) {
            if (move[0] == coords[0] && move[1] == coords[1]) {
                return true;
            }
        }
        return false;
    }

    public boolean isMoved() {
        return moved;
    }

    public abstract Array<int[]> getMoves(Board board);

    public void moved(){
        moved = true;
    }


    public Array<int[]> getAvailableMoves() {
        return availableMoves;
    }

    void setAvailableMoves(Array<int[]> availableMoves) {
        this.availableMoves = availableMoves;
    }
}
