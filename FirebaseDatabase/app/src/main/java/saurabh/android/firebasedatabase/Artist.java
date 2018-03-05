package saurabh.android.firebasedatabase;

/**
 * Created by SAURABH on 8/22/2017.
 */

public class Artist {

    String artist_id;
    String artist_genre;
    String artist_name;

    public Artist(){

    }

    public Artist(String artist_id,String artist_genre,String artist_name){
        this.artist_id=artist_id;
        this.artist_genre=artist_genre;
        this.artist_name=artist_name;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public String getArtist_genre() {
        return artist_genre;
    }

    public String getArtist_name() {
        return artist_name;
    }
}
