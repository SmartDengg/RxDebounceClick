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
import com.smartdengg.debounce.RxDebounceClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.util.RxRingBuffer;

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
                       .observeOn(AndroidSchedulers.mainThread(), RxRingBuffer.SIZE)
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
