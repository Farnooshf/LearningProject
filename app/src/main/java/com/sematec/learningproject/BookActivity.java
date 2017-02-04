package com.sematec.learningproject;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Farnoosh on 3/11/2016.
 */
public class BookActivity extends Activity {
    private PublicMethods pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        BookDatabaseHandler db = new BookDatabaseHandler(this, "books_db", null, 2);
        BookModel bookModel = new BookModel();
        bookModel.setAuthor("ali");
        bookModel.setName("alireza");
        bookModel.setIsbn("212111");
        bookModel.setPublisher("AMirhossein Teymoori");
        bookModel.setYear(2016);
        bookModel.save();


    }


}

