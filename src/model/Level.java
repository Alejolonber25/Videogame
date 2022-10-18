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

    /**
     * Level()
     * @param number int
     * @param pointsForNextLevel int
     * @param quantitytreasures int
     * @param quantityEnemies int
     */
    public Level(int number, int pointsForNextLevel, int quantitytreasures, int quantityEnemies){
        this.number = number;
        this.pointsForNextLevel = pointsForNextLevel;
        treasures = new Treasure[quantitytreasures];
        enemies = new Enemy[quantityEnemies];
    }

    /**
     * getPointsForNextLevel()
     * @return pointsForNextLevel int
     */
    public int getPointsForNextLevel(){
        return pointsForNextLevel;
    }
    /**
     * getNumber()
     * @return this.number int
     */
    public int getNumber(){
        return this.number;
    }
    /**
     * getTreasures()
     * @return this.treasures Treasure[]
     */
    public Treasure[] getTreasures(){
        return this.treasures;
    }
    /**
     * getEnemies()
     * @return this.enemies Enemy[]
     */
    public Enemy[] getEnemies(){
        return this.enemies;
    }
    /**
     * getDifficulty()
     * @return this.difficulty String
     */
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