package com.example.chess;

public class Bishop extends Figure{

    public Bishop(int x, int y, char color){
        super(x, y, color);
        blackOnBlackResource = R.drawable.black_bishop_on_black;
        blackOnWhiteResource = R.drawable.black_bishop_on_white;
        whiteOnBlackResource = R.drawable.white_bishop_on_black;
        whiteOnWhiteResource = R.drawable.white_bishop_on_white;
    }

    public boolean isPossibleMove(int x, int y) {
        if(!isValidCell(x, y))
            return false;
        if(this.getY() == y  &&  this.getX() == x)
            return false;
        if(Math.abs(this.getX() - x) == Math.abs(this.getY() - y))
            return true;
        return false;
    }

    public String toString() {
        return "Bishop";
    }
}
