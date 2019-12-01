package com.example.myapplication3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button textbtn,help,search;
    private ListView lv;
    private Intent i;
    private MyAdapter adapter;

    private  NotesDB notesDB;
    SQLiteDatabase dbReader;
    private Cursor cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    public void initView(){
        lv =(ListView) findViewById(R.id.list);
        textbtn=(Button)findViewById(R.id.text);
        help =(Button) findViewById(R.id.help);
        search=(Button)findViewById(R.id.search) ;
        textbtn.setOnClickListener(this);
        //notesDB=new NotesDB(this);
        notesDB=new NotesDB(this,"notes",null,1);
        search.setOnClickListener(this);
        help.setOnClickListener(this);
        dbReader=notesDB.getReadableDatabase();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = dbReader.query(NotesDB.TABLE_NAME,null,null,null,
                        null,
                        null,null);
                cursor.moveToPosition(position);
                Intent i=new Intent(MainActivity.this,SelectAct.class);
               i.putExtra(NotesDB.ID,cursor.getInt(cursor.getColumnIndex(NotesDB.ID)));
               i.putExtra(NotesDB.CONTENT,cursor.getString(cursor.getColumnIndex(NotesDB.CONTENT)));
               i.putExtra(NotesDB.TIME,cursor.getString(cursor.getColumnIndex(NotesDB.TIME)));
                startActivity(i);

            }
        });
}


    @Override
    public void onClick(View v) {
        i = new Intent(this,AddContent.class);
        switch (v.getId()){
            case  R.id.text:
               //i.putExtra("flag","1");
                startActivity(i);
                break;
            case  R.id.help:
                Intent intent_help = new Intent(MainActivity.this,HelpAct.class);
                startActivity(intent_help);
                break;
            case  R.id.search:
                Intent intent_search = new Intent(MainActivity.this,SearchAct.class);
                startActivity(intent_search);
                break;
        }
    }
    public void selectDB(){
        Cursor cursor = dbReader.query(NotesDB.TABLE_NAME,null,null,null,null,
                null,null);
        adapter=new MyAdapter(this,cursor);
        lv.setAdapter(adapter);



    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDB();
    }



}
