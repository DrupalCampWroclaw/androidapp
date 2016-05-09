package pl.drupalcampwroclaw.drupalcamp.sessions;

import java.io.Serializable;


public class Session implements Serializable {

    private String name;
    private String speakers;
    private String time;
    private String room;
    private String language;

    public Session(String name, String speakers, String time, String room, String language) {
        super();
        this.name = name;
        this.speakers = speakers;
        this.time = time;
        this.room = room;
        this.language = language;
    }

    public String getName() {
        return this.name;
    }
    public String getSpeakers() {
        return this.speakers;
    }
    public String getTime() {
        return this.time;
    }
    public String getRoom() {
        return this.room;
    }
    public String getLanguage() {
        return this.language;
    }

}
