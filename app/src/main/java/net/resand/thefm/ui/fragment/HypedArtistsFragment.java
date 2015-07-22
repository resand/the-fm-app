package net.resand.thefm.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.resand.thefm.BuildConfig;
import net.resand.thefm.R;
import net.resand.thefm.io.LastFmApiAdapter;
import net.resand.thefm.io.model.HypedArtistResponse;
import net.resand.thefm.ui.ItemOffsetDecoration;
import net.resand.thefm.ui.adapter.HypedArtistsAdapter;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.android.schedulers.AndroidSchedulers;


/**  * Created by resand */

public class HypedArtistsFragment extends Fragment implements Callback<HypedArtistResponse> {

    public static final int COLUMNS = 2;

    private RecyclerView artistList;
    private HypedArtistsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_hyped_artists, container, false);

        artistList = (RecyclerView) root.findViewById(R.id.artist_list);
        adapter = new HypedArtistsAdapter(getActivity());

        setupList();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        requestHypedArtists();
    }

    private void requestHypedArtists() {
        LastFmApiAdapter.getHypedArtist()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(hypedArtistResponse -> {
                    adapter.addAll(hypedArtistResponse.getArtists());
                });
    }



    private void setupList() {
        artistList.setLayoutManager(new GridLayoutManager(getActivity(), COLUMNS));
        artistList.setAdapter(adapter);
        artistList.addItemDecoration(new ItemOffsetDecoration(getActivity(), R.integer.offset_grid));
    }

    @Override
    public void success(HypedArtistResponse hypedArtistResponse, Response response) {
        adapter.addAll(hypedArtistResponse.getArtists());
    }

    @Override
    public void failure(RetrofitError error) {
        if (error.getKind() == RetrofitError.Kind.NETWORK) {
            Toast.makeText(getActivity(), R.string.network_error, Toast.LENGTH_LONG).show();
        }
    }
}
