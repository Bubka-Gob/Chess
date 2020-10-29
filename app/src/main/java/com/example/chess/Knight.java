package com.example.chess;

public class Knight extends Figure{

    public Knight(int x, int y, char color){
        super(x, y, color);
        blackOnBlackResource = R.drawable.black_knight_on_black;
        blackOnWhiteResource = R.drawable.black_knight_on_white;
        whiteOnBlackResource = R.drawable.white_knight_on_black;
        whiteOnWhiteResource = R.drawable.white_knight_on_white;
    }

    public boolean isPossibleMove(int x, int y) {
        if(!isValidCell(x, y))
            return false;
        if(this.getY() == y  &&  this.getX() == x)
            return false;
        if(Math.abs(this.getX() - x) == 2  &&  Math.abs(this.getY() - y) == 1)
            return true;
        if(Math.abs(this.getX() - x) == 1  &&  Math.abs(this.getY() - y) == 2)
            return true;
        return false;
    }

    public String toString() {
        return "Knight";
    }
}
