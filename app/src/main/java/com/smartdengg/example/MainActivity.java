package com.smartdengg.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.RxDebounceClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    @NonNull
    @Bind(R.id.recyclerview)
    protected RecyclerView recyclerView;

    @NonNull
    @Bind(R.id.button)
    protected Button button;

    private InfoAdapter infoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);

        MainActivity.this.setupAdapter();
        MainActivity.this.bindClick();
    }

    private void bindClick() {

        RxDebounceClick.onClick(button)
                       .observeOn(AndroidSchedulers.mainThread())
                       .doOnNext(new Action1<Void>() {
                           @Override
                           public void call(Void aVoid) {
                               MainActivity.this.recyclerView.scrollToPosition(infoAdapter.getItemCount());
                           }
                       })
                       .map(new Func1<Void, Entity>() {
                           @Override
                           public Entity call(Void aVoid) {

                               long timeline = System.currentTimeMillis();
                               String processInfo = Thread.currentThread()
                                                          .toString();

                               return new Entity(timeline, processInfo);
                           }
                       })
                       .subscribe(this.infoAdapter);

        //https://jitpack.io/com/github/SmartDengg/RxDebounceClick/1.0.1/javadoc/
    }

    private void setupAdapter() {

        final LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.infoAdapter = InfoAdapter.created(MainActivity.this);
        this.recyclerView.setAdapter(infoAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(MainActivity.this);
    }
}
