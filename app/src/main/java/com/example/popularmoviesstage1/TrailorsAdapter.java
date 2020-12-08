package com.example.popularmoviesstage1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class TrailorsAdapter extends   RecyclerView.Adapter<TrailorsAdapter.NumberViewHolder> {




    MoviesModel moviesModel;
    Context context;

    final private ListItemClickListener mOnClickListener ;
    private static int viewHolderCount;
    public interface ListItemClickListener {

        void onClick(MoviesModel model, int position);
    }
    public TrailorsAdapter( ListItemClickListener listener) {

        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    public  TrailorsAdapter(MainActivity mainActivity, ListItemClickListener mOnClickListener, MoviesModel moviesModel)
    {
        this.mOnClickListener = mOnClickListener;
        this.moviesModel=moviesModel;
    }

    private static final String TAG = TrailorsAdapter.class.getSimpleName();


    public TrailorsAdapter(ListItemClickListener mOnClickListener, MoviesModel moviesModel ,Context context) {
        this.mOnClickListener = mOnClickListener;
        this.moviesModel=moviesModel;
        this.context=context;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.trailor_show_movies;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NumberViewHolder holder,final int position) {
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri="https://www.youtube.com/watch?v="+moviesModel.getMovie_key().get(position).toString();
                DetailsActivity detailsActivity=(DetailsActivity) context ;
                detailsActivity.openweb(uri);
            }
        });


    }


    @Override
    public int getItemCount() {
        if (moviesModel !=null)
           return moviesModel.getMovie_key().size();

        else
            return 5;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button button;

        public NumberViewHolder(View itemView) {
            super(itemView);
            button=itemView.findViewById(R.id.trailorButton);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onClick(moviesModel , getAdapterPosition());
        }
    }

}

