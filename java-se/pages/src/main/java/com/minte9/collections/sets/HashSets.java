/**
 * HashSet stores UNIQUE elements and offers no sorting.
 * 
 * To check for duplicates HashSet uses two methods inherited from Object:
 *      - hashCode() for quick access (performance)
 *      - equals() for actual equality check
 * 
 * The default implementation from Object:
 *      - equals() checks if two references are the SAME OBJECT in memory
 *      - hashCode() generates a number based on memory location
 * 
 * This means:
 *      new Song("Imagine", "John Lennon")
 *      new Song("Imagine", "John Lennon")
 * are considered DIFFERENT unless we oberride Object's methods.
 * 
 * Overriding equals() and hashCode() is optional.
 * Java allows you to rely on the default behavior.
 */

package com.minte9.collections.sets;

import java.util.HashSet;
import java.util.Objects;

public class HashSets {            
    public static void main(String[] args) {     

        HashSet<String> myLHashSet = new HashSet<>();
        myLHashSet.add("AAA");
        myLHashSet.add("AAA"); // duplicate
        myLHashSet.add("BBB");
        myLHashSet.add("CCC");

        System.out.println(myLHashSet);  // [AAA, CCC, BBB]

        HashSet<Song> songs = new HashSet<>();
        songs.add(new Song("Imagine", "John Lennon"));
        songs.add(new Song("Imagine", "John Lennon")); // duplicate
        songs.add(new Song("Africa", "Toto"));
        songs.add(new Song("Africa", "Weezer"));  // not a duplicate

        System.out.println(songs); 
            // Imagine (John Lennon), Africa (Weezer), Africa (Toto)

    }
}

class Song {
    private String title;
    private String artist;

    public Song (String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return title + " (" + artist + ")";
    }

    @Override
    public boolean equals(Object o) {
        Song other = (Song) o;
        return title.equals(other.title) && artist.equals(other.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist);
    }
}
