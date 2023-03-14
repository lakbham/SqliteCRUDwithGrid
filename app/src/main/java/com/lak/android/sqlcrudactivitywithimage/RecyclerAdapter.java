package com.lak.android.sqlcrudactivitywithimage;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.LinearLayout;
////import android.support.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

////import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{
    ArrayList<Recipe> arrayList = new ArrayList<>();

    Context ctx;
    RecyclerAdapter(ArrayList<Recipe> arrayList,Context ctx)
    {
        this.arrayList = arrayList;
        this.ctx=ctx;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
          RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,ctx,arrayList);
        return  recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
    Recipe recipe = arrayList.get(position);
        byte[] RecipeImage = recipe.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(RecipeImage, 0, RecipeImage.length);
        holder.imv.setImageBitmap(bitmap);
        holder.Name.setText(recipe.getName());
        holder.Nut.setText(Integer.toString(recipe.getNutvalue()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView imv;
        TextView Name, Nut;
        ArrayList<Recipe> recipes=new ArrayList<Recipe>();
        Context ctx;
        RecyclerViewHolder(View view, Context ctx,ArrayList<Recipe> recipes )
        {
            super(view);
            this.recipes=recipes;
            this.ctx=ctx;
            view.setOnClickListener(this);
            imv= (ImageView) view.findViewById(R.id.imageView2);
            Name=(TextView) view.findViewById(R.id.recname);
            Nut=(TextView)view.findViewById(R.id.recnut);

        }

        @Override
        public void onClick(View v) {
        int position =getAdapterPosition();
           //Toast.makeText(ctx, "value of Position is"+position,
             //       Toast.LENGTH_LONG).show();
        Recipe recipe =this.recipes.get(position);

       Intent intent =new Intent(this.ctx,RecipeDetails.class);
       //added code
            imageViewToByte(imv);
       //added code end
        intent.putExtra("imv",recipe.getImage());

        intent.putExtra("name",recipe.getName());
        intent.putExtra("Nut",recipe.getNutvalue());
        intent.putExtra("ingred",recipe.getIngred());
        intent.putExtra("preptime",recipe.getPreptime());
        this.ctx.startActivity(intent);

        }
    //added code begin
        public static byte[] imageViewToByte(ImageView image) {
            Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            return byteArray;
        }
        //added code end
    }
}
