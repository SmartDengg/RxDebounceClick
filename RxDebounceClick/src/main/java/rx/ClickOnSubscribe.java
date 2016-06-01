package rx;

import android.view.View;

/**
 * Created by Joker on 2016/4/21.sssssssssssssssss
 */
public class ClickOnSubscribe implements Observable.OnSubscribe<Void> {

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
