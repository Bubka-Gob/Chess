package com.example.chess;

public class Rook extends Figure{

    public Rook(int x, int y, char color){
        super(x, y, color);
        blackOnBlackResource = R.drawable.black_rook_on_black;
        blackOnWhiteResource = R.drawable.black_rook_on_white;
        whiteOnBlackResource = R.drawable.white_rook_on_black;
        whiteOnWhiteResource = R.drawable.white_rook_on_white;
    }

    public boolean isPossibleMove(int x, int y) {
        if(!isValidCell(x, y))
            return false;
        if(this.getY() == y  &&  this.getX() == x)
            return false;
        if(this.getX() == x  ||  this.getY() == y)
            return true;
        return false;
    }

    public String toString() {
        return "Rook";
    }

}
