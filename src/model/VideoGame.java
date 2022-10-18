package model;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.Random;
/**
 * VideoGame
 */
public class VideoGame {
    public Random random = new Random();
    public int widthScreen;
    public int heightScreen;
    private Player[] players = new Player[20];
    private Level[] levels = new Level[10];

    private String[] namesTreasures = new String[50];

    

    /**
     * VideoGame()
     */
    public VideoGame(){

        levels[0] = new Level(1,5,5,3);
        levels[1] = new Level(2,50,5,3);
        levels[2] = new Level(3,70,5,3);
        levels[3] = new Level(4,100,5,3);
        levels[4] = new Level(5,120,5,3);
        levels[5] = new Level(6,150,5,4);
        levels[6] = new Level(7,170,5,3);
        levels[7] = new Level(8,200,5,1);
        levels[8] = new Level(9,220,5,1);
        levels[9] = new Level(10,250,5,1);


    }
    /**
     * setResolution()
     * @param optionResolution
     */
    public void setResolution(int optionResolution){
        switch (optionResolution) {
            case 1 -> {
                widthScreen = 640;
                heightScreen = 480;
            }
            case 2 -> {
                widthScreen = 960;
                heightScreen = 540;
            }
            case 3 -> {
                widthScreen = 1280;
                heightScreen = 720;
            }
            case 4 -> {
                widthScreen = 1920;
                heightScreen = 1080;
            }
            case 5 -> {
                widthScreen = 2560;
                heightScreen = 1440;
            }
            case 6 -> {
                widthScreen = 3480;
                heightScreen = 2160;
            }
            case 7 -> {
                widthScreen = 7680;
                heightScreen = 4320;
            }
        }
    }
    /**
     * registerPlayer()
     * @param nickname String
     * @param name String
     * @return msj String
     */
    public String registerPlayer(String nickname, String name){
        StringBuilder msj = new StringBuilder();
        Player player = new Player(nickname, name, levels[0]);
        boolean isFound = false;
        for (int i = 0; i < players.length && !isFound; i++) {
            if(players[i] != null && players[i].getNickname().equalsIgnoreCase(player.getNickname())){
                isFound = true;
            }
        }
        if(!isFound){
            isFound = false;
            for (int i = 0; i < players.length && !isFound; i++) {
                if(players[i] == null){
                    players[i] = player;
                    isFound = true;
                    msj.append("The player has been successfully registered");
                }
            }if(!isFound){
                msj.append("No more players can be added");
            }
        }else{
            msj.append("This nickname was already created");
        }
        return msj.toString();
    }
    /**
     * modifyScorePlayer()
     * @param nickname String
     * @param score int
     * @return msj String
     */
    public String modifyScorePlayer(String nickname, int score){
        StringBuilder msj = new StringBuilder();
        int posPlayer = searchPosPlayerByNickname(nickname);
        if(posPlayer != -1){
            players[posPlayer].setScore(score);
            msj.append("Satisfactory modification of points");
        }else{
            msj.append("Player didn't find");
        }
        return msj.toString();
    }
    /**
     * tryLevelUp()
     * @param nickname String
     * @return msj String
     */
    public String tryLevelUp(String nickname) {
        StringBuilder msj = new StringBuilder();
        int posPlayer = searchPosPlayerByNickname(nickname);
        if(posPlayer != -1){
            int posLevel = searchPosLevelByNumber(players[posPlayer].getLevel().getNumber());
            if(posLevel != -1){
                if(players[posPlayer].getScore() >= players[posPlayer].getLevel().getPointsForNextLevel()){
                    players[posPlayer].modifyLevel(levels[posLevel+1]);
                    msj.append("The player can successfully level up\nThe new level is ").append(players[posPlayer].getLevel().getNumber());
                }else{
                    int scoreMissing = (players[posPlayer].getLevel().getPointsForNextLevel() - players[posPlayer].getScore());
                    msj.append("The player can not level up, it must get ").append(scoreMissing).append(" more");
                }
            }else{
                msj.append("Level didn't find");
            }

        }else{
            msj.append("Player didn't find");
        }

        return msj.toString();
    }   
    /**
     * registerTreasureToLevel()
     * @param name String
     * @param url String
     * @param scoreToIncrease int
     * @param numberLevel int
     * @param quantity int
     * @return msj String
     */
    public String registerTreasureToLevel(String name, String url, int scoreToIncrease, int numberLevel, int quantity) {
        StringBuilder msj = new StringBuilder();
        int posLevel = searchPosLevelByNumber(numberLevel);
        if (posLevel != -1) {
            Treasure treasure = new Treasure(name, url, scoreToIncrease, random.nextInt(widthScreen), random.nextInt(heightScreen));
            int quantityCompleted = 0;
            int positionsEmpty = 0;
            for (int i = 0; i < levels[posLevel].getTreasures().length; i++) {
                if (levels[posLevel].getTreasures()[i] == null) {
                    positionsEmpty++;
                }
            }
            if(positionsEmpty >= quantity){
                for (int i = 0; i < levels[posLevel].getTreasures().length && quantityCompleted != quantity; i++) {
                    if (levels[posLevel].getTreasures()[i] == null) {
                        levels[posLevel].getTreasures()[i] = treasure;
                        quantityCompleted++;
                        addNameTreasure(name);
                    }
                }

            msj.append("Treasure(s) created successfully");
            }else{
                msj.append("that amount of treasure cannot be added, there is only room for ").append(positionsEmpty);
            }
        }
        else{
            msj.append("Level didn't find");
        }
        return msj.toString();
    }
    /**
     * registerEnemyToLevel()
     * @param name String
     * @param type String
     * @param scoreToIncrease int
     * @param scoreToDecrease int
     * @param numberLevel int
     * @return msj String
     */
    public String registerEnemyToLevel(String name, String type, int scoreToIncrease, int scoreToDecrease, int numberLevel){
        StringBuilder msj = new StringBuilder();
        int posLevel = searchPosLevelByNumber(numberLevel);
        if(posLevel != -1){
            TypeEnemy typeE = null;
            String[] typesEnemies = {"ogre","abstract","boss","wizard"};
            boolean typeValid = false;
            for (String typeEnemy: typesEnemies) {
                if(typeEnemy.equalsIgnoreCase(type)){
                    typeValid = true;
                    typeE = TypeEnemy.valueOf(type.toUpperCase());
                }
            }
            if (typeValid){
                int countEnemies = 0;
                for (int i = 0; i < levels[posLevel].getEnemies().length; i++) {
                    if (levels[posLevel].getEnemies()[i] != null && levels[posLevel].getEnemies()[i].getName().equalsIgnoreCase(name)){
                        countEnemies++;
                    }
                }
                if (countEnemies == 0){
                    Enemy enemy = new Enemy(name, typeE, scoreToIncrease, scoreToDecrease, random.nextInt(widthScreen), random.nextInt(heightScreen));
                    boolean isFound = false;
                    for (int i = 0; i < levels[posLevel].getEnemies().length && !isFound; i++) {
                        if(levels[posLevel].getEnemies()[i] == null){
                            levels[posLevel].getEnemies()[i] = enemy;
                            isFound = true;
                            msj.append("Enemy created successfully ").append(levels[posLevel].getEnemies()[i].getType());
                        }
                    }
                    if(!isFound){
                        msj.append("It can't have more enemies");
                    }
                }else{
                    msj.append("It can't register the enemy, because there is already one like it");
                }
            }else{
                msj.append("The type of enemy is not valid");
            }

        }else {
            msj.append("Level didn't find");
        }
        return msj.toString();
    }

