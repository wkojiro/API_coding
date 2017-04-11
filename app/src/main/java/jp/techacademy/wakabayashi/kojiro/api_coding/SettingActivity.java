package jp.techacademy.wakabayashi.kojiro.api_coding;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {

    private ListView mListView;
    private DestAdapter mDestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // ListViewの設定
        mDestAdapter = new DestAdapter(SettingActivity.this);
        mListView = (ListView) findViewById(R.id.listView);


        // ListViewをタップしたときの処理
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 入力・編集する画面に遷移させる
            }
        });

        // ListViewを長押ししたときの処理
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // タスクを削除する

                return true;
            }
        });
        reloadListView();
    }
    private void reloadListView() {

        Log.d("debug","reloadListView");
        // 後でTaskクラスに変更する
        List<String> destList = new ArrayList<String>();
        destList.add("aaa");
        destList.add("aaa");
        destList.add("aaa");
        destList.add("aaa");
        destList.add("aaa");
        destList.add("aaa");

        mDestAdapter.setDestList(destList);
        mListView.setAdapter(mDestAdapter);

        mDestAdapter.notifyDataSetChanged();
    }


}

