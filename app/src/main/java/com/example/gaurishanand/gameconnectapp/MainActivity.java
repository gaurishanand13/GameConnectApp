package com.example.gaurishanand.gameconnectapp;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer=1;
    int winningPositions[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int arr[]={0,0,0,0,0,0,0,0,0};
    boolean activeGame=true;
    TextView textView;
    Button button;

    public void clickFunction(View view)
    {
        ImageView imageView=(ImageView) view;
        int TappedCounter=Integer.parseInt(imageView.getTag().toString());
        if(arr[TappedCounter]==0 && activeGame)
        {
            arr[TappedCounter]=activePlayer;
            if(activePlayer==1)
            {
                activePlayer=2;
                imageView.setImageResource(R.drawable.cross);
            }
            else
            {
                activePlayer=1;
                imageView.setImageResource(R.drawable.circle);
            }
            int c=1;
            for(int i=0;i<9;i++)
            {
                if(arr[i]==0)
                {
                    c=2;
                    break;
                }
            }
            if(c==1)
            {
                activeGame=false;
                textView.setText("MATCH IS DRAWN");
                button.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
            }
            for(int i=0;i<8;i++)
            {
                if(arr[winningPositions[i][0]]==arr[winningPositions[i][1]] && arr[winningPositions[i][1]]==arr[winningPositions[i][2]] && arr[winningPositions[i][0]]!=0)
                {
                    String ans;
                    if(activePlayer==1)
                    {
                        ans="PLAYER 2";
                    }
                    else
                    {
                        ans="PLAYER 1";
                    }
                    activeGame=false;
                    textView.setText(ans+" HAS WON");
                    button.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playagain(View view)
    {
        button.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        for(int i=0;i<9;i++)
        {
            arr[i]=0;
        }
        activePlayer=1;
        activeGame=true;
        /*GridLayout gridLayout = (GridLayout) findViewById(R.id.myGridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++)
        {
            ImageView counter =(ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }*/
        ImageView imageView1=(ImageView)findViewById(R.id.imageView1);
        imageView1.setImageDrawable(null);
        ImageView imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView2.setImageDrawable(null);
        ImageView imageView3=(ImageView)findViewById(R.id.imageView3);
        imageView3.setImageDrawable(null);
        ImageView imageView4=(ImageView)findViewById(R.id.imageView4);
        imageView4.setImageDrawable(null);
        ImageView imageView5=(ImageView)findViewById(R.id.imageView5);
        imageView5.setImageDrawable(null);
        ImageView imageView6=(ImageView)findViewById(R.id.imageView6);
        imageView6.setImageDrawable(null);
        ImageView imageView7=(ImageView)findViewById(R.id.imageView7);
        imageView7.setImageDrawable(null);
        ImageView imageView8=(ImageView)findViewById(R.id.imageView8);
        imageView8.setImageDrawable(null);
        ImageView imageView9=(ImageView)findViewById(R.id.imageView9);
        imageView9.setImageDrawable(null);
    }
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView2);
        button=(Button)findViewById(R.id.button);
    }
}
