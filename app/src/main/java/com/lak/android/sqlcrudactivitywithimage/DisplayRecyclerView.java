package com.lak.android.sqlcrudactivitywithimage;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

//import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class DisplayRecyclerView extends AppCompatActivity{

    RecyclerView recyclerView;
    //RecyclerAdapter adapter;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Recipe> arrayList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle SavedInstanceState) {

        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_display_list);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setHasFixedSize(true);
       //sqLiteHelper = new SQLiteHelper(this, "RecipeDB.sqlite", null, 1);
        SQLiteHelper sqLiteHelper = new SQLiteHelper( this, "RecipeDB.sqlite", null, 1);
        SQLiteDatabase SqLiteDatabase = sqLiteHelper.getReadableDatabase();

        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM Recipe");
        arrayList.clear();

   //Cursor cursor = sqliteHelper.getInformation(SqLiteDatabase);
    if( cursor != null && cursor.moveToFirst())
      do {
         //byte[] image = cursor.getBlob(5);
      //   int id = cursor.getInt(0);
         String  name = cursor.getString(1);
          String  ingred = cursor.getString(2);
          int preptime = cursor.getInt(3);
          int nutvalue = cursor.getInt(4);
          byte[] image = cursor.getBlob(5);
          //Toast.makeText(getApplicationContext(), "nut value is"+nutvalue,
            //      Toast.LENGTH_LONG).show();

          arrayList.add(new Recipe(name, ingred, preptime, nutvalue, image,0));

        }while (cursor.moveToNext());
      sqLiteHelper.close();
    adapter = new RecyclerAdapter(arrayList,this);
    recyclerView.setAdapter(adapter);
    }

}
