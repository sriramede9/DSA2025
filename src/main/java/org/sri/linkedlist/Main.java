package org.sri.linkedlist;

class Song {
    String title;
    String artist;
    Song next;
    Song prev;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }
}

class Playlist {
    private Song head;
    private Song tail;

    public void addSong(String title, String artist) {
        Song newSong = new Song(title, artist);
        if (head == null) {
            head = tail = newSong;
        } else {
            tail.next = newSong;
            newSong.prev = tail;
            tail = newSong;
        }
    }

    public boolean removeSong(String title) {
        Song current = head;
        while (current != null) {
            if (current.title.equals(title)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next; // Removing head
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev; // Removing tail
                }
                return true;
            }
            current = current.next;
        }
        return false; // Song not found
    }


    public void displayPlaylist() {
        Song current = head;
        while (current != null) {
            System.out.println(current.title + " by " + current.artist);
            current = current.next;
        }
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        playlist.addSong("Imagine", "John Lennon");
        playlist.addSong("Bohemian Rhapsody", "Queen");
        playlist.addSong("ID", "Thunder");

        // Display current playlist
        playlist.displayPlaylist();
        playlist.removeSong("Bohemian Rhapsody");
        playlist.displayPlaylist();

        // Remove a specific song (you would need to keep track of song nodes)
    }
}