package rx;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by Joker on 2016/4/21.
 */
public class RxDebounceClick {

    private RxDebounceClick() {
        throw new IllegalStateException("No instances!");
    }

    @CheckResult
    @NonNull
    public static Observable<Void> onClick(@NonNull View view) {
        return Observable.create(new ClickOnSubscribe(view));
    }
}
