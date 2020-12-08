package com.example.popularmoviesstage1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesstage1.Database.Database_movies;
import com.example.popularmoviesstage1.Database.Favourit_movies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements FilmAdapter.ListItemClickListener ,TrailorsAdapter.ListItemClickListener ,FavouritAdapter.ListItemClickListener {
    String urlJson="http://api.themoviedb.org/3/movie/popular?api_key=37789dc48bb9195cdca528bdbc31ce85";

    private FilmAdapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private TrailorsAdapter trailorAdapter;
    private FavouritAdapter favouritAdapter;
    TextView textView;
    MoviesModel moviesModel;
    MoviesModel moviesModelTrailor;
    String tag3="bv";
    String tag2="bv2";
    Button button;
int num;
Database_movies db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FechData fechData=new FechData();
//       // textView=findViewById(R.id.idd);
//        fechData.execute();

            recyclerView = (RecyclerView) findViewById(R.id.RV_Movies);
      //  recyclerView2 = (RecyclerView) findViewById(R.id.trailorMovies);
        db = Database_movies.getInstance(getApplicationContext());
        recyclerView2 = (RecyclerView) findViewById(R.id.RV_Movies);



        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("data")) {
                moviesModel =savedInstanceState.getParcelable("data");
                GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);

                mAdapter = new FilmAdapter(getAction(), moviesModel);

                recyclerView.setAdapter(mAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);


            }}else {

            FechData ff=new FechData();
            // textView=findViewById(R.id.idd);
            ff.execute();
        }
    }

    private MainActivity getAction() {
        return MainActivity.this;
    }


    /* ----------------------  Menu List Code--------------------------- */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId())
        {
            case R.id.SortByPopularity: {
                urlJson = "http://api.themoviedb.org/3/movie/popular?api_key=37789dc48bb9195cdca528bdbc31ce85";
                Toast.makeText(this, "Sorted By Poroperty", Toast.LENGTH_LONG).show();

                FechData ff=new FechData();
                ff.execute();
                break;
            }
            case R.id.SortByRate: {
               urlJson = "http://api.themoviedb.org/3/movie/top_rated?api_key=37789dc48bb9195cdca528bdbc31ce85";
                Toast.makeText(this, "Sorted By Rate", Toast.LENGTH_LONG).show();
                FechData f2=new FechData();
                f2.execute();
                break;
            }case R.id.Favourite: {
            favouritLoadData();
//            final LiveData<List<Favourit_movies>> list=  db.taskDao().loadAllTasks();
//
//            list.observe(this, new Observer<List<Favourit_movies>>() {
//                @Override
//                public void onChanged(@Nullable List<Favourit_movies> taskEntries) {
//                    Toast.makeText(MainActivity.this, "load live data succefuly", Toast.LENGTH_LONG).show();
//
//                    GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
//                    favouritAdapter=new FavouritAdapter(getAction(),taskEntries);
//
//                    recyclerView2.setAdapter(favouritAdapter);
//                    recyclerView2.setHasFixedSize(true);
//                    recyclerView2.setLayoutManager(layoutManager);
//
//                }
//            });

            Toast.makeText(this, "Sorted By Favourite", Toast.LENGTH_LONG).show();

            break;

        }

        }
      return  super.onOptionsItemSelected(item);

    }
public void favouritLoadData(){

    final LiveData<List<Favourit_movies>> list=  db.taskDao().loadAllTasks();

    list.observe(this, new Observer<List<Favourit_movies>>() {
        @Override
        public void onChanged(@Nullable List<Favourit_movies> taskEntries) {
            Toast.makeText(MainActivity.this, "load live data succefuly", Toast.LENGTH_LONG).show();

            GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
            favouritAdapter=new FavouritAdapter(getAction(),taskEntries);

            recyclerView2.setAdapter(favouritAdapter);
            recyclerView2.setHasFixedSize(true);
            recyclerView2.setLayoutManager(layoutManager);

        }
    });

}
    /* ----------------------  ------------------------------- */
/*-----------------------Recycle view onclick--------------------------*/

    @Override
      public void onClick(MoviesModel model, int position) {


                Intent intent = new Intent(this, DetailsActivity.class);
                intent.putExtra("poster_path" , model.getPoster_path().get(position));
                intent.putExtra("original_title" , model.getOriginal_title().get(position));
                intent.putExtra("vote_count" , model.getVote_count().get(position));
                intent.putExtra("release_date" , model.getRelease_date().get(position));
                intent.putExtra("overview" , model.getOverview().get(position));
                intent.putExtra("id" , model.getMovie_id().get(position));
                startActivity(intent);

     }

    @Override
    public void onClick(List<Favourit_movies> model, int position) {
//        Intent intent2 = new Intent(this, DetailsActivity.class);
//        intent2.putExtra("poster_path2" , model.get(position).getPoster());
//        intent2.putExtra("original_title2" , model.get(position).getTitle());
//        intent2.putExtra("vote_count2" , model.get(position).getVote_count());
//        intent2.putExtra("release_date2" , model.get(position).getRelease_date());
//        intent2.putExtra("overview2" , model.get(position).getOverview());
//
//        startActivity(intent2);
    }

    /*-------------------------------------------------*/
    public class FechData extends AsyncTask<URL, Void, String> {
        String data = "";

        @Override
        protected String doInBackground(URL... urls) {

            try {

                URL url = new URL(urlJson);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }


            } catch (MalformedURLException e) {

                return e.getMessage();
            } catch (IOException e) {
                return e.getMessage();
            }
            return data;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (data != null && !data.equals("")) {
                try {

                    JsonUtils jsonUtils = new JsonUtils();
                    moviesModel = jsonUtils.parseMoviesModelJson(s);
                    GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);

                    mAdapter = new FilmAdapter(getAction(), moviesModel);

                    recyclerView.setAdapter(mAdapter);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(layoutManager);


                    // textView.setText(moviesModel.getPoster_path().get(0));
                } catch (Exception e) {
                    //  textView.setText(e.getMessage());

                }


            }
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("data",moviesModel);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        moviesModel=savedInstanceState.getParcelable("data");


    }
}



