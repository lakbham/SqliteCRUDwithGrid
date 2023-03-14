package com.lak.android.sqlcrudactivitywithimage;

        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.database.sqlite.SQLiteStatement;

    public class SQLiteHelper extends SQLiteOpenHelper {

        public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        public void queryData(String sql) {
            SQLiteDatabase database = getWritableDatabase();
            database.execSQL(sql);
        }

        public void insertData(String name, String ingred, Integer preptime, Integer nutvalue, byte[] image) {
            SQLiteDatabase database = getWritableDatabase();
            String sql = "INSERT INTO Recipe VALUES (NULL, ?, ?, ?, ?, ?)";

            SQLiteStatement statement = database.compileStatement(sql);
            statement.clearBindings();

            statement.bindString(1, name);
            statement.bindString(2, ingred);
            statement.bindLong(3, preptime);
            statement.bindLong(4, nutvalue);
            statement.bindBlob(5, image);

            statement.executeInsert();
        }

        public void updateData(String name, String ingred, Integer preptime, Integer nutvalue, byte[] image, int id) {
            SQLiteDatabase database = getWritableDatabase();

            String sql = "UPDATE Recipe SET name = ?, ingred = ?, preptime = ?, nutvalue = ?, image = ? WHERE id = ?";
            SQLiteStatement statement = database.compileStatement(sql);

            statement.bindString(1, name);
            statement.bindString(2, ingred);
            statement.bindDouble(3, (double) preptime);
            statement.bindDouble(4, (double) nutvalue);
            statement.bindBlob(5, image);
            statement.bindDouble(6, (double) id);

            statement.execute();
            database.close();
        }

        public void deleteData(int id) {
            SQLiteDatabase database = getWritableDatabase();

            String sql = "DELETE FROM Recipe WHERE id = ?";
            SQLiteStatement statement = database.compileStatement(sql);
            statement.clearBindings();
            statement.bindDouble(1, (double) id);

            statement.execute();
            database.close();
        }

        public Cursor getData(String sql) {
            SQLiteDatabase database = getReadableDatabase();
            return database.rawQuery(sql, null);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
           // database.execSQL("create table Recipe(id INTEGER primary key,name TEXT,ingred TEXT,preptime int nutvalue int, image BLOB)");
        }



        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
          //database.execSQL("drop table if exists Recipe");
        }
    }


