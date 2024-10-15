package at.fhooe.sail.vis.jaxb.parser.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HighscoreList {

    private List<Highscore> highscores;

    public HighscoreList(String jsonData) {
        highscores = new ArrayList<>(); // create empty list
        JSONObject obj = new JSONObject(jsonData); // create JSON object from string
        JSONArray arr = obj.getJSONArray("Highscore"); // get array from JSON object

        for (int i = 0; i < arr.length(); i++) { // iterate over array
            JSONObject highscore = arr.getJSONObject(i); // get JSON object from array
            String name = highscore.getString("Name"); // get name from JSON object
            int score = highscore.getInt("Score"); // get score from JSON object
            int time = highscore.getInt("Time"); // get time from JSON object
            highscores.add(new Highscore(name, score, time)); // add new highscore to list
        }
    }

    public List<Highscore> getHighscores() {
        return highscores;
    }

    @Override
    public String toString() {
        return  "{\n highscores : \n" + highscores + "\n}";
    }
}
