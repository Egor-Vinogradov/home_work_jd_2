package by.it_academy.jd2.site_vote.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteStorage {
    private final static VoteStorage instance = new VoteStorage();

    public void setArtist(Map<String, Integer> artist) {
        this.artist = artist;
    }

    public void setGenre(Map<String, Integer> genre) {
        this.genre = genre;
    }

    public void setAbout(List<String> about) {
        this.about = about;
    }

    private Map<String, Integer> artist;
    private Map<String, Integer> genre;
    private List<String> about;

    private VoteStorage() {
        this.artist = new HashMap<>();
        this.genre = new HashMap<>();
        this.about = new ArrayList<>();
    }

    public Map<String, Integer> getArtist() {
        return artist;
    }

    public Map<String, Integer> getGenre() {
        return genre;
    }

    public List<String> getAbout() {
        return about;
    }

    public static VoteStorage getInstance() {
        return instance;
    }
}
