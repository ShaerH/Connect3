package com.example.shaer.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0=x, 1=o;
    int activePlayer = 0;
    //2 means unplayed
    int gameState[] = {2,2,2,2,2,2,2,2,2};
    int winningPostions[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameActive = true;
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameActive) {
            gameState[tappedCounter]=activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.xpattern);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.opattern);
                activePlayer = 0;

            }
            counter.animate().translationYBy(1000f).setDuration(300);

            for (int [] winningPostions : winningPostions){
                if (gameState[winningPostions[0]]==gameState[winningPostions[1]] &&
                        gameState[winningPostions[1]]==gameState[winningPostions[2]] &&
                        gameState[winningPostions[0]] !=2){

                    gameActive = false;
                    String winner = "O";
                    if (gameState[winningPostions[0]] == 0){
                        winner = "X";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner +" has Won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameisOver = true;

                    for (int counterState: gameState){
                        if(counterState == 2)gameisOver=false;
                    }
                    if (gameisOver){
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("Its a Draw");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

    }

    public void playAgain(View view){
        gameActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        gameActive = true;

        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i<gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
