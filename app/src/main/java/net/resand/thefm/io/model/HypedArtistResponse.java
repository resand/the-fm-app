package net.resand.thefm.io.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.resand.thefm.domain.Artist;

import java.util.ArrayList;

/**  * Created by resand */
public class HypedArtistResponse {

    @SerializedName(JsonKeys.ARTISTS_RESPONSE)
    private MainResponse mainResponse;

    public ArrayList<Artist> getArtists(){
        return  mainResponse.artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        mainResponse.artists = artists;
    }

    private class MainResponse {

        private ArrayList<Artist> artists;

    }
}
