package rx;

import android.os.Looper;
import android.view.View;

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

        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("Must be called from the main thread. Was: " + Thread.currentThread());
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.request(1);
                }
            }
        };

        view.setOnClickListener(listener);

        DebounceClickProducer<? super Void> clickProducer = new DebounceClickProducer<>(view, subscriber);
        subscriber.add(clickProducer);
        subscriber.setProducer(clickProducer);
    }
}
