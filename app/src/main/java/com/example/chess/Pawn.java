package com.example.chess;

public class Pawn extends Figure{

    public Pawn(int x, int y, char color){
        super(x, y, color);
        blackOnBlackResource = R.drawable.black_pawn_on_black;
        blackOnWhiteResource = R.drawable.black_pawn_on_white;
        whiteOnBlackResource = R.drawable.white_pawn_on_black;
        whiteOnWhiteResource = R.drawable.white_pawn_on_white;
    }

    public boolean isPossibleMove(int x, int y) {
        if(!isValidCell(x, y) || this.getX() != x)
            return false;
        if(this.getY() == y  &&  this.getX() == x)
            return false;

        if(getColor() == 'b'){
            if(this.getY() == 0)
                return false;
            if(this.getY() == 6){
                if(y == 5  ||  y == 4)
                    return true;
            }
            else if(this.getY() - y ==1)
                return true;
        }

        else if(getColor() == 'w'){
            if(this.getY() == 7)
                return false;
            if(this.getY() == 1){
                if(y == 2  ||  y == 3)
                    return true;
            }
            else if(y - this.getY() == 1)
                return true;
        }
        return false;
    }

    public boolean isPossibleAttack(int x, int y) {
        if(!isValidCell(x, y))
            return false;
        if(this.getY() == y  &&  this.getX() == x)
            return false;

        if(getColor() == 'b'){
            if(Math.abs(this.getX() - x) == 1){
                if(this.getY() - y == 1)
                    return true;
            }
        }

        else if(getColor() == 'w'){
            if(Math.abs(this.getX() - x) == 1){
                if(y - this.getY() == 1)
                    return true;
            }
        }
        return false;
    }

    public String toString() {
        return "Pawn";
    }
}
