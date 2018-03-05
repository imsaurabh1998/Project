package saurabh.android.firebasedatabase;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SAURABH on 8/25/2017.
 */

public class ArtistTrackList extends ArrayAdapter<Track> {


    Activity context;
    List<Track> tracks;

    public ArtistTrackList(Activity context,List<Track> tracks){
        super(context,R.layout.track_layout,tracks);
        this.context=context;
        this.tracks=tracks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater=context.getLayoutInflater();

        View view=inflater.inflate(R.layout.track_layout,null,true);

        TextView text=(TextView)view.findViewById(R.id.textviewTrackArtist);
        TextView text1=(TextView)view.findViewById(R.id.textViewTrackSeek);

       Track track=tracks.get(position);

        text.setText(track.getTrack_name());
        text1.setText(String.valueOf(track.getTrack_seekbar()));

        return view;

    }
}
