package com.smartdengg.debounce;

import android.view.View;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.plugins.RxJavaPlugins;

/**
 * Created by SmartDengg on 2016/6/26.
 */
class OperatorDebounce<T> implements Observable.Operator<T, T> {

    private static OperatorDebounce<?> instance;

    private View view;

    public OperatorDebounce(View view) {
        this.view = view;
    }

    @SuppressWarnings("unchecked")
    public static <T> OperatorDebounce<T> getInstance(View view) {
        if (instance == null) {
            synchronized (OperatorDebounce.class) {
                if (instance == null) instance = new OperatorDebounce<>(view);
            }
        }
        return (OperatorDebounce<T>) instance;
    }

    @Override
    public Subscriber<? super T> call(Subscriber<? super T> childSubscriber) {

        final ParentSubscriber<T> parentSubscriber = new ParentSubscriber<>(childSubscriber);
        childSubscriber.add(parentSubscriber);
        childSubscriber.setProducer(new Producer() {
            @Override
            public void request(long n) {
                parentSubscriber.requestMore(n);
            }
        });

        final DebounceClickProducer<? super T> clickProducer = new DebounceClickProducer<>(view, childSubscriber);
        parentSubscriber.add(clickProducer);
        parentSubscriber.setProducer(clickProducer);

        return parentSubscriber;
    }

    private static class ParentSubscriber<T> extends Subscriber<T> {

        private final Subscriber<? super T> child;

        private ParentSubscriber(Subscriber<? super T> child) {
            this.child = child;
        }

        @Override
        public void onStart() {
            this.request(0L);
        }

        @Override
        public void onCompleted() {
            child.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            RxJavaPlugins.getInstance()
                         .getErrorHandler()
                         .handleError(e);
            child.onError(e);
        }

        @Override
        public void onNext(T t) {
            this.requestMore(1L);
        }

        public void requestMore(long n) {
            this.request(n);
        }
    }

}
