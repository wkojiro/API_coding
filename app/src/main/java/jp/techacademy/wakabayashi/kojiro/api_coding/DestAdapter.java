package jp.techacademy.wakabayashi.kojiro.api_coding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wkojiro on 2017/04/11.
 */

public class DestAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;

    //memo:アイテムを保持する LIst を mTaskList という名前で定義します。setterも実装しておきます。
    // クラスはタスクを表す Task クラスに後ほど修正しますがまずは String クラスとしておきます。Task クラスは後のRealmのチャプターで実装します
    private List<String> mDestList;


    public DestAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void setDestList(List<String> destList) {
        mDestList = destList;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(android.R.layout.simple_list_item_2, null);
        }

        TextView textView1 = (TextView) convertView.findViewById(android.R.id.text1);
        TextView textView2 = (TextView) convertView.findViewById(android.R.id.text2);

        // 後でTaskクラスから情報を取得するように変更する
        textView1.setText(mDestList.get(position));

        return convertView;
    }
}
