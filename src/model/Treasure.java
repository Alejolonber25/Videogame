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
    
    /**
     * Treasure()
     * @param name String
     * @param url String
     * @param scoreToIncrease int
     * @param x int
     * @param y int
     */
    public Treasure(String name, String url, int scoreToIncrease, int x, int y){
        this.name = name;
        this.url = url;
        this.scoreToIncrease = scoreToIncrease;
        this.x = x;
        this.y = y;
    }
    /**
     * getScoreToIncrease()
     * @return scoreToIncrease int
     */
    public int getScoreToIncrease(){
        return scoreToIncrease;
    }
    /**
     * getName()
     * @return this.name  String
     */
    public String getName(){
        return this.name;
    }

}