package by.it_academy.jd2.site_vote.service;
import by.it_academy.jd2.site_vote.storage.VoteStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteService {

    private final static VoteService instance = new VoteService();
    private final VoteStorage storage;

    private VoteService() {
        this.storage = VoteStorage.getInstance();
    }

    public void addVote(String artist, String[] genres, String about){
        this.storage.getAbout().add(about);

        Integer artistPosition = this.storage.getArtist().getOrDefault(artist, 0);
        this.storage.getArtist().put(artist, ++artistPosition);

        if(genres != null){
            for (String genre : genres) {
                Integer genrePosition = this.storage.getGenre().getOrDefault(genre, 0);
                this.storage.getGenre().put(genre, ++genrePosition);
            }
        }
    }

    public Map<String, Integer> getArtistResult(){
        return this.storage.getArtist();
    }

    public Map<String, Integer> getGenreResult(){
        return this.storage.getGenre();
    }

    public List<String> getAboutResult(){
        return this.storage.getAbout();
    }

    public static VoteService getInstance() {
        return instance;
    }

    public String getArtistName(String key) {
        switch (key){
            case "1":
                return "Ирина Олегрова";
            case "2":
                return "Каста";
            case "3":
                return "Луна";
            case "4":
                return "Иванушки";
            default:
                break;
        }
        return "";
    }

    public String getGenreName(String key) {
        switch (key){
            case "1":
                return "Рок";
            case "2":
                return "Поп";
            case "3":
                return "Фолк";
            case "4":
                return "Альт";
            case "5":
                return "Клкассика";
            case "6":
                return "Джаз";
            case "7":
                return "Тиктоник";
            default:
                break;
        }
        return "";
    }

    public void saveResult(Map<String, Integer> artist, Map<String, Integer> genre,
                           List<String> about, String pathFile) {
        String eol = System.getProperty("line.separator");

        try (Writer writer = new FileWriter(new File(pathFile))) {

            for (Map.Entry<String, Integer> entry : artist.entrySet()) {
                writer.append("artist")
                        .append(',')
                        .append(entry.getKey())
                        .append(',')
                        .append(entry.getValue().toString())
                        .append(eol);
            }

            for (Map.Entry<String, Integer> entry : genre.entrySet()) {
                writer.append("genre")
                        .append(',')
                        .append(entry.getKey())
                        .append(',')
                        .append(entry.getValue().toString())
                        .append(eol);
            }

            for (String string : about) {
                writer.append("about")
                        .append(',')
                        .append(string)
                        .append(eol);
            }

        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public void readData() {

        Map<String, Integer> artist = new HashMap<>();
        Map<String, Integer> genre = new HashMap<>();
        List<String> about = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(new File("c:\\jd1\\Mk-JD2-82-21\\src\\data.txt")))) {

            String nextLine = reader.readLine();

            while (nextLine != null) {
                String[] values_per_line = nextLine.split(",");
                switch (values_per_line[0]) {
                    case "artist":
                        artist.put(values_per_line[1], Integer.parseInt(values_per_line[2]));
                        break;
                    case "genre":
                        genre.put(values_per_line[1], Integer.parseInt(values_per_line[2]));
                        break;
                    case "about":
                        if (values_per_line.length == 1) {
                            about.add(null);
                        } else {
                            about.add(values_per_line[1]);
                        }
                        break;
                    default:
                        break;
                }
                nextLine = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.storage.setArtist(artist);
        this.storage.setGenre(genre);
        this.storage.setAbout(about);

    }
}
