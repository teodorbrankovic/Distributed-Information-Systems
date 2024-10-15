package at.fhooe.sail.vis.jaxb.parser.json;

import java.util.List;

public class JSON_Parser {

    public static void main(String[] args) {

        String jsonData = "{\n" +
                "\"Highscore\" : [\n" +
                "{\"Name\":\"TBD Name 01\", \"Score\":10, \"Time\":10},\n" +
                "{\"Name\":\"TBD Name 02\", \"Score\":10, \"Time\":11},\n" +
                "{\"Name\":\"TBD Name 03\", \"Score\":9, \"Time\":9}\n" +
                "]\n" +
                "}";

        HighscoreList highscoreList = new HighscoreList(jsonData);
        System.out.println();
        System.out.println("Task 3.1.c");
        System.out.println();
        System.out.println("----parser/JSON----");
        System.out.println(highscoreList);
    }
}