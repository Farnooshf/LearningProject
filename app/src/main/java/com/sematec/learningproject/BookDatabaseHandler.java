package com.sematec.learningproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Farnoosh on 3/11/2016.
 */
public class BookDatabaseHandler extends SQLiteOpenHelper {

    private PublicMethods pm;

    String db_create_query = "" +
            "CREATE TABLE books_tbl (" +
            " _id INTEGER AUTO INCEREMNT PRIMARY KEY ," +
            " name TEXT ," +
            " isbn TEXT ," +
            " author TEXT ," +
            " publisher TEXT ," +
            " year INTEGER ," +
            " image_url TEXT ," +
            " page_count INTEGER  " +
            ")" +
            "";

    public void insert(BookModel bookModel) {

        String insertQuery = "INSERT INTO books_tbl " +
                "(name , isbn , author   ,  publisher  ,  year  ,  image_url  ,   page_count )" +
                "VALUES( '" + bookModel.getName() + "' ,  '" + bookModel.getIsbn() + "' ,  " +
                "'" + bookModel.getAuthor() + "' ," +
                "  '" + bookModel.getPublisher() + "' ," +
                "  " + bookModel.getYear() + " ,  '" + bookModel.getImage_url() + "'  , " + bookModel.getPage_count() + " )";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertQuery);
        db.close();
    }

    public BookModel getRecords(String isbn) {
        BookModel bookModel = null;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  name , isbn , publisher ,year  from books_tbl where isbn='" + isbn + "'", null);
        while (cursor.moveToNext()) {
            bookModel = new BookModel();
            bookModel.setName(cursor.getString(0));
            bookModel.setIsbn(cursor.getString(1));
            bookModel.setPublisher(cursor.getString(2));
            bookModel.setYear(Integer.parseInt(cursor.getString(3)));
        }
        db.close();
        return bookModel;
    }

    public BookDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create_query);

     }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
