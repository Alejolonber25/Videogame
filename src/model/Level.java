package model;

/**
 * Level
 */
public class Level {
    private int number;
    private int pointsForNextLevel;
    private Treasure[] treasures;
    private Enemy[] enemies;
    private String difficulty;

    public Level(int number, int pointsForNextLevel, int quantitytreasures, int quantityEnemies){
        this.number = number;
        this.pointsForNextLevel = pointsForNextLevel;
        treasures = new Treasure[quantitytreasures];
        enemies = new Enemy[quantityEnemies];
    }

    public int getPointsForNextLevel(){
        return pointsForNextLevel;
    }

    public int getNumber(){
        return this.number;
    }

    public Treasure[] getTreasures(){
        return this.treasures;
    }

    public Enemy[] getEnemies(){
        return this.enemies;
    }

    public String getDifficulty(){
        // Update Difficulty
        int scoreToIncreaseTreasures = 0;
        int scoreToIncreaseEnemies = 0;
        for (Treasure treasure : treasures) {
            if (treasure != null) {
                scoreToIncreaseTreasures += treasure.getScoreToIncrease();
            }
        }

        for (Enemy enemy : enemies) {
            if (enemy != null) {
                scoreToIncreaseEnemies += enemy.getScoreToIncrease();
            }
        }

        if(scoreToIncreaseTreasures > scoreToIncreaseEnemies){
            this.difficulty = "Easy";
        }
        else if(scoreToIncreaseTreasures == scoreToIncreaseEnemies){
            this.difficulty = "Medium";
        }
        else{
            this.difficulty = "Hard";
        }

        // Get Difficulty
        return this.difficulty;
    }
}