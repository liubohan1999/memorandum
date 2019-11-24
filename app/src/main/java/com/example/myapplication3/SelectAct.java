package com.example.myapplication3;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectAct extends Activity  implements View.OnClickListener {

    private Button s_delete,s_save;
    private EditText s_tv;
    private NotesDB notesDB;
    private SQLiteDatabase dbwriter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
       // System.out.println(getIntent().getIntExtra(NotesDB.ID,0));
        s_delete=(Button)findViewById(R.id.s_delete);
        s_save=(Button)findViewById(R.id.s_save);
        s_tv=(EditText)findViewById(R.id.s_tv);
        notesDB =new NotesDB(this);
        dbwriter=notesDB.getWritableDatabase();
        s_delete.setOnClickListener(this);
        s_save.setOnClickListener(this);

        s_tv.setText(getIntent().getStringExtra(NotesDB.CONTENT));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.s_delete:
                deleteDate();
                finish();
                break;
            case R.id.s_save:
                saveDate();
                finish();

                break;
        }

    }

    public void deleteDate(){
        dbwriter.delete(NotesDB.TABLE_NAME,
                "_id=" +getIntent().getIntExtra(NotesDB.ID,0),null);
    }

    public void saveDate(){
        ContentValues cv =new ContentValues();
        cv.put(NotesDB.CONTENT,s_tv.getText().toString());
        cv.put(NotesDB.TIME,getTime());
        dbwriter.update(NotesDB.TABLE_NAME,cv,"_id=" +getIntent().getIntExtra(NotesDB.ID,0),null);

    }
    public String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date();
        String str = format.format(curDate);
        return str;
    }


}
