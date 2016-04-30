package com.smartdengg.example;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import rx.Observer;

/**
 * Created by Joker on 2016/4/28.
 */
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> implements Observer<Entity> {

    private Context context;
    private List<Entity> items = new ArrayList<>();

    public InfoAdapter(Context context) {
        this.context = context;
    }

    public static InfoAdapter created(Context context) {
        return new InfoAdapter(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context)
                                            .inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((TextView) holder.itemView).setText(this.items.get(position)
                                                       .toString());
    }

    @Override
    public int getItemCount() {
        return (this.items != null) ? this.items.size() : 0;
    }

    @Override
    public void onCompleted() {
        /*never invoke*/
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onNext(Entity entity) {

        this.items.add(entity);
        this.notifyItemInserted(items.size());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
