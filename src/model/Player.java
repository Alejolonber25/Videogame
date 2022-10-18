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
    
    public Player(String nickname, String name, Level level) {
        this.nickname = nickname;
        this.name = name;
        this.level = level;
    }


    public void setScore(int score){
        this.score = score;
    }

    public void modifyLevel(Level level){
        this.level = level;
    }

    public String getNickname(){
        return this.nickname;
    }

    public Level getLevel(){
        return this.level;
    }

    public int getScore(){
        return this.score;
    }
}