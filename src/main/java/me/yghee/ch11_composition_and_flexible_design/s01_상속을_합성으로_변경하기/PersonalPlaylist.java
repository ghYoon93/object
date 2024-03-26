package me.yghee.ch11_composition_and_flexible_design.s01_상속을_합성으로_변경하기;

public class PersonalPlaylist {
    private Playlist playlist = new Playlist();

    public void append(Song song) {
        playlist.append(song);
    }

    public void remove(Song song) {
        playlist.getTracks().remove(song);
        playlist.getSingers().remove(song.getSinger());
    }
}
