package com.example.alexandreblanloeil.ti;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.alpha;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Bitmap bmp, bmpSave;
    ImageView imView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*TextView tv = (TextView) findViewById(R.id.txtHello);  //textview fait bugger les boutons (déja buggés), implémentation ultérieure
        tv.setText("Hello from the code !");*/
        imView = findViewById(R.id.bmp);
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inScaled = false;
        o.inMutable = true;
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.image, o);
        bmpSave = bmp.copy(bmp.getConfig(), false);
        imView.setImageBitmap(bmp);
        System.out.println("test");
        Log.i("TAG", "appli crée");
        final Button buttonToGray = findViewById(R.id.togray);
        final Button buttonColorize = findViewById(R.id.red);
        final Button buttonKeepRed = findViewById(R.id.green);
        final Button buttonBlue = findViewById(R.id.blue);
        final Button buttonContrast = findViewById(R.id.contrast);
        final Button buttonConvolve = findViewById(R.id.convolve);
        final Button buttonReset = findViewById(R.id.reset);
        buttonToGray.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toGray2(bmp);
            }
        });
        buttonColorize.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                colorize(bmp);
            }
        });
        buttonKeepRed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                keepRed(bmp);
            }
        });
        buttonBlue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                keepGreen(bmp);
            }
        });
        buttonConvolve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                convolve(bmp);
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                reset();
            }
        });
        buttonContrast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                contrast(bmp);
            }
        });

    }

    protected void toGray(Bitmap bmp) {
        System.out.println("Image greyed successfully");
        for (int y = 0; y < bmp.getHeight(); y++) {
            for (int x = 0; x < bmp.getWidth(); x++) {
                int p = bmp.getPixel(x,y);
                float r = Color.red(p);
                float g = Color.green(p);
                float b = Color.blue(p);
                float a = Color.alpha(p);
                float m = ( r+g+b ) / 765;
                bmp.setPixel(x,y, Color.argb(a,m,m,m));
            }
        }
    }

    protected void toGray2(Bitmap bmp) {
        System.out.println("Image greyed successfully");
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < width * height; i++) {
            int r = Color.red(pixels[i]);
            int g = Color.green(pixels[i]);
            int b = Color.blue(pixels[i]);
            int m = (r + g + b) / 3;
            pixels[i] = Color.rgb(m,m,m);
        }
        bmp.setPixels(pixels, 0, width, 0, 0, width, height);
    }
    protected void colorize(Bitmap bmp){
        System.out.println("Image colorized successfully");
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] pixels = new int[width * height];
        float[] hsv = new float[3];

        Random randint = new Random();
        int hue = randint.nextInt(361); //361 car exclu (0,360)

        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < width * height; i++) {
            Color.colorToHSV(pixels[i], hsv );
            //int a = Color.alpha(pixels[i]);
            hsv[0] = hue;
            pixels[i] = Color.HSVToColor(hsv);
        }
        bmp.setPixels(pixels, 0, width, 0, 0, width, height);
    }

    protected void keepRed(Bitmap bmp){
        System.out.println("Image has only red");
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] pixels = new int[width * height];
        float[] hsv = new float[3];

        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < width * height; i++) {
            int r = Color.red(pixels[i]);
            int g = Color.green(pixels[i]);
            int b = Color.blue(pixels[i]);
            Color.colorToHSV(pixels[i], hsv );
            //int a = Color.alpha(pixels[i]);
            if(hsv[0] > 340 || hsv[0] < 15) {
                pixels[i] = Color.rgb(r, g, b);
            }
                else{
                int m = (r + g + b) / 3;
                pixels[i] = Color.rgb(m, m, m);
            }
        }
        bmp.setPixels(pixels, 0, width, 0, 0, width, height);
    }
    protected void keepGreen(Bitmap bmp){
        System.out.println("Image has only red");
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] pixels = new int[width * height];
        float[] hsv = new float[3];

        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < width * height; i++) {
            int r = Color.red(pixels[i]);
            int g = Color.green(pixels[i]);
            int b = Color.blue(pixels[i]);
            Color.colorToHSV(pixels[i], hsv );
            //int a = Color.alpha(pixels[i]);
            if(hsv[0] > 70 && hsv[0] < 170) {
                pixels[i] = Color.rgb(r, g, b);
            }
            else{
                int m = (r + g + b) / 3;
                pixels[i] = Color.rgb(m, m, m);
            }
        }
        bmp.setPixels(pixels, 0, width, 0, 0, width, height);
    }
    protected void contrast(Bitmap bmp){
        System.out.println("contraste egalisation histogramme");
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] pixels = new int[width * height];
        float[] hsv = new float[3];
        int minred = 255;  //255 = pire valeur possible
        int maxred = 0; //0 = pire valeur possible
        int mingreen = 255;  //255 = pire valeur possible
        int maxgreen = 0; //0 = pire valeur possible
        int minblue = 255;  //255 = pire valeur possible
        int maxblue = 0; //0 = pire valeur possible

        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < width * height; i++) {
            int r = Color.red(pixels[i]);   //capture la valeur de la couleur rouge
            int g = Color.green(pixels[i]);   //capture la valeur de la couleur vert
            int b = Color.blue(pixels[i]);   //capture la valeur de la couleur bleue
            if (r < minred) {     //plus petit que la valeur minimale = remplacée
                minred = r;
            }
            if (r > maxred) {     //plus grand que la valeur maximale = remplacée
                maxred = r;
            }

        }
        for(int i = 0; i < width* height; i++) {
            int r = Color.red(pixels[i]); //capture la valeur courante du rouge
            int contr = (r - minred) * 255 / (maxred - minred);
            pixels[i] = Color.rgb(contr, contr, contr);
        }
        bmp.setPixels(pixels, 0, width, 0, 0, width, height);


    }
    protected void contrastExtDynColor(Bitmap bmp){ //ne fonctionne pas, l'image change pas
        System.out.println("contraste egalisation histogramme");
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] pixels = new int[width * height];
        float[] hsv = new float[3];
        int minred = 255;  //255 = pire valeur possible
        int maxred = 0; //0 = pire valeur possible
        int mingreen = 255;  //255 = pire valeur possible
        int maxgreen = 0; //0 = pire valeur possible
        int minblue = 255;  //255 = pire valeur possible
        int maxblue = 0; //0 = pire valeur possible

        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < width * height; i++) {
            int r = Color.red(pixels[i]);   //capture la valeur de la couleur rouge
            int g = Color.green(pixels[i]);   //capture la valeur de la couleur vert
            int b = Color.blue(pixels[i]);   //capture la valeur de la couleur bleue
            if (r < minred) {     //plus petit que la valeur minimale = remplacée
                minred = r;
            }
            if (r > maxred) {     //plus grand que la valeur maximale = remplacée
                maxred = r;
            }
            if (r < mingreen) {     //plus petit que la valeur minimale = remplacée
                mingreen = g;
            }
            if (r > maxgreen) {     //plus grand que la valeur maximale = remplacée
                maxgreen = g;
            }
            if (r < minblue) {     //plus petit que la valeur minimale = remplacée
                minblue = b;
            }
            if (r > maxblue) {     //plus grand que la valeur maximale = remplacée
                maxblue = b;
            }

        }
        for(int i = 0; i < width* height; i++) {
            int r = Color.red(pixels[i]); //capture la valeur courante du rouge
            int contrred = (r - minred) * 255 / (maxred - minred);
            int g = Color.green(pixels[i]); //capture la valeur courante du vert
            int contrgreen = (g - mingreen) * 255 / (maxgreen - mingreen);
            int b = Color.blue(pixels[i]); //capture la valeur courante du bleu
            int contrblue = (b - minblue) * 255 / (maxblue - minblue);
            pixels[i] = Color.rgb(contrred, contrgreen, contrblue);
        }
        bmp.setPixels(pixels, 0, width, 0, 0, width, height);

    }

    protected void contrastEgHist(Bitmap bmp){ //ne fonctionne pas, l'image change pas
        System.out.println("contraste egalisation histogramme");
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] pixels = new int[width * height];
        float[] hsv = new float[3];
        int minred = 255;  //255 = pire valeur possible
        int maxred = 0; //0 = pire valeur possible
        int mingreen = 255;  //255 = pire valeur possible
        int maxgreen = 0; //0 = pire valeur possible
        int minblue = 255;  //255 = pire valeur possible
        int maxblue = 0; //0 = pire valeur possible

        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < width * height; i++) {
            int r = Color.red(pixels[i]);   //capture la valeur de la couleur rouge
            int g = Color.green(pixels[i]);   //capture la valeur de la couleur vert
            int b = Color.blue(pixels[i]);   //capture la valeur de la couleur bleue
            if (r < minred) {     //plus petit que la valeur minimale = remplacée
                minred = r;
            }
            if (r > maxred) {     //plus grand que la valeur maximale = remplacée
                maxred = r;
            }
            if (r < mingreen) {     //plus petit que la valeur minimale = remplacée
                mingreen = g;
            }
            if (r > maxgreen) {     //plus grand que la valeur maximale = remplacée
                maxgreen = g;
            }
            if (r < minblue) {     //plus petit que la valeur minimale = remplacée
                minblue = b;
            }
            if (r > maxblue) {     //plus grand que la valeur maximale = remplacée
                maxblue = b;
            }

        }
        for(int i = 0; i < width* height; i++) {
            int r = Color.red(pixels[i]); //capture la valeur courante du rouge
            int contrred = (r - minred) * 255 / (maxred - minred);
            int g = Color.green(pixels[i]); //capture la valeur courante du vert
            int contrgreen = (g - mingreen) * 255 / (maxgreen - mingreen);
            int b = Color.blue(pixels[i]); //capture la valeur courante du bleu
            int contrblue = (b - minblue) * 255 / (maxblue - minblue);
            pixels[i] = Color.rgb(contrred, contrgreen, contrblue);
        }
        bmp.setPixels(pixels, 0, width, 0, 0, width, height);

    }

   /* protected void histogram(Bitmap bmp){
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] pixels = new int[width * height];
        float[] hsv = new float[3];

        for(int i = 0; i < nl; i++)
            for(int j = 0; j < nc; j++)
                hist[I(i, j)]++;
    } */

    protected void convolve(Bitmap bmp){
        System.out.println("Image convolved successfully");
    }

    protected void reset(){
        bmp = bmpSave.copy(bmp.getConfig() , true);
        imView.setImageBitmap(bmp);
        System.out.println("Image reset successfully");
    }

    protected void onStart() {
        super.onStart();
        Log.i("CV", "appli démarrée");
    }

    protected void onResume() {
        super.onResume();
        Log.i("CV", "appli reprise");
    }

    protected void onPause() {
        super.onPause();
        Log.i("CV", "appli en pause");

    }

    protected void onRestart() {
        super.onRestart();
        Log.i("CV", "appli redémarrée");

    }

    protected void onStop() {
        super.onStop();
        Log.i("CV", "appli arrétée");

    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i("CV", "appli DESTROYED !");

    }



}