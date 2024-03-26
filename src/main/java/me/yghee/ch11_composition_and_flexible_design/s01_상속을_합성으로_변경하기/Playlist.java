package me.yghee.ch11_composition_and_flexible_design.s01_상속을_합성으로_변경하기;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private List<Song> tracks = new ArrayList<>();
    public List<Song> songs = new ArrayList<>();
    public void append ( Song song ) {
        songs.add( song );
    }

    public <E> List getTracks () {
        return tracks;
    }
}