    /**
     * treasuresAndEnemiesOfALevel()
     * @param numberLevel int
     * @return msj String
     */

    public String treasuresAndEnemiesOfALevel(int numberLevel){
        StringBuilder msj = new StringBuilder();
        int posLevel = searchPosLevelByNumber(numberLevel);
        if (posLevel != -1){
            int countEnemies = 0;
            int countTreasures = 0;
            for (int j = 0; j < levels[posLevel].getTreasures().length; j++) {
                if (levels[posLevel].getTreasures()[j] != null) {
                    countTreasures++;
                }
            }
            for (int j = 0; j < levels[posLevel].getEnemies().length; j++) {
                if (levels[posLevel].getEnemies()[j] != null) {
                    countEnemies++;
                }
            }
            msj.append("Count treasures and enemies is ").append(countTreasures).append(" and ").append(countEnemies).append(" respectively");
        }else{
            msj.append("Position level didn't find");
        }
        return msj.toString();
    }
    /**
     * treasureInAllLevels()
     * @param nameTreasure String
     * @return msj String
     */
    public String treasureInAllLevels(String nameTreasure){
        int countTreasure = 0;
        StringBuilder msj = new StringBuilder();
        for (Level level : levels) {
            for (int j = 0; j < level.getTreasures().length; j++) {
                if (level.getTreasures()[j] != null && level.getTreasures()[j].getName().equalsIgnoreCase(nameTreasure)) {
                    countTreasure++;
                }
            }
        }
        if(countTreasure != 0) {
            msj.append("There are ").append(countTreasure).append(" equal treasures");
        }else{
            msj.append("This treasure isn't in the video game");
        }
        return msj.toString();
    }
    /**
     * enemyTypeOnAllLevels()
     * @param enemyType String
     * @return msj String
     */
    public String enemyTypeOnAllLevels(String enemyType){
    StringBuilder msj = new StringBuilder();
    int countEnemies = 0;
        for (Level level: levels) {
            for (int i = 0; i < level.getEnemies().length; i++){
                if(level.getEnemies()[i] != null && level.getEnemies()[i].getType().equalsIgnoreCase(enemyType)){
                    countEnemies++;
                }
            }
        }
    if(countEnemies != 0){
        msj.append("There ").append(countEnemies).append(" equal enemies");
    }else{
        msj.append("This enemy type isn't in the video game");
    }
    return msj.toString();
    }
    /**
     * mostRepeatedTreasure()
     * @return msj String
     */
    public String mostRepeatedTreasure(){
        StringBuilder msj = new StringBuilder();
        int[] countTreasures = new int[50];
        for (int i = 0; i < namesTreasures.length; i++) {
            for (Level level : levels) {
                for (int j = 0; j < level.getTreasures().length; j++) {
                    if ( namesTreasures[i]!= null && level.getTreasures()[j] != null && namesTreasures[i].equalsIgnoreCase(level.getTreasures()[j].getName())) {
                        countTreasures[i]++;
                    }
                }
            }
        }

        int posMax = 0;
        for (int i = 1; i < countTreasures.length; i++) {
            if( countTreasures[i] > countTreasures[posMax]){
                posMax = i;
            }
        }
        if(namesTreasures[posMax] != null){
            msj.append(namesTreasures[posMax]);
        }else{
            msj.append("There are no registered treasures");
        }
        
        return msj.toString();
    }

