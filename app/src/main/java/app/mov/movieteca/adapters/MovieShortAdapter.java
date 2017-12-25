package app.mov.movieteca.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import app.mov.movieteca.R;
import app.mov.movieteca.database.Handler;
import app.mov.movieteca.fragments.FullDetailMovie;
import app.mov.movieteca.models.movies.MovieShort;
import app.mov.movieteca.utils.Constants;
import app.mov.movieteca.utils.GenresUtil;
import app.mov.movieteca.utils.Helper;

/**
 * Created by Catalin on 12/21/2017.
 */

public class MovieShortAdapter extends RecyclerView.Adapter<MovieShortAdapter.MovieViewHolder>{

    private Context context;
    private List<MovieShort> movies;

    public MovieShortAdapter(Context context, List<MovieShort> movies){
        this.context = context;
        this.movies = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item_card, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        Glide.with(context.getApplicationContext()).load(Constants.IMAGE_LOADING_BASE_URL_342.concat(movies.get(position).getBackdrop_path()))
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.movieImageView);
        if (movies.get(position).getOriginal_title() != null)
            holder.movieTitleTextView.setText(movies.get(position).getOriginal_title());
        else {
            holder.movieTitleTextView.setText("");
        }
        if (movies.get(position).getVote_average() != null && movies.get(position).getVote_average() > 0) {
            holder.movieRatingTextView.setVisibility(View.VISIBLE);
            holder.movieRatingTextView.setText(String.format("%.1f", movies.get(position).getVote_average()) + Constants.RATING_SYMBOL);
        } else {
            holder.movieRatingTextView.setVisibility(View.GONE);
        }
        if (movies.get(position).getRelease_date() != null){
            holder.releaseDate.setText("Release date: ".concat(Helper.formatDate(movies.get(position).getRelease_date())));
        }
        else {
            holder.releaseDate.setText("");
        }
        setGenres(holder, movies.get(position));
        if (Handler.isFav(context, "movie", movies.get(position).getId())){
            holder.favButton.setImageResource(R.drawable.ic_favorite_black_18dp);
            holder.favButton.setTag("favorit");
        }
        else {
            holder.favButton.setImageResource(R.drawable.ic_favorite_border_black_18dp);
            holder.favButton.setTag("nefavorit");
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    private void setGenres(MovieViewHolder holder, MovieShort movie) {
        String genreString = "";
        for (int i = 0; i < movie.getGenre_ids().size(); i++) {
            if (movie.getGenre_ids().get(i) == null) continue;
            if (GenresUtil.getGenreName(movie.getGenre_ids().get(i)) == null) continue;
            genreString += GenresUtil.getGenreName(movie.getGenre_ids().get(i)) + ", ";
        }
        if (!genreString.isEmpty())
            holder.movieGenreTextView.setText(genreString.substring(0, genreString.length() - 2));
        else
            holder.movieGenreTextView.setText("");
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        public CardView movieCard;
        public TextView movieTitleTextView;
        public TextView movieRatingTextView;
        public TextView movieGenreTextView;
        public ImageView movieImageView;
        public ImageButton favButton;
        public TextView releaseDate;

        public MovieViewHolder(final View itemView) {
            super(itemView);
            movieCard = (CardView) itemView.findViewById(R.id.card_view_show_card);
            movieImageView = (ImageView) itemView.findViewById(R.id.image_view);
            movieTitleTextView = (TextView) itemView.findViewById(R.id.text_view_title_show_card);
            movieRatingTextView = (TextView) itemView.findViewById(R.id.text_view_rating_show_card);
            movieGenreTextView = (TextView) itemView.findViewById(R.id.text_view_genre_show_card);
            favButton = (ImageButton) itemView.findViewById(R.id.image_button_fav);
            releaseDate = (TextView) itemView.findViewById(R.id.text_view_release_date);
            movieTitleTextView.setSelected(true);
            favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                    if (favButton.getTag().equals("favorit")){
                        Handler.removeFromFavorites(context, "movie", movies.get(getAdapterPosition()).getId());
                        Helper.notifyUser("remove", "fav", movies.get(getAdapterPosition()).getOriginal_title(), context);
                        favButton.setTag("nefavorit");
                        favButton.setImageResource(R.drawable.ic_favorite_border_black_18dp);
                    }
                    else if (favButton.getTag().equals("nefavorit")){
                        Handler.addToFavorites(context, "movie", movies.get(getAdapterPosition()).getId(),
                                movies.get(getAdapterPosition()).getOriginal_title(), movies.get(getAdapterPosition()).getBackdrop_path());
                        Helper.notifyUser("add", "fav", movies.get(getAdapterPosition()).getOriginal_title(), context);
                        favButton.setTag("favorit");
                        favButton.setImageResource(R.drawable.ic_favorite_black_18dp);
                    }
                }
            });

            movieCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("movies", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(Constants.movie_id, movies.get(getAdapterPosition()).getId());
                    editor.commit();
                    Helper.changeFragment(context, new FullDetailMovie());
                }
            });
        }
    }
}
