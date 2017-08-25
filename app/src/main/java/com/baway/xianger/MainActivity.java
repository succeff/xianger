package com.baway.xianger;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    XRecyclerView recyclerView;
    List<Data.DataBean> list = new ArrayList<>();
    private MyAdapter adapter;
    int page=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadView();
        loadData();
        recyclerView = (XRecyclerView) findViewById(R.id.xrecycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(this,list);
        recyclerView.setAdapter(adapter);

        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        recyclerView.refreshComplete();
                    }
                },1000);

            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        loadView();
                        recyclerView.loadMoreComplete();
                    }
                },1000);

            }
        });
      adapter.setListener(new MyAdapter.OnItemClickListener() {
          @Override
          public void OnItemClickListener(View view, int position) {
              Intent intent = new Intent(MainActivity.this, TwoActivity.class);
              startActivity(intent);
              Toast.makeText(MainActivity.this,list.get(position).getIntroduction(),Toast.LENGTH_SHORT).show();
          }
      });

    }

    private void loadData() {
        if(isNetworkConnected(this)){
            Toast.makeText(this,"有网",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"没网",Toast.LENGTH_SHORT).show();
            laodMei();
        }
    }
    public  void laodMei() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("没有网络 是否连接网络");
        builder.setTitle("提示");
        builder.setPositiveButton("确认",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
                startActivity(wifiSettingsIntent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.create().show();

    }
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    private void loadView() {
              String url = "http://www.yulin520.com/a2a/impressApi/news/mergeList?sign=C7548DE604BCB8A17592EFB9006F9265&pageSize=20&gender=2&ts=1871746850&page="+page;
              HttpUrl.sendOkHttpRequest(url, new Callback() {
                  @Override
                  public void onFailure(Call call, IOException e) {

                  }

                  @Override
                  public void onResponse(Call call, Response response) throws IOException {
                      Data data = new Gson().fromJson(response.body().string(), Data.class);
                      list.addAll(data.getData());
                      Log.e("onResponse", "onResponse"+data.toString());

                  }
              });
    }



}
