package model;

import java.beans.ConstructorProperties;
import java.lang.reflect.Constructor;

import javax.management.ConstructorParameters;

/**
 * Player
 */
public class Player {

    private String nickname;
    private String name;
    private int score = 10;
    private int lives = 5;
    private Level level;
    
    /**
     * Player()
     * @param nickname String
     * @param name String
     * @param level Level
     */
    public Player(String nickname, String name, Level level) {
        this.nickname = nickname;
        this.name = name;
        this.level = level;
    }

    /**
     * setScore()
     * @param score int
     */
    public void setScore(int score){
        this.score = score;
    }
    /**
     * modifyLevel()
     * @param level Level
     */
    public void modifyLevel(Level level){
        this.level = level;
    }
    /**
     * getNickname()
     * @return this.nickname String
     */
    public String getNickname(){
        return this.nickname;
    }
    /**
     * getLevel()
     * @return this.level Level
     */
    public Level getLevel(){
        return this.level;
    }
    /**
     * getScore()
     * @return this.score int
     */
    public int getScore(){
        return this.score;
    }
}