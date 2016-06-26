package com.rx.debounce;

import android.view.View;
import rx.Observable;

/**
 * Created by Joker on 2016/4/21.
 */
public class RxDebounceClick {

    private RxDebounceClick() {
        throw new IllegalStateException("No instances!");
    }

    public static Observable<Void> onClick(View view) {

        return Observable.create(new ClickOnSubscribe(Utils.checkNotNull(view, "view == null")));
    }
}
