package com.example.chess;

import android.content.Context;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Figure> whiteList;
    private List<Figure> blackList;
    private ImageView[][] boardImages;
    private Figure pickedFigure = null;
    private boolean isWhiteTurn;
    private Saver saver;


    public Board(ImageView[][] boardImages, Context context) {
        this.boardImages = boardImages;
        this.saver = new Saver(context);
        generateStartBoard();
    }

    // main game logic here
    public void imagePressed(int x, int y) {
        if(pickedFigure == null) {
            if (getFigure(x, y) != null) {
                setPickedFigure(getFigure(x, y));
                if(isWhiteTurn)
                    setClickable(blackList);
                else
                    setClickable(whiteList);
            }
        }
        else
            if(getFigure(x,y) == null) {
                if(pickedFigure.isPossibleMove(x, y) && isFreeWay(pickedFigure, x, y)) {
                    clearCell(pickedFigure.getX(), pickedFigure.getY());
                    pickedFigure.move(x, y);
                    visualize(pickedFigure);
                    pickedFigure = null;
                    clearMovingFilters();
                    changeTurn();
                }
            }
            else
                if(getFigure(x, y).getColor() == pickedFigure.getColor())
                    setPickedFigure(getFigure(x, y));
                else if(pickedFigure.isPossibleAttack(x, y) && isFreeWay(pickedFigure, x, y)) {
                    clearCell(pickedFigure.getX(), pickedFigure.getY());
                    killFigure(x, y);
                    pickedFigure.attack(x, y);
                    visualize(pickedFigure);
                    pickedFigure = null;
                    clearMovingFilters();
                    changeTurn();
                }
    }

    public void killFigure (int x, int y) {
        if(getWhiteFigure(x, y) != null) {
            clearCell(x, y);
            whiteList.remove(getWhiteFigure(x, y));
        }
        if(getBlackFigure(x, y) != null) {
            clearCell(x, y);
            blackList.remove(getBlackFigure(x, y));
        }
    }

    public void changeTurn () {
        if(isWhiteTurn) {
            isWhiteTurn = false;
            setClickable(blackList);
            setUnClickable(whiteList);
        }
        else {
            isWhiteTurn = true;
            setClickable(whiteList);
            setUnClickable(blackList);
        }
    }

    public void setUnClickable (List<Figure> list) {
        for(Figure figure: list)
            boardImages[figure.getX()][figure.getY()].setClickable(false);
    }

    public void setClickable (List<Figure> list) {
        for(Figure figure: list)
            boardImages[figure.getX()][figure.getY()].setClickable(true);
    }

    public void setAllClickable() {
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                boardImages[i][j].setClickable(true);
    }

    public void setFilter(int x, int y){
        if(pickedFigure.equals(getFigure(x, y)))
            boardImages[x][y].setColorFilter(0X7F123423);
        else if(getFigure(x, y) != null)
            boardImages[x][y].setColorFilter(0X7FFF1100);
        else
            boardImages[x][y].setColorFilter(0X7FFFED00);
    }

    public void clearCell(int x, int y) {
        if(Figure.isValidCell(x, y)){
            if(isBlackCell(x, y))
                boardImages[x][y].setImageResource(R.drawable.black);
            else
                boardImages[x][y].setImageResource(R.drawable.white);
            boardImages[x][y].clearColorFilter();
        }
    }

    public void clearBoard() {
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                clearCell(i, j);
        whiteList.clear();
        blackList.clear();
        setAllClickable();
        pickedFigure = null;
    }

    public void clearColorFiler(int x, int y){
        boardImages[x][y].clearColorFilter();
    }

    public void clearMovingFilters() {
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++) {
                clearColorFiler(i, j);
            }
        if(pickedFigure != null)
            setFilter(pickedFigure.getX(), pickedFigure.getY());
    }

    public void generateStartBoard() {
        whiteList = new ArrayList<>();
        blackList = new ArrayList<>();
        for(int i=0; i<8; i++){
            whiteList.add(new Pawn(i, 1, 'w'));
            blackList.add(new Pawn(i, 6, 'b'));
        }
        for(int i=0; i<2; i++){
            whiteList.add(new Rook(i*7, 0, 'w'));
            blackList.add(new Rook(i*7, 7, 'b'));

            whiteList.add(new Knight(i*5 + 1, 0, 'w'));
            blackList.add(new Knight(i*5 + 1, 7, 'b'));

            whiteList.add(new Bishop(i*3 + 2, 0, 'w'));
            blackList.add(new Bishop(i*3 + 2, 7, 'b'));
        }

        whiteList.add(new King(3, 0, 'w'));
        blackList.add(new King(3, 7, 'b'));

        whiteList.add(new Queen(4, 0, 'w'));
        blackList.add(new Queen(4, 7, 'b'));

        for(Figure figure: whiteList)
            visualize(figure);
        for (Figure figure: blackList)
            visualize(figure);
        setUnClickable(blackList);
        setClickable(whiteList);
        isWhiteTurn = true;
    }

    public void visualize(Figure figure) {
        if(isBlackCell(figure.getX(), figure.getY()))
            boardImages[figure.getX()] [figure.getY()]
                    .setImageResource(figure.getBlackCellResource());
        else
            boardImages[figure.getX()] [figure.getY()]
                    .setImageResource(figure.getWhiteCellResource());
    }

    public void setPickedFigure(Figure figure) {
        if(pickedFigure != null){
            clearMovingFilters();
            clearColorFiler(pickedFigure.getX(), pickedFigure.getY());
        }
        pickedFigure = figure;
        setFilter(pickedFigure.getX(), pickedFigure.getY());
        showPossibilities();
    }

    public void showPossibilities (){
        if(pickedFigure != null)
            for (int i=0; i<8; i++)
                for(int j=0; j<8; j++){
                    if(getFigure(i, j) == null) {
                        if (pickedFigure.isPossibleMove(i, j) && isFreeWay(pickedFigure, i, j))
                            setFilter(i, j);
                    }
                    else if(getFigure(i, j).getColor() != pickedFigure.getColor()){
                        if(pickedFigure.isPossibleAttack(i, j) && isFreeWay(pickedFigure, i, j))
                            setFilter(i, j);
                    }
                }

    }

    public void save() {
        saver.clearBase();
        saver.saveFigures(blackList);
        saver.saveFigures(whiteList);
        saver.saveTurn(isWhiteTurn);
    }

    public boolean load() {
        if(saver.loadFigures('b') != null) {
            clearBoard();
            isWhiteTurn = saver.LoadTurn();
            blackList = saver.loadFigures('b');
            whiteList = saver.loadFigures('w');
            for(Figure figure: whiteList)
                visualize(figure);
            for(Figure figure: blackList)
                visualize(figure);
            if(isWhiteTurn) {
                setClickable(whiteList);
                setUnClickable(blackList);
            }
            else {
                setClickable(blackList);
                setUnClickable(whiteList);
            }
            return true;
        }
        return false;
    }

    public void delete() {
        saver.clearBase();
    }

    public Figure getFigure(int x, int y) {
        if(getWhiteFigure(x, y) != null)
            return getWhiteFigure(x, y);
        else
            return getBlackFigure(x, y);
    }

    public Figure getWhiteFigure(int x, int y) {
        for(Figure figure: whiteList){
            if(figure.getX() == x  &&  figure.getY() == y)
                return figure;
        }
        return null;
    }

    public Figure getBlackFigure(int x, int y) {
        for(Figure figure: blackList){
            if(figure.getX() == x  &&  figure.getY() == y)
                return figure;
        }
        return null;
    }

    public boolean isBlackCell(int x, int y) {
        if((x+y)%2 == 0)
            return true;
        else
            return false;
    }

    public boolean isFreeWay(Figure figure, int x, int y){
        if(figure instanceof Knight)
            return true;
        if(figure.getX() == x){
            if(figure.getY() < y){
                for(int i = figure.getY()+1; i<y; i++){
                    if(getFigure(x, i) != null)
                        return false;
                }
            }
            else {
                for(int i = y+1; i<figure.getY(); i++){
                    if(getFigure(x, i) != null)
                        return false;
                }
            }
        }

        else if(figure.getY() == y){
            if(figure.getX() < x){
                for(int i = figure.getX()+1; i<x; i++){
                    if(getFigure(i, y) != null)
                        return false;
                }
            }
            else {
                for(int i = x+1; i<figure.getX(); i++){
                    if(getFigure(i, y) != null)
                        return false;
                }
            }
        }

        else if (y < figure.getY()) {
            if(figure.getX() < x){
                for(int i = figure.getX()+1, j = y+1; i<x; i++, j++){
                    if(getFigure(i, j) != null)
                        return false;
                }
            }
            else {
                for(int i = x+1, j = y+1; i<figure.getX(); i++, j++){
                    if(getFigure(i, j) != null)
                        return false;
                }
            }
        }

        else {
            if(figure.getX() < x){
                for(int i = figure.getX()+1, j = figure.getY()+1; i<x; i++, j++){
                    if(getFigure(i, j) != null)
                        return false;
                }
            }
            else {
                for(int i = x+1, j = figure.getY()+1; i<figure.getX(); i++, j++){
                    if(getFigure(i, j) != null)
                        return false;
                }
            }
        }

        return true;
    }
}
