/**
 * ArrayList doesn't have a sort method in its own.
 * Insteed, we use Collections.sort(), which works on any List implementation.
 * 
 * By default, Collections.sort() uses natural ordering:
 * - Strings: alphabetical order
 * - Numbers: numeric order
 * 
 * Collections.sort() modifies the original list (in-place).
 * 
 * If your list contains custom object, 
 * they must implement Comparable OR use a Comparator.
 */

package com.minte9.collections.lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ArraysSorting {
    public static void main(String[] args) {

        List<String> myList = new ArrayList<>();
        myList.add("AB");
        myList.add("BC");
        myList.add("AD");

        // Sort the list alphabetically (ascending)
        Collections.sort(myList);
        System.out.println(myList); // [AB, AD, BC]

        // Sort in reverse order
        Collections.sort(myList, Collections.reverseOrder());
        System.out.println(myList);  // [BC, AD, AB]

        // Equivalent modern call (Java 8+)
        myList.sort(String::compareTo);
        System.out.println(myList);  // [AB, AD, BC]

        // Sorting objects (the compiler doesn't know what t sort)
        List<Song> songList = new ArrayList<>();
        songList.add(new Song("A", "y"));
        songList.add(new Song("B", "z"));
        songList.add(new Song("C", "w"));

        Collections.sort(songList);
        System.out.println(songList);  // [A:y, B:z, C:w]
    }
}

class Song implements Comparable<Song> {
    String title;
    String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public int compareTo(Song song) {
        return title.compareTo(song.title);
    }

    @Override public String toString() {
        return title + ":" + artist;
    }
}