package model;
/**
 * Enemy
 */
public class Enemy {
    
    private String name;
    private TypeEnemy type;
    private int scoreToIncrease;
    private int scoreToDecrease;
    private int x;
    private int y;

    /**
     * Enemy()
     * @param name String
     * @param type String
     * @param scoreToIncrease int
     * @param scoreToDecrease int
     * @param x int
     * @param y int
     */
    public Enemy(String name, TypeEnemy type, int scoreToIncrease, int scoreToDecrease, int x, int y){
        this.name = name;
        this.type = type;
        this.scoreToIncrease = scoreToIncrease;
        this.scoreToDecrease = scoreToDecrease;
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
     * @return this.name String
     */
    public String getName(){
        return this.name;
    }
    /**
     * getType()
     * @return this.type String
     */
    public String getType(){
        return this.type.toString();
    }

}