package jp.techacademy.wakabayashi.kojiro.api_coding;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private ImageView mImageView;

    private Integer testcount = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.imageView);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(testcount == 0) {
                    new DownloadImageTask(mImageView)
                            .execute("https://pbs.twimg.com/profile_images/61655057/2425718692_1783fe0913_b.jpg");
                } else if(testcount == 1){

                    new postMailTask().execute();
                } else if(testcount == 2){
                    new getDistTask().execute();
                }

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //main.xmlの内容を読み込む
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    private class getDistTask extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... params) {

            final MediaType JSON
                    = MediaType.parse("application/json; charset=utf-8");

            String url = "https://rails5api-wkojiro1.c9users.io/destinations.json?email=test00@test.com&token=1:YUJo6C_adXVod4na3onD";

            String result = null;

            // リクエストオブジェクトを作って
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            // クライアントオブジェクトを作って
            OkHttpClient client = new OkHttpClient();

            // リクエストして結果を受け取って
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()){
                    result = response.body().string();
                    Log.d("hoge", "doGet success");
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("hoge", "error orz:" + e.getMessage(), e);
            }

            // 返す
            return null;


        }

        @Override
        protected void onPostExecute(Void result){




        }
    }


    private class postMailTask extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... params) {

            final MediaType JSON
                    = MediaType.parse("application/json; charset=utf-8");

            String url = "https://rails5api-wkojiro1.c9users.io/trackings.json?email=test00@test.com&token=1:YUJo6C_adXVod4na3onD";
            String[] paramds = {"test","test@test.com","139.513","35.692"};

            String result = null;

            final String json =
                    "{" +
                            "\"destname\":\"" + paramds[0] + "\"," +
                            "\"destemail\":\"" + paramds[1] + "\"," +
                            "\"destaddress\":\"\"," +
                            "\"nowlatitude\":\"" + paramds[2] + "\"," +
                            "\"nowlongitude\":\"" + paramds[3] + "\"" +
                            "}";


            RequestBody body = RequestBody.create(JSON, json);


            // リクエストオブジェクトを作って
            Request request = new Request.Builder()
                    .url(url)
                    //.header("Authorization", credential)
                    .post(body)
                    .build();

            // クライアントオブジェクトを作って
            OkHttpClient client = new OkHttpClient();

            // リクエストして結果を受け取って
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()){
                    result = response.body().string();
                    Log.d("hoge", "doPost success");
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("hoge", "error orz:" + e.getMessage(), e);
            }

            // 返す
            return null;

        }

        @Override
        protected void onPostExecute(Void result){




        }

    }



    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {

            final OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(urls[0])
                    .build();

            Response response = null;
            Bitmap mIcon11 = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (response.isSuccessful()) {
                try {
                    mIcon11 = BitmapFactory.decodeStream(response.body().byteStream());
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }

            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
