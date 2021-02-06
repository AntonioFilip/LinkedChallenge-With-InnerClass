import com.sun.security.jgss.GSSUtil;

import java.io.IOError;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<Album>();

    public static void main(String[] args) {
        Album album = new Album("The Stage", "A7X");
        album.addSong("Higher",2.03);
        album.addSong("Exist",4.21);
        album.addSong("Hail to the King",3.55);
        album.addSong("Shepard",3.2);
        albums.add(album);

        album = new Album("Indestructible","Disturbed");
        album.addSong("Stricken",3.21);
        album.addSong("Warrior",3.15);
        album.addSong("Torn",2.45);
        album.addSong("Sound of Silence",2.24);

        LinkedList<Song> playlist1 = new LinkedList<>();
        albums.get(0).addToPlaylist(2,playlist1);
        albums.get(0).addToPlaylist("Shepard",playlist1);
        albums.get(0).addToPlaylist("Test",playlist1);
        albums.get(0).addToPlaylist("Higher",playlist1);

        play(playlist1);
        play(playlist1);
    }

    private static void printAll(LinkedList<Song> playlist){
        for(Song currentSong : playlist){
            System.out.println(currentSong.toString());
        }
    }

    private static void play(LinkedList<Song> playlist){
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> playlistIterator = playlist.listIterator();
            if(playlist.size()==0){
                System.out.println("No songs");
                return;
            }else{
                System.out.println(playlistIterator.next().toString());
                printMenu();
            }
            while(!quit){
                int action = scanner.nextInt();
                scanner.nextLine();
                switch(action){
                    case 0:
                        System.out.println("Over");
                        quit = true;
                        break;
                    case 1:
                        if(!forward){
                            if(playlistIterator.hasNext()) {
                                playlistIterator.next();
                            }
                            forward = true;
                        }
                        if(playlistIterator.hasNext()) {
                            System.out.println(playlistIterator.next().toString());
                        }else{
                            System.out.println("End of list");
                            forward = false;
                        }
                        break;
                    case 2:
                        if(forward){
                            if(playlistIterator.hasPrevious()) {
                                playlistIterator.previous();
                            }
                            forward = false;
                        }
                        if(playlistIterator.hasPrevious()){
                            System.out.println(playlistIterator.previous().toString());
                        }else{
                            System.out.println("Start of playlist");
                            forward = true;
                        }
                        break;
                    case 3:
                        if(forward){
                            if(playlistIterator.hasPrevious()){
                                System.out.println("Replaying "+playlistIterator.previous().toString());
                                forward = false;
                            }else{
                                System.out.println(" Start of the list");
                            }
                        } else{
                            if(playlistIterator.hasNext()){
                                System.out.println("Replaying" + playlistIterator.next().toString());
                                forward = true;
                            } else{
                                System.out.println("End");
                            }
                        }
                        break;
                    case 4:
                        printList(playlist);
                        break;
                    case 5:
                        printMenu();
                        break;
                    case 6:
                        if(playlist.size()>0){
                            playlistIterator.remove();
                            if(playlistIterator.hasNext()){
                                System.out.println("Now playing " + playlistIterator.next().toString());
                            } else if(playlistIterator.hasPrevious()){
                                System.out.println("Now playing " + playlistIterator.previous().toString());
                            }
                        }
                        break;
                }
            }
    }

    private static void printMenu(){
        System.out.println("Actions:");
        System.out.println("0 - quit");
        System.out.println("1 - play next song");
        System.out.println("2 - play previous");
        System.out.println("3 - replay");
        System.out.println("4 - list songs");
        System.out.println("5 - print actions");
        System.out.println("6 - remove song");

    }

    private static void printList(LinkedList<Song> playlist){
        ListIterator<Song> listIterator = playlist.listIterator();
        System.out.println("======================");
        while(listIterator.hasNext()){
            System.out.println(listIterator.next().toString());
        }
        System.out.println("======================");
    }
}
