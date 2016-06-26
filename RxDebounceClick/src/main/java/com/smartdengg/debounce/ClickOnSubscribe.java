package com.smartdengg.debounce;

import android.view.View;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Joker on 2016/4/21.
 */
class ClickOnSubscribe implements Observable.OnSubscribe<Void> {

    private View view;

    public ClickOnSubscribe(View view) {
        this.view = view;
    }

    @Override
    public void call(final Subscriber<? super Void> subscriber) {

        Utils.checkMainThread();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(null);
                }
            }
        };

        view.setOnClickListener(listener);
    }
}
