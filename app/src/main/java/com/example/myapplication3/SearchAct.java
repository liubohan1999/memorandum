package com.example.myapplication3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SearchAct extends AppCompatActivity implements View.OnClickListener {
    private NotesDB notesDB;
    private SQLiteDatabase dbReader;
    private Button btn_search;
    private EditText edtsearch;
    private ListView ls;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        notesDB = new NotesDB(this);
        dbReader = notesDB.getReadableDatabase();
        btn_search = findViewById(R.id.searchbtn);
        edtsearch = findViewById(R.id.search_et);
        btn_search.setOnClickListener(this);
        ls = (ListView) findViewById(R.id.list_search);
    }

    public void setWordsListView(List<Map<String, String>> items) {
        SimpleAdapter adapter;


        adapter = new SimpleAdapter(this, items, R.layout.port_list_view_item, new String[]{
                NotesDB.CONTENT,
                NotesDB.TIME}, new int[]{R.id.tv_fm_words_details_word, R.id.tv_fm_words_details_meaning});


        ls.setAdapter(adapter);
    }


    public List<Map<String, String>> likeQuery(String searchContent) {
        List<Map<String, String>> result = new LinkedList<>();
        SQLiteDatabase db = notesDB.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + NotesDB.TABLE_NAME + " where " +
                NotesDB.CONTENT + " like ?", new String[]{"%" + searchContent + "%"});
        Map<String, String> item;
        while (cursor.moveToNext()) {
            item = new HashMap<>();
            String content = cursor.getString(cursor.getColumnIndex(NotesDB.CONTENT));
            String time = cursor.getString(cursor.getColumnIndex(NotesDB.TIME));

            item.put(NotesDB.CONTENT, content);
            item.put(NotesDB.TIME, time);
            result.add(item);
        }
        return result;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchbtn:
                String str_search = edtsearch.getText().toString();
                if (!str_search.equals(""))
                    setWordsListView(likeQuery(str_search));
                break;
        }
    }
}






