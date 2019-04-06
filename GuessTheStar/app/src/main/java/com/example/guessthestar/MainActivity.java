package com.example.guessthestar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> celebrityURL=new ArrayList<String>();
    ArrayList<String> celebrityNAME=new ArrayList<String>();
    ImageView imageView;
    Button button;
    Button button1;
    Button button2;
    Button button3;
    public class downloadimage extends AsyncTask<String,Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream in=urlConnection.getInputStream();
                Bitmap bitmap= BitmapFactory.decodeStream(in);
                return bitmap;
            }
            catch (Exception e)
            {
                System.out.println("HELLO");
                return null;
            }
        }
    }
    public void show()
    {
        Random rand=new Random();
        int correctCeleb=rand.nextInt(celebrityURL.size());
        int locationofcorrectanswer=rand.nextInt(4);
        downloadimage task=new downloadimage();
        try {
            Bitmap myimage=task.execute(celebrityURL.get(correctCeleb)).get();
            imageView.setImageBitmap(myimage);
            //System.out.println("HELLO");
        }
        catch (Exception e)
        {
            Log.i("result","failed");
        }

    }

    public class downloadTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String result=null;
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
                urlConnection.setRequestProperty("User-Agent", "Mozilla");
                InputStream in=urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);
                int data=reader.read();
                while (data!=-1)
                {
                    char ch=(char)data;
                    result+=ch;
                    data=reader.read();
                }
                return result;
            }
            catch (Exception e)
            {
                return "FAILED";
            }
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String result=null;
        downloadTask task=new downloadTask();
        try
        {
            result=task.execute("http://www.posh24.se/kandisar").get();
        }
        catch (Exception e)
        {
            result="failed";
        }
        Log.i("RESULT",result);
        /*Pattern p=Pattern.compile(" src=\"(.*?)\"");
        Matcher m=p.matcher(result);
        while (m.find())
        {
            celebrityURL.add(m.group(1));
        }
        p=Pattern.compile(" alt=\"(.*?)\">");
        m=p.matcher(result);
        while (m.find())
        {
            celebrityNAME.add(m.group(1));
        }*/
         /*imageView=(ImageView)findViewById(R.id.imageView);
        button=(Button)findViewById(R.id.button);
        button1=(Button)findViewById(R.id.button2);
        button2=(Button)findViewById(R.id.button3);
        button3=(Button)findViewById(R.id.button4);*/
        //show();
    }
}