package com.johnson.pablo.poesiaperuana.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.johnson.pablo.poesiaperuana.R;
import com.johnson.pablo.poesiaperuana.domain.model.Poet;
import com.johnson.pablo.poesiaperuana.presentation.utils.media.ImageFactory;
import com.johnson.pablo.poesiaperuana.presentation.utils.media.loaders.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pjohnson on 9/04/17.
 */
public class PoetsRecyclerViewAdapter extends RecyclerView.Adapter<PoetsRecyclerViewAdapter.ViewHolder> {

    private final List<Poet> poets;
    private final LayoutInflater inflater;

    public PoetsRecyclerViewAdapter(Context context, List<Poet> poets) {
        this.poets = poets;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.poets_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Poet poet = poets.get(position);
        holder.poetName.setText(poet.getName());
        holder.poetDateLife.setText(poet.getBirthDate());
        holder.poetCity.setText(poet.getCity());
        ImageFactory.getLoader().loadFromUrl(poet.getImageUrl(), holder.poetImageView,
                ImageLoader.ImageTransformation.CIRCLE, null);
    }

    @Override
    public int getItemCount() {
        return poets.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.poetName)
        TextView poetName;
        @BindView(R.id.poetDateLife)
        TextView poetDateLife;
        @BindView(R.id.poetCity)
        TextView poetCity;
        @BindView(R.id.poetImageView)
        ImageView poetImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
