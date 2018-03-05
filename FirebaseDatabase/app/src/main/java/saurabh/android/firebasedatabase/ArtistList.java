package saurabh.android.firebasedatabase;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAURABH on 8/23/2017.
 */

public class ArtistList extends ArrayAdapter<Artist> {


    Activity context;
    List<Artist> listArtist;

   public  ArtistList(Activity context,List<Artist> listArtist) {
       super(context,R.layout.list_layout,listArtist);
       this.context=context;
       this.listArtist=listArtist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater= context.getLayoutInflater();

        View listView=inflater.inflate(R.layout.list_layout,null,true);

        TextView artist=(TextView) listView.findViewById(R.id.textviewArtist);
        TextView genre=(TextView)listView.findViewById(R.id.textviewGenre);

        Artist artist1=listArtist.get(position);

        artist.setText(artist1.getArtist_name());
        genre.setText(artist1.getArtist_genre());

        return listView;
    }
}
