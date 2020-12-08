package com.example.popularmoviesstage1;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.NumberViewHolder> {


    MoviesModel moviesModel;

    final private ListItemClickListener mOnClickListener ;
    private static int viewHolderCount;
    public interface ListItemClickListener {
       //void onListItemClick(int clickedItemIndex);

        void onClick(MoviesModel model, int position);
    }
    public FilmAdapter( ListItemClickListener listener) {

        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    public  FilmAdapter(MainActivity mainActivity, ListItemClickListener mOnClickListener, MoviesModel moviesModel)
    {
        this.mOnClickListener = mOnClickListener;
        this.moviesModel=moviesModel;
    }

    private static final String TAG = FilmAdapter.class.getSimpleName();

    public FilmAdapter(ListItemClickListener mOnClickListener, MoviesModel moviesModel) {
        this.mOnClickListener = mOnClickListener;
        this.moviesModel=moviesModel;
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

       Picasso.get().load("http://image.tmdb.org/t/p/w185//"+moviesModel.getPoster_path().get(position)).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        if (moviesModel !=null)
        return moviesModel.getPoster_path().size();
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
            mOnClickListener.onClick(moviesModel , getAdapterPosition());
        }
        }

    }

