package net.resand.thefm.io.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.resand.thefm.domain.Artist;
import net.resand.thefm.io.model.HypedArtistResponse;
import net.resand.thefm.io.model.JsonKeys;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**  * Created by resand */
public class HypedArtistsResponseDeserializer implements JsonDeserializer<HypedArtistResponse> {

    @Override
    public HypedArtistResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        HypedArtistResponse response = gson.fromJson(json, HypedArtistResponse.class);

        //The artists array will be parsed manually due nested elements
        JsonObject artistsResponseData = json.getAsJsonObject().getAsJsonObject(JsonKeys.ARTISTS_RESPONSE);

        JsonArray artistsArray = artistsResponseData.getAsJsonArray(JsonKeys.ARTISTS_ARRAY);
        response.setArtists(extractArtistsFromJsonArray(artistsArray));

        return response;
    }

    private ArrayList<Artist> extractArtistsFromJsonArray(JsonArray artistsArray) {
        ArrayList<Artist> artists = new ArrayList<>();

        for (int i = 0; i < artistsArray.size(); i++) {
            JsonObject artistData = artistsArray.get(i).getAsJsonObject();

            Artist currentArtist = Artist.buildArtistFromJson(artistData)
                                    .extractUrlsFromImagesArray(artistData.getAsJsonArray(JsonKeys.ARTIST_IMAGES));

            artists.add(currentArtist);
        }

        return artists;
    }

}
