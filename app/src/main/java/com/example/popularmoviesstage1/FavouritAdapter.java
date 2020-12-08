package com.example.popularmoviesstage1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesstage1.Database.Favourit_movies;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouritAdapter  extends RecyclerView.Adapter<FavouritAdapter.NumberViewHolder> {


    List<Favourit_movies> favouritMoviesList;

    final private ListItemClickListener mOnClickListener ;
    private static int viewHolderCount;
    public interface ListItemClickListener {
        //void onListItemClick(int clickedItemIndex);

        void onClick(List<Favourit_movies> model, int position);
    }
    public FavouritAdapter( ListItemClickListener listener) {

        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    public  FavouritAdapter(MainActivity mainActivity, ListItemClickListener mOnClickListener,List<Favourit_movies> favouritMoviesList)
    {
        this.mOnClickListener = mOnClickListener;
        this.favouritMoviesList=favouritMoviesList;
    }

    private static final String TAG = FilmAdapter.class.getSimpleName();

    public FavouritAdapter(ListItemClickListener mOnClickListener, List<Favourit_movies> favouritMoviesList) {
        this.mOnClickListener = mOnClickListener;
        this.favouritMoviesList=favouritMoviesList;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.film_image;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {

        Picasso.get().load("http://image.tmdb.org/t/p/w185//"+favouritMoviesList.get(position).getPoster()).into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        if (favouritMoviesList !=null)
            return favouritMoviesList.size();
        else
            return 5;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        public NumberViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.Movies_Picture);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onClick(favouritMoviesList , getAdapterPosition());
        }
    }

    public List<Favourit_movies> getFavouritMoviesList() {
        return favouritMoviesList;
    }
}

