package com.lak.android.sqlcrudactivitywithimage;

//Created by Lakshmi --working -- AVD name -- Pixel 3 API24

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Shader;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
        import android.Manifest;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.drawable.BitmapDrawable;
        import android.net.Uri;
        import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Activity;
       // import android.support.annotation.NonNull;

      import androidx.core.app.ActivityCompat;

        //import android.support.v4.app.ActivityCompat;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.Build;
import android.graphics.Canvas;
        import java.io.ByteArrayOutputStream;
        import java.io.FileNotFoundException;
        import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    EditText edtName1, edtIngred1, edtPreptime1, edtNutvalue1;
    Button btnChoose, btnAdd, btnList, btnRecycler;
    ImageView imageView;

    final int REQUEST_CODE_GALLERY = 999;
    private long pressedTime;

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
      sqLiteHelper = new SQLiteHelper(this, "RecipeDB.db", null, 1);
        //sqLiteHelper = openOrCreateDatabase('RecipeDB.sqlite',MODE_PRIVATE,null);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS Recipe(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, ingred VARCHAR, preptime INTEGER, nutvalue INTEGER, image BLOB)");

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sqLiteHelper.insertData(
                            edtName1.getText().toString().trim(),
                            edtIngred1.getText().toString().trim(),
                            Integer.parseInt(edtPreptime1.getText().toString()),
                            Integer.parseInt(edtNutvalue1.getText().toString()),
                            imageViewToByte(imageView)
                    );
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                    edtName1.setText("");
                    edtIngred1.setText("");
                    edtPreptime1.setText("");
                    edtNutvalue1.setText("");
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecipeList.class);
                startActivity(intent);
            }
        });

        btnRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, DisplayRecyclerView.class);
                startActivity(intent1);
            }
        });
    }

    // In the below code, when the user presses the ‘BACK’ button once, they are greeted with a toast asking them to press it again to exit.
    // If the user then presses ‘BACK’ again within 2 seconds(2000ms), then the app is closed, otherwise, we remain there.
    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }


    public static byte[] imageViewToByte(ImageView image) {
        try {
            Bitmap bitmap;
            Drawable drawable;
            drawable=null;
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            return byteArray;
           // return bitmap;
        } catch (OutOfMemoryError e) {
            // Handle the error
            return null;
        }
    }
    //// Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ////ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ////bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        ////byte[] byteArray = stream.toByteArray();
        ////return byteArray;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        edtName1 = (EditText) findViewById(R.id.edtName);
        edtIngred1 = (EditText) findViewById(R.id.edtIngred);
        edtPreptime1 = (EditText) findViewById(R.id.edtPreptime);
        edtNutvalue1 = (EditText) findViewById(R.id.edtNutvalue);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnList = (Button) findViewById(R.id.btnList);
        btnRecycler = (Button) findViewById(R.id.btnRecycler);
        imageView = (ImageView) findViewById(R.id.imageView);
    }


}
