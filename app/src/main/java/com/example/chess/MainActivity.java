package com.example.chess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView[][] images = new ImageView[8][8];
    Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boardInit();
        board = new Board(images, getApplicationContext());
    }

    @Override
    protected void onStop() {
        super.onStop();
        board.save();
    }

    @Override
    protected void onStart() {
        super.onStart();
        board.load();
    }

    public void boardInit(){
        images[0][0] = findViewById(R.id.imageViewA1);
        images[1][0] = findViewById(R.id.imageViewB1);
        images[2][0] = findViewById(R.id.imageViewC1);
        images[3][0] = findViewById(R.id.imageViewD1);
        images[4][0] = findViewById(R.id.imageViewE1);
        images[5][0] = findViewById(R.id.imageViewF1);
        images[6][0] = findViewById(R.id.imageViewG1);
        images[7][0] = findViewById(R.id.imageViewH1);

        images[0][1] = findViewById(R.id.imageViewA2);
        images[1][1] = findViewById(R.id.imageViewB2);
        images[2][1] = findViewById(R.id.imageViewC2);
        images[3][1] = findViewById(R.id.imageViewD2);
        images[4][1] = findViewById(R.id.imageViewE2);
        images[5][1] = findViewById(R.id.imageViewF2);
        images[6][1] = findViewById(R.id.imageViewG2);
        images[7][1] = findViewById(R.id.imageViewH2);

        images[0][2] = findViewById(R.id.imageViewA3);
        images[1][2] = findViewById(R.id.imageViewB3);
        images[2][2] = findViewById(R.id.imageViewC3);
        images[3][2] = findViewById(R.id.imageViewD3);
        images[4][2] = findViewById(R.id.imageViewE3);
        images[5][2] = findViewById(R.id.imageViewF3);
        images[6][2] = findViewById(R.id.imageViewG3);
        images[7][2] = findViewById(R.id.imageViewH3);

        images[0][3] = findViewById(R.id.imageViewA4);
        images[1][3] = findViewById(R.id.imageViewB4);
        images[2][3] = findViewById(R.id.imageViewC4);
        images[3][3] = findViewById(R.id.imageViewD4);
        images[4][3] = findViewById(R.id.imageViewE4);
        images[5][3] = findViewById(R.id.imageViewF4);
        images[6][3] = findViewById(R.id.imageViewG4);
        images[7][3] = findViewById(R.id.imageViewH4);

        images[0][4] = findViewById(R.id.imageViewA5);
        images[1][4] = findViewById(R.id.imageViewB5);
        images[2][4] = findViewById(R.id.imageViewC5);
        images[3][4] = findViewById(R.id.imageViewD5);
        images[4][4] = findViewById(R.id.imageViewE5);
        images[5][4] = findViewById(R.id.imageViewF5);
        images[6][4] = findViewById(R.id.imageViewG5);
        images[7][4] = findViewById(R.id.imageViewH5);

        images[0][5] = findViewById(R.id.imageViewA6);
        images[1][5] = findViewById(R.id.imageViewB6);
        images[2][5] = findViewById(R.id.imageViewC6);
        images[3][5] = findViewById(R.id.imageViewD6);
        images[4][5] = findViewById(R.id.imageViewE6);
        images[5][5] = findViewById(R.id.imageViewF6);
        images[6][5] = findViewById(R.id.imageViewG6);
        images[7][5] = findViewById(R.id.imageViewH6);

        images[0][6] = findViewById(R.id.imageViewA7);
        images[1][6] = findViewById(R.id.imageViewB7);
        images[2][6] = findViewById(R.id.imageViewC7);
        images[3][6] = findViewById(R.id.imageViewD7);
        images[4][6] = findViewById(R.id.imageViewE7);
        images[5][6] = findViewById(R.id.imageViewF7);
        images[6][6] = findViewById(R.id.imageViewG7);
        images[7][6] = findViewById(R.id.imageViewH7);

        images[0][7] = findViewById(R.id.imageViewA8);
        images[1][7] = findViewById(R.id.imageViewB8);
        images[2][7] = findViewById(R.id.imageViewC8);
        images[3][7] = findViewById(R.id.imageViewD8);
        images[4][7] = findViewById(R.id.imageViewE8);
        images[5][7] = findViewById(R.id.imageViewF8);
        images[6][7] = findViewById(R.id.imageViewG8);
        images[7][7] = findViewById(R.id.imageViewH8);


        int width = images[0][0].getWidth();
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                //images[i][j].setLayoutParams(new FrameLayout.LayoutParams(10,10)); ХУИТА
            }
        }

    }

    public void onClickBoard(View view){
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++){
                if(view.getId() == images[i][j].getId())
                    board.imagePressed(i, j);
            }
        }
    }

    public void reset(View view) {
        board.clearBoard();
        board.delete();
        board.generateStartBoard();
    }
}