    /**
     * highestScoringEnemy()
     * @return msj String
     */
    public String highestScoringEnemy() {
        StringBuilder msj = new StringBuilder();
        Enemy[] enemiesWithScoreMax = new Enemy[10];
        Level[] levelsEnemiesWithScoreMax = new Level[10];

        for (int i = 0; i < levels.length; i++) {
            int posMax = 0;
                for (int j = 1; j < levels[i].getEnemies().length; j++) {
                    if (levels[i].getEnemies()[j] != null &&  levels[i].getEnemies()[posMax] != null && levels[i].getEnemies()[j].getScoreToIncrease() > levels[i].getEnemies()[posMax].getScoreToIncrease()){
                        posMax = j;
                    }
                }
                enemiesWithScoreMax[i] = levels[i].getEnemies()[posMax];
                levelsEnemiesWithScoreMax[i] = levels[i];

        }
        int posMax = 0;

        for (int i = 1; i < enemiesWithScoreMax.length; i++) {
            if(enemiesWithScoreMax[posMax] == null){
                posMax++;
            }else{
                if(enemiesWithScoreMax[i] != null && enemiesWithScoreMax[i].getScoreToIncrease() > enemiesWithScoreMax[posMax].getScoreToIncrease()){
                    posMax = i;
                }
            }
        }

        if(enemiesWithScoreMax[posMax] != null){
            msj.append("The enemy with the highest score is ").append(enemiesWithScoreMax[posMax].getName()).append(", and its level is ").append(levelsEnemiesWithScoreMax[posMax].getNumber());
        }else{
            msj.append("There's no enemies");
        }
    return msj.toString();

    }
    /**
     * consonantsNamesEnemies()
     * @return msj String
     */
    public String consonantsNamesEnemies() {
        StringBuilder namesEnemies = new StringBuilder();
        for (Level level : levels) {
            for (Enemy enemy : level.getEnemies()) {
                if (enemy != null) {
                    namesEnemies.append(enemy.getName());
                }
            }
        }
        StringBuilder consonantsNamesEnemies = new StringBuilder();
        for (char c : namesEnemies.toString().toLowerCase().toCharArray()) {
            if (c != ' ' && c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u'){
                consonantsNamesEnemies.append(c);
            }
        }
        return String.valueOf(consonantsNamesEnemies.toString().length());
    }
    
