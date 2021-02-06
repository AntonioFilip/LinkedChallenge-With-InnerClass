import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Album {
    private String albumName;
    private String artist;
    private SongList songs;

    public Album(String albumName,String artist) {
        this.albumName = albumName;
        this.artist = artist;
        this.songs = new SongList();
    }

    public String getAlbumName() {
        return albumName;
    }


    public void addSong(String title,double duration){
        this.songs.addSong(new Song(title,duration));
    }
    /*
        private Song findSong(String title){
            for(Song checkedSong: this.songs){
                if(checkedSong.getTitle().equals(title)) {
                    return checkedSong;
                }
            }
            return null;
        }
    */
    public boolean addToPlaylist(int trackNumber, LinkedList<Song> playlist){
        Song currentSong = this.songs.findSong(trackNumber);
        if(currentSong!=null){
            playlist.add(currentSong);
            return true;
        }
        System.out.println("Does not have the track" + trackNumber);
        return false;
    }

    public boolean addToPlaylist(String title, LinkedList<Song> playlist){
        Song checkedSong = this.songs.findSong(title);
        if(checkedSong!=null)
        {
            playlist.add(checkedSong);
            return true;
        }
            System.out.println("Song is not in album");
            return false;

    }

    private class SongList{
        private ArrayList<Song> songs;

        public SongList() {
            this.songs = new ArrayList<Song>();
        }

        public boolean addSong(Song song){
            if(songs.contains(song)){
                return false;
            }
            this.songs.add(song);
            return true;
        }

        public Song findSong(String title){
            for(Song currentSong : this.songs){
                if(currentSong.getTitle().equals(title)){
                    return currentSong;
                }
            }
            return null;
        }

        public Song findSong(int trackNumber){
            int index = trackNumber - 1;
            if(index >= 0 && index<= this.songs.size()){
                return songs.get(index);
            }else{
                return null;
            }
        }
    }
}
