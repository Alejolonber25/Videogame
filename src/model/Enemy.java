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

    public Enemy(String name, TypeEnemy type, int scoreToIncrease, int scoreToDecrease, int x, int y){
        this.name = name;
        this.type = type;
        this.scoreToIncrease = scoreToIncrease;
        this.scoreToDecrease = scoreToDecrease;
        this.x = x;
        this.y = y;
    }

    public int getScoreToIncrease(){
        return scoreToIncrease;
    }

    public String getName(){
        return this.name;
    }
    public String getType(){
        return this.type.toString();
    }

}