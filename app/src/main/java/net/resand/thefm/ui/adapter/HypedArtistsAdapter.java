package net.resand.thefm.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.resand.thefm.R;
import net.resand.thefm.domain.Artist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**  * Created by resand */
public class HypedArtistsAdapter extends RecyclerView.Adapter<HypedArtistsAdapter.HypedArtistHolder> {

    ArrayList<Artist> artists;
    Context context;

    public HypedArtistsAdapter(Context context) {
        this.context = context;
        this.artists = new ArrayList<>();
    }

    @Override
    public HypedArtistHolder onCreateViewHolder(ViewGroup container, int position) {
        View artistView = LayoutInflater.from(context)
                .inflate(R.layout.item_hyped_artists, container, false);

        return new HypedArtistHolder(artistView);
    }

    @Override
    public void onBindViewHolder(HypedArtistHolder hypedArtistHolder, int position) {
        Artist currentArtist = artists.get(position);

        hypedArtistHolder.setName(currentArtist.getName());

        if(currentArtist.getUrlMediumImage() != null)
            hypedArtistHolder.setImage(currentArtist.getUrlMediumImage());
        else
            hypedArtistHolder.setDefaultImage();
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public void addAll(ArrayList<Artist> artists) {
        if (artists == null)
            throw new NullPointerException("The items cannot be null");

        this.artists.addAll(artists);
        notifyDataSetChanged();
    }

    public void addItem(Artist artist){
        artists.add(artist);

        notifyItemInserted(getItemCount()-1);
    }

    public void replace(ArrayList<Artist> artists){
        this.artists = artists;
        notifyDataSetChanged();
    }

    public class HypedArtistHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView name;

        public HypedArtistHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.artist_img);
            name = (TextView) itemView.findViewById(R.id.artist_name);
        }

        public void setName(String name){
           this.name.setText(name);
        }

        public void setDefaultImage(){
            Picasso.with(context)
                    .load(R.drawable.artist_placeholder)
                    .into(image);
        }

        public void setImage(String urlImage){
            Picasso.with(context)
                    .load(urlImage)
                    .placeholder(R.drawable.artist_placeholder)
                    .into(image);
        }

    }
}
