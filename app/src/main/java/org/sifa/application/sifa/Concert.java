package org.sifa.application.sifa;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shashank on 4/15/16.
 * @author Shashank
 */

public class Concert
        implements Serializable {

    private HashMap<String, String> mainArtists = new HashMap<>(); //Value is instrument(vocal, violin, veena) Key is Artist Name

    private HashMap<String, String> accompanists = new HashMap<>(); //Value is instrument(vocal, violin, veena) Key is Artist Name

    private String date;

    private String time;

    private String venue;

    private String description;

    private ArrayList<String> pictureSource = new ArrayList<>();

    private String ticketLink;

    private String ticketHeader;


    public Concert() {

    }

    public Concert(String mainArtist, String date) {
        mainArtists.put("vocal", mainArtist);
        this.date = date;
    }

    public HashMap<String, String> getAccompanistsInfo() {
        return accompanists;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<String, String> getMainArtistsInfo() {
        return mainArtists;
    }

    public ArrayList<String> getPictureSource() {
        return pictureSource;
    }

    public String getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public String[] getMainArtists() {
        return mainArtists.keySet().toArray(new String[mainArtists.size()]);
    }

    public String[] getAccompanists() {
        return accompanists.keySet().toArray(new String[accompanists.size()]);
    }


    public void addAccompanist(String name, String instrument) {
        accompanists.put(name, instrument);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addMainArtist(String mainArtistName, String instrument) {
        mainArtists.put(mainArtistName, instrument);
    }

    public void addPictureSource(String pictureSource) {
        this.pictureSource.add(pictureSource);
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setTicketLink(String ticketLink) {
        this.ticketLink = ticketLink;
    }

    public void setTicketHeader(String ticketHeader) {
        this.ticketHeader = ticketHeader;
    }

    public String getTicketHeader() {
        return ticketHeader;
    }

    public String getTicketLink() {
        return ticketLink;
    }


    @Override
    public String toString() {
        return "Concert{" +
                "accompanists=" + accompanists +
                ", mainArtists=" + mainArtists +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", venue='" + venue + '\'' +
                ", description='" + description + '\'' +
                ", pictureSource=" + pictureSource +
                ", ticketLink='" + ticketLink + '\'' +
                ", ticketHeader='" + ticketHeader + '\'' +
                '}';
    }

}