package com.rx.debounce;

import android.os.Looper;
import android.view.View;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by SmartDengg on 2016/4/21.
 * *
 * A click listener that debounces multiple clicks posted in the same frame. A click on one button disables all buttons for that frame.
 */
public class DebounceClickProducer<T> extends AtomicBoolean implements Subscription, Producer {

    private View view;
    private Subscriber<T> subscriber;
    private final AtomicBoolean unsubscribed = new AtomicBoolean();

    private final Runnable clickable = new Runnable() {
        @Override
        public void run() {
            DebounceClickProducer.this.set(true);
        }
    };

    public DebounceClickProducer(View view, Subscriber<T> subscriber) {
        this.view = view;
        this.subscriber = subscriber;
    }

    @Override
    public void request(long n) {
        if (n < 0) throw new IllegalArgumentException("n < 0: " + n);
        if (n == 0) return; // Nothing to do when requesting 0.
        if (!DebounceClickProducer.this.getAndSet(true)) return;//Click was already triggered.

        if (!subscriber.isUnsubscribed()) {

            this.set(false);
            subscriber.onNext(null);
            view.post(clickable);
        }
    }

    public void requestMore() {
        DebounceClickProducer.this.request(1);
    }

    @Override
    public void unsubscribe() {

        if (unsubscribed.compareAndSet(false, true)) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                DebounceClickProducer.this.clearClickListener();
            } else {
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        DebounceClickProducer.this.clearClickListener();
                    }
                });
            }
        }
    }

    @Override
    public boolean isUnsubscribed() {
        return this.unsubscribed.get();
    }

    private void clearClickListener() {
        if (view != null) view.setOnClickListener(null);
    }
}
