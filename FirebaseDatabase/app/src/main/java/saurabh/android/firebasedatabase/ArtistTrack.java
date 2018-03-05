package saurabh.android.firebasedatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAURABH on 8/25/2017.
 */

public class ArtistTrack extends AppCompatActivity {

    TextView textview1;
    EditText edittext1;
    SeekBar seekbar1;
    Button button1;
    ListView listview;

    DatabaseReference databaseTrack;

    List<Track> trackList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_tracks);

        textview1=(TextView)findViewById(R.id.textviewArtist1);
        edittext1=(EditText)findViewById(R.id.edittextTrackname);
        seekbar1=(SeekBar)findViewById(R.id.seekbarArtist);
       button1=(Button)findViewById(R.id.buttonAddArtist);

        listview=(ListView)findViewById(R.id.listviewTrack);
        trackList=new ArrayList<>();

        Intent intent=getIntent();

        String id=intent.getStringExtra(MainActivity.ARTIST_ID);
        String name=intent.getStringExtra(MainActivity.ARTIST_NAME);

        textview1.setText(name);

        databaseTrack= FirebaseDatabase.getInstance().getReference("tracks").child(id);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTrack();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseTrack.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                        trackList.clear();
                for(DataSnapshot shot:dataSnapshot.getChildren()){
                    Track track=shot.getValue(Track.class);
                    trackList.add(track);
                }

                ArtistTrackList adapter=new ArtistTrackList(ArtistTrack.this,trackList);
                listview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void saveTrack(){
       String Edittext= edittext1.getText().toString();
        int SeekBar=seekbar1.getProgress();

        if(!TextUtils.isEmpty(Edittext)){
            String key=databaseTrack.push().getKey();

            Track track= new Track(key,Edittext,SeekBar);
            databaseTrack.child(key).setValue(track);

            Toast.makeText(this, "Track NAME,SEEKBAR Added", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Enter Some Data", Toast.LENGTH_SHORT).show();

    }
}