    /**
     * topFivePlayers()
     * @return msj String
     */
    public String topFivePlayers(){
        StringBuilder msj = new StringBuilder();
        for (int i = 0; i < players.length-1; i++) {
            for (int j = 0; j < players.length-1-i; j++) {

                if ((players[j] != null && players[j + 1] != null && players[j].getScore() > players[j + 1].getScore()) || (players[j] != null && players[j + 1] == null)) {
                    Player copyPlayer = players[j + 1];
                    players[j + 1] = players[j];
                    players[j] = copyPlayer;

                }
            }
        }
        for (int i = players.length-1; i > players.length-6; i--) {
            if(players[i] != null){
                msj.append(players[i].getNickname());
            }
            else{
                msj.append("-");}
            msj.append("\n");
        }
        return msj.toString();
    }

    /**
     * addNameTreasure()
     * @param name String
     */
    public void addNameTreasure(String name){
        boolean isFound = false;
        for (int i = 0; i < namesTreasures.length && !isFound; i++) {
            if (namesTreasures[i] != null && namesTreasures[i].equalsIgnoreCase(name)) {
                isFound = true;
                break;
            }
        }
        if(!isFound){
            for (int i = 0; i < namesTreasures.length && !isFound; i++) {
                if(namesTreasures[i] == null){
                    namesTreasures[i] = name;
                    isFound = true;
                }
            }
        }
    }

    /**
     * searchPosLevelByNumber()
     * @param numberLevel String
     * @return posLevel int
     */
    public int searchPosLevelByNumber(int numberLevel){
        int posLevel = -1;
        boolean isFound = false;
        for (int i = 0; i < levels.length && !isFound; i++) {
            if(levels[i] != null && levels[i].getNumber() == numberLevel){
                posLevel = i;
                isFound = true;
            }
        }
        return posLevel;
    }

    /**
     * searchPosPlayerByNickname()
     * @param nickname String
     * @return posPlayer int
     */

    public int searchPosPlayerByNickname(String nickname){
        int posPlayer = -1;
        boolean isFound = false;
        for (int i = 0; i < players.length && !isFound; i++) {
            if(players[i] != null && players[i].getNickname().equals(nickname)){
                posPlayer = i;
                isFound = true;
            }
        }
        return posPlayer;
    }
}