package model;
/**
 * Treasure
 */
public class Treasure {

    private String name;
    private String url;
    private int scoreToIncrease;
    private int x;
    private int y;

    public Treasure(String name, String url, int scoreToIncrease, int x, int y){
        this.name = name;
        this.url = url;
        this.scoreToIncrease = scoreToIncrease;
        this.x = x;
        this.y = y;
    }

    public int getScoreToIncrease(){
        return scoreToIncrease;
    }

    public String getName(){
        return this.name;
    }

}