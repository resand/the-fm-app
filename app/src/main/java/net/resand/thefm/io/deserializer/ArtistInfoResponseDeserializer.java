package net.resand.thefm.io.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.resand.thefm.domain.Artist;
import net.resand.thefm.io.model.JsonKeys;

import org.json.JSONArray;

import java.lang.reflect.Type;

/**  * Created by resand */
public class ArtistInfoResponseDeserializer implements JsonDeserializer<Artist> {
    @Override
    public Artist deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();

        JsonObject artistData = json.getAsJsonObject().getAsJsonObject(JsonKeys.ARTISTS_ARRAY);
        JsonArray artistImages = artistData.getAsJsonArray(JsonKeys.ARTIST_IMAGES);
        JsonObject artistStats = artistData.getAsJsonObject(JsonKeys.ARTIST_STATS);

        Artist artist = Artist.buildArtistFromJson(artistData);
        artist.extractUrlsFromImagesArray(artistImages);
        artist.extractStatsFromJson(artistStats);

        return artist;
    }
}
