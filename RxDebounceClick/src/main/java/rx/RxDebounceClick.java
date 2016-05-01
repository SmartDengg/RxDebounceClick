package rx;

import android.view.View;

/**
 * Created by Joker on 2016/4/21.
 */
public class RxDebounceClick {

    private RxDebounceClick() {
        throw new IllegalStateException("No instances!");
    }

    public static Observable<Void> onClick(View view) {

        Utils.checkNotNull(view, "view == null");

        return Observable.create(new ClickOnSubscribe(view));
    }
}
