package app.mov.movieteca.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.InputStream;
import java.util.List;

import app.mov.movieteca.R;
import app.mov.movieteca.fragments.FullDetailCast;
import app.mov.movieteca.models.movies.MovieCast;
import app.mov.movieteca.utils.Constants;
import app.mov.movieteca.utils.Helper;

/**
 * Created by Catalin on 12/21/2017.
 */

public class MovieFullDetailCastsAdapter extends RecyclerView.Adapter<MovieFullDetailCastsAdapter.MovieFullDetailCastViewHolder> {

    public Context context;
    public List<MovieCast> castList;

    public MovieFullDetailCastsAdapter(Context context, List<MovieCast> castList){
        this.context = context;
        this.castList = castList;
    }

    @Override
    public MovieFullDetailCastsAdapter.MovieFullDetailCastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item_cast, parent, false);
        return new MovieFullDetailCastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieFullDetailCastsAdapter.MovieFullDetailCastViewHolder holder, int position) {
        Glide.with(context.getApplicationContext()).load(Constants.IMAGE_LOADING_BASE_URL_342.concat(castList.get(position).getProfile_path()))
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.castImage);
        if (castList.get(position).getName() != null){
            holder.castName.setText(castList.get(position).getName());
        }
        if (castList.get(position).getCharacter() != null){
            holder.castMovieName.setText(castList.get(position).getCharacter());
        }
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public class MovieFullDetailCastViewHolder extends RecyclerView.ViewHolder{

        public TextView castName;
        public TextView castMovieName;
        public ImageView castImage;
        public CardView castCard;

        public MovieFullDetailCastViewHolder(View itemView) {
            super(itemView);
            castName = (TextView)itemView.findViewById(R.id.cast_name);
            castMovieName = (TextView)itemView.findViewById(R.id.cast_movie_name);
            castImage = (ImageView)itemView.findViewById(R.id.image_view_cast);
            castCard = (CardView)itemView.findViewById(R.id.cast_card);
            castCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("cast", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(Constants.cast_id, castList.get(getAdapterPosition()).getId());
                    editor.commit();
                    Helper.changeFragment(context, new FullDetailCast());
                }
            });
        }
    }

}