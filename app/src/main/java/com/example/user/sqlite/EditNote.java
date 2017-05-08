package com.example.user.sqlite;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

/**
 * Created by User on 2017/5/8.
 */

public class EditNote extends AppCompatActivity {
     SQLiteDatabase db;
     DBOpenHelper  openhelper;
     EditText TitleBox ;
     EditText BodyBox;
     String from_Title;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
                 super.onCreate(savedInstanceState);
                 setContentView(R.layout.activity_edit_note);
                 Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                 setSupportActionBar(toolbar);


                 Intent from= getIntent();
                 from_Title = from.getStringExtra("NoteTitle");


                 TitleBox = (EditText)findViewById(R.id.input_title);
                 BodyBox = (EditText)findViewById(R.id.input_context);


                 openhelper = new DBOpenHelper (this);
                 db = openhelper.getWritableDatabase();
                 Cursor c = db.rawQuery("select *  from " +
                                 MainActivity.DATABASE_TABLE, null);
                 String[]names = c.getColumnNames();


                 if(from_Title.equals("title")){
                     }
                 else{
                         String body = c.getString(c.getColumnIndex(names[0]));
                         TitleBox.setText(from_Title);
                         BodyBox.setText(body);
                     }


                 FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                 fab.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                                 Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                         .setAction("Action", null).show();
                             }
         });
             }


        class DBOpenHelper extends SQLiteOpenHelper {
         public DBOpenHelper(Context context) {
                         super(context, "demo.db", null, 2);
                     }
         @Override
         public void onCreate(SQLiteDatabase db) {
                        // db.execSQL("create table " +
                         //        MainActivity.DATABASE_TABLE + "(title,body);");
                     }
         @Override
         public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
                         //Log.d("LINCYU", "onUpgrade: " + oldV + " -> " + newV);
                         //db.execSQL("alter table " + MainActivity.DATABASE_TABLE +
                                 //" add column date;");
                     }
    }




             @Override
     public void onStop() {
                 super.onStop();
                 if(TitleBox.getText().toString().isEmpty()){


                     }
                 else{
                         if(from_Title.equals("title")){
                                 db.execSQL("insert into "+MainActivity.DATABASE_TABLE+" values('"+TitleBox.getText().toString()
                                             +"','"+BodyBox.getText().toString()+"')");
                             }
                         else{
                                 db.execSQL("delete from "+MainActivity.DATABASE_TABLE+" where title="+from_Title);
                                 db.execSQL("insert into "+MainActivity.DATABASE_TABLE+" values("+TitleBox.getText().toString()
                                                 +","+BodyBox.getText().toString()+")");
                             }
                     }
                 // ATTENTION: This was auto-generated to implement the App Indexing API.
                 // See https://g.co/AppIndexing/AndroidStudio for more information.


             }
 }


