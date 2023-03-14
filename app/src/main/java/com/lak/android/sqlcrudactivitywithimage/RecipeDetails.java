package com.lak.android.sqlcrudactivitywithimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import androidx.appcompat.app.AppCompatActivity;
////import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class RecipeDetails extends AppCompatActivity {
    ImageView imageViewa, imageViewb, imageViewc, imageViewd, imageViewe, imageViewf, imageView;
    TextView tx_name, tx_ingred, tx_preptime, tx_nutvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_details_layout);
        imageView = (ImageView) findViewById(R.id.imageView3);
        tx_name = (TextView) findViewById(R.id.textView);
        tx_preptime = (TextView) findViewById(R.id.textView2);
        tx_nutvalue = (TextView) findViewById(R.id.textView3);
        tx_ingred = (TextView) findViewById(R.id.textView5);

        imageViewa = (ImageView) findViewById(R.id.imageView5);
        imageViewb = (ImageView) findViewById(R.id.imageView6);
        imageViewc = (ImageView) findViewById(R.id.imageView7);
        imageViewd = (ImageView) findViewById(R.id.imageView8);
        imageViewe = (ImageView) findViewById(R.id.imageView9);
        imageViewf = (ImageView) findViewById(R.id.imageView10);

        // imp code to decode the bytearray and put in the next activity
        Bitmap b = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("imv"), 0, getIntent().getByteArrayExtra("imv").length);
        imageView.setImageBitmap(b);
        tx_name.setText("Name:   " + getIntent().getStringExtra("name"));
        tx_ingred.setText("Ingredients:   " + getIntent().getStringExtra("ingred"));
        tx_preptime.setText("PrepTime:    " + getIntent().getIntExtra("preptime", 00));
        tx_nutvalue.setText("NutValue: " + getIntent().getIntExtra("Nut", 00));

    }


 }

