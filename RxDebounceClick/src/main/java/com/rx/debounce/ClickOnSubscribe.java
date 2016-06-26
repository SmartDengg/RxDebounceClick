package com.rx.debounce;

import android.view.View;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Joker on 2016/4/21.
 */
public class ClickOnSubscribe implements Observable.OnSubscribe<Void> {

    private View view;

    public ClickOnSubscribe(View view) {
        this.view = view;
    }

    @Override
    public void call(final Subscriber<? super Void> subscriber) {

        Utils.checkMainThread();

        final DebounceClickProducer<? super Void> clickProducer = new DebounceClickProducer<>(view, subscriber);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!subscriber.isUnsubscribed()) {
                    clickProducer.requestMore();
                }
            }
        };

        view.setOnClickListener(listener);

        subscriber.setProducer(clickProducer);
        subscriber.add(clickProducer);
    }
}
