package com.example.chess;

import java.util.Objects;

abstract public class Figure {
    protected int blackOnBlackResource;
    protected int blackOnWhiteResource;
    protected int whiteOnBlackResource;
    protected int whiteOnWhiteResource;
    private int x;
    private int y;
    private char color;

    protected Figure(int x, int y, char color){
        if(isValidCell(x, y)){
            setX(x);
            setY(y);
        }
        else{
            setX(0);
            setY(0);
        }

        if(color == 'b'  ||  color == 'w')
            setColor(color);
        else
            setColor('w');
    }

    protected void setX(int x){
        this.x = x;
    }
    protected void setY(int y){
        this.y = y;
    }
    protected void setColor(char color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public char getColor() {
        return color;
    }

    public int getBlackCellResource() {
        if(getColor() == 'b')
            return blackOnBlackResource;
        else
            return whiteOnBlackResource;
    }

    public int getWhiteCellResource() {
        if(getColor() == 'b')
            return blackOnWhiteResource;
        else
            return whiteOnWhiteResource;
    }

    public static boolean isValidCell(int x, int y) {
        if(x>=0 && x<8  &&  y>=0 && y<8)
            return true;
        else
            return false;
    }

    public boolean move(int x, int y) {
        if(isPossibleMove(x, y)){
            setX(x);
            setY(y);
            return true;
        }
        else
            return false;
    }

    public boolean attack(int x, int y) {
        if(isPossibleAttack(x, y)){
            setX(x);
            setY(y);
            return true;
        }
        else
            return false;
    }

    public boolean isPossibleAttack(int x, int y) {
        if(isPossibleMove(x, y))
            return true;
        else
            return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Figure))
            return false;
        Figure figure = (Figure) o;
        return this.getX() == figure.getX() && this.getY() == figure.getY();
    }

    abstract public boolean isPossibleMove(int x, int y);

}
