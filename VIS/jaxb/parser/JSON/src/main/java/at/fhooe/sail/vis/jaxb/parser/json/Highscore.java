package at.fhooe.sail.vis.jaxb.parser.json;

public class Highscore {
    String name;
    int score;
    int time;


    public Highscore(String name, int score, int time) {
        this.name = name;
        this.score = score;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Highscore: " +
                "name = " + name +
                ", score = " + score +
                ", time = " + time + "\n";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

}
