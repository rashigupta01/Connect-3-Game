package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView[] image = new ImageView[9];
    Button tryAgainBtn;
    TextView status;
    boolean gameActive = true;

    public int turn = 1;
    public void clickImage(View view) {
        ImageView image = (ImageView) view;
        if (image.getTag().toString().compareTo("Empty") == 0 && gameActive) {
            if (turn == 1) {
                image.setTranslationY(-1500);
                image.setImageResource(R.drawable.red);
                image.setTag("Red");
                image.animate().translationYBy(1500).setDuration(500);
                turn = 2;
            } else {
                image.setTranslationY(-1500);
                image.setImageResource(R.drawable.yellow);
                image.setTag("Yellow");
                image.animate().translationYBy(1500).setDuration(500);
                turn = 1;
            }
        }
        if(whoWin()!=0) {
            gameActive = false;
            if(whoWin()==-1) {
                status.setText("DRAW!!");
            } else if(whoWin()==1) {
                status.setText("Player 1 wins :)");
            } else if(whoWin()==2) {
                status.setText("Player 2 wins :)");
            }
            tryAgainBtn.setVisibility(View.VISIBLE);
            status.setVisibility(View.VISIBLE);
        }
    }

    public void tryAgain(View view) {
        clearBoard();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image[0] = findViewById(R.id.imageView1);
        image[1] = findViewById(R.id.imageView2);
        image[2] = findViewById(R.id.imageView3);
        image[3] = findViewById(R.id.imageView4);
        image[4] = findViewById(R.id.imageView5);
        image[5] = findViewById(R.id.imageView6);
        image[6] = findViewById(R.id.imageView7);
        image[7] = findViewById(R.id.imageView8);
        image[8] = findViewById(R.id.imageView9);

        tryAgainBtn = findViewById(R.id.tryAgainBtn);
        status = findViewById(R.id.status);

        clearBoard();
    }
    public void clearBoard () {
        for (int i=0; i<9; i++) {
            image[i].setTag("Empty");
            image[i].setImageDrawable(null);
        }
        tryAgainBtn.setVisibility(View.GONE);
        status.setVisibility(View.GONE);
        turn = 1;
        gameActive=true;
    }

    public int whoWin() {
        int redh, redv, yellowh, yellowv;
        for(int i=0; i<3; i++) {
            redh=0; yellowh=0; redv=0; yellowv=0;
            for(int j=0; j<3; j++) {
                if(image[3*i+j].getTag().toString().compareTo("Red")==0) {
                    redh++;
                } else if(image[3*i+j].getTag().toString().compareTo("Yellow")==0) {
                    yellowh++;
                }

                if(image[3*j+i].getTag().toString().compareTo("Red")==0) {
                    redv++;
                } else if(image[3*j+i].getTag().toString().compareTo("Yellow")==0) {
                    yellowv++;
                }
            }
            if(redh==3 || redv==3) return 1;
            if(yellowh==3|| yellowv==3) return 2;
        }
        redh=0; yellowh=0; redv=0; yellowv=0;
        for (int i=0; i<3; i++) {
            if(image[4*i].getTag().toString().compareTo("Red")==0) {
                redh++;
            } else if(image[4*i].getTag().toString().compareTo("Yellow")==0) {
                yellowh++;
            }

            if(image[2*i+2].getTag().toString().compareTo("Red")==0) {
                redv++;
            } else if(image[2*i+2].getTag().toString().compareTo("Yellow")==0) {
                yellowv++;
            }
        }
        if(redh==3 || redv==3) return 1;
        if(yellowh==3|| yellowv==3) return 2;

        if(full()) return -1;
        return 0;
    }
    boolean full () {
        int count=0;
        for (int i=0; i<9; i++) {
            if(image[i].getTag().toString().compareTo("Empty")==0) {
                count++;
            }
        }
        if(count==0) {
            return true;
        } else {
            return false;
        }
    }
}