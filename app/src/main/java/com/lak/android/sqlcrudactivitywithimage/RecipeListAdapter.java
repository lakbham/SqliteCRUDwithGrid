package com.lak.android.sqlcrudactivitywithimage;

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.ArrayList;

    public class RecipeListAdapter extends BaseAdapter {

        private Context context;
        private int layout;
        private ArrayList<Recipe> recipiesList;

        public RecipeListAdapter(Context context, int layout, ArrayList<Recipe> recipiesList) {
            this.context = context;
            this.layout = layout;
            this.recipiesList = recipiesList;
        }

        @Override
        public int getCount() {
            return recipiesList.size();
        }

        @Override
        public Object getItem(int position) {
            return recipiesList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder {
            ImageView imageView;
            TextView txtName, txtIngred;

        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            View row = view;
            ViewHolder holder = new ViewHolder();

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(layout, null);

                holder.txtName = (TextView) row.findViewById(R.id.txtName);
                holder.txtIngred = (TextView) row.findViewById(R.id.txtIngred);
                holder.imageView = (ImageView) row.findViewById(R.id.imgRecipe);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

           Recipe recipe = recipiesList.get(position);

            holder.txtName.setText(recipe.getName());
            holder.txtIngred.setText(recipe.getIngred());

            byte[] RecipeImage = recipe.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(RecipeImage, 0, RecipeImage.length);
            holder.imageView.setImageBitmap(bitmap);

            return row;
        }
    }


