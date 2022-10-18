package ui;
import java.util.Locale;
import java.util.Scanner;

import model.VideoGame;

public class Main {
    private final Scanner reader;
    private final VideoGame videoGame;
    public Main(){
        reader = new Scanner(System.in);
        videoGame = new VideoGame();
    }
    /**
     * main()
     * @param args String[]
     */
    public static void main(String[] args){
        Main main = new Main();
        main.cleanConsole();
        main.uiSetResolution();
        int option = 0; 
		do{
            main.cleanConsole();
			option = main.getOptionShowMenu();
            main.cleanConsole();
			main.executeOption(option);
            main.reader.nextLine();
            main.reader.nextLine();
		}while(option != 0);
		main.reader.close();
    }
    /**
     * getOptionShowMenu()
     * @return option int
     */
    public int getOptionShowMenu(){
        int option = 0;
		System.out.println("<<<<<  Menu >>>>>");
		System.out.println(
                """
                        1. Create player
                        2. Register enemy to a level
                        3. Register treasure to a level
                        4. Modify a player's score
                        5. Increase the level of a player
                        6. Treasures and enemies of a level
                        7. Treasures on every level
                        8. Enemy type on all levels
                        9. Most repeated treasure
                        10. Highest scoring enemy
                        11. Consonants of the names of the enemies
                        12. Top 5 players
                        0. Exit.""");
		option = reader.nextInt(); 
		return option; 
    }
    
    /**
     * executeOption()
     * @param option int
     */
    public void executeOption(int option){

        switch (option) {
            case 1 -> uiRegisterPlayer();
            case 2 -> uiRegisterEnemyToLevel();
            case 3 -> uiRegisterTreasureToLevel();
            case 4 -> uiModifyScorePlayer();
            case 5 -> uiTryLevelUp();
            case 6 -> uiTreasuresAndEnemiesOfALevel();
            case 7 -> uiTreasureInAllLevels();
            case 8 -> uiEnemyTypeOnAllLevels();
            case 9 -> uiMostRepeatedTreasure();
            case 10 -> uiHighestScoringEnemy();
            case 11 -> uiConsonantsNamesEnemies();
            case 12 -> uiTopFivePlayers();
            default -> {
            }
        }
    }
    /**
     * cleanConsole()
     */
    public void cleanConsole(){
        System.out.print("\033[H\033[2J");
    }
    /**
     * uiSetResolution()
     */
    public void uiSetResolution(){
        System.out.println("<<<<< Welcome to Video Game >>>>>");
        System.out.println("Resolutions:");
        System.out.println(
        """
        1. SD
        2. QHD
        3. HD
        4. FHD
        5. 2K
        6. 4K
        7. 8K
        """);
        System.out.print("Resolution:");
        int optionResolution = reader.nextInt();
        videoGame.setResolution(optionResolution);
    }

    /**
     * uiRegisterPlayer()
     */
    public void uiRegisterPlayer(){
        System.out.println("Register player");
        System.out.print("Nickname: ");
        String nickname = reader.next();
        System.out.print("Name: ");
        String name = reader.next();
        System.out.println(videoGame.registerPlayer(nickname, name));
    }
    /**
     * uiRegisterEnemyToLevel()
     */
    public void uiRegisterEnemyToLevel(){
        System.out.println("Register enemy");
        System.out.print("Name: ");
        String name = reader.next();
        System.out.print("Type: ");
        String type = reader.next();
        System.out.print("Score to increase to player: ");
        int scoreToIncrease = reader.nextInt();
        System.out.print("Score to decrease to player: ");
        int scoreToDecrease = reader.nextInt();
        System.out.print("Number level: ");
        int numberLevel = reader.nextInt();
        System.out.println(videoGame.registerEnemyToLevel(name, type, scoreToIncrease, scoreToDecrease, numberLevel));

    }
    /**
     * uiRegisterTreasureToLevel()
     */
    public void uiRegisterTreasureToLevel(){
        System.out.println("Register treasure");
        System.out.print("Name: ");
        String name = reader.next();
        System.out.print("Url: ");
        String url = reader.next();
        System.out.print("Score to increase: ");
        int scoreToIncrease = reader.nextInt();
        System.out.print("Number level: ");
        int numberLevel = reader.nextInt();
        System.out.print("Quantity: ");
        int quantity = reader.nextInt();
        System.out.println(videoGame.registerTreasureToLevel(name, url, scoreToIncrease, numberLevel, quantity));
    }
    /**
     * uiModifyScorePlayer()
     */
    public void uiModifyScorePlayer(){
        System.out.println("Modify score");
        System.out.print("Nickname: ");
        String nickname = reader.next();
        System.out.print("Score: ");
        int score = reader.nextInt();
        System.out.println(videoGame.modifyScorePlayer(nickname, score));
    }
    /**
     * uiTryLevelUp()
     */
    public void uiTryLevelUp(){
        System.out.println("Level up");
        System.out.print("Nickname: ");
        String nickname = reader.next();
        System.out.println(videoGame.tryLevelUp(nickname));
    }
    /**
     * uiTreasuresAndEnemiesOfALevel()
     */
    public void uiTreasuresAndEnemiesOfALevel(){
        System.out.println("Consult treasures and enemies of a level");
        System.out.print("Level: ");
        int numberLevel = reader.nextInt();
        System.out.println(videoGame.treasuresAndEnemiesOfALevel(numberLevel));
    }
    /**
     * uiTreasureInAllLevels()
     */
    public void uiTreasureInAllLevels(){
        System.out.println("Consult quantities of a treasure");
        System.out.print("Treasure: ");
        String nameTreasure = reader.next();
        System.out.println(videoGame.treasureInAllLevels(nameTreasure));
    }
    /**
     * uiEnemyTypeOnAllLevels()
     */
    public void  uiEnemyTypeOnAllLevels(){
        System.out.println("Consult quantities of a type enemy");
        System.out.println("Enemy type: ");
        String enemyType = reader.next();
        System.out.println(videoGame.enemyTypeOnAllLevels(enemyType));
    }
    /**
     * uiMostRepeatedTreasure()
     */
    public void uiMostRepeatedTreasure(){
        System.out.println("Consult most repeated treasure");
        System.out.println(videoGame.mostRepeatedTreasure());
    }
    /**
     * uiHighestScoringEnemy()
     */
    public void uiHighestScoringEnemy(){
        System.out.println("Consult enemy with highest scoring");
        System.out.println(videoGame.highestScoringEnemy());
    }
    /**
     * uiConsonantsNamesEnemies()
     */
    public void uiConsonantsNamesEnemies(){
        System.out.println("Consult number of consonants of the names of the enemies");
        System.out.println(videoGame.consonantsNamesEnemies());
    }
    /**
     * uiTopFivePlayers()
     */
    public void uiTopFivePlayers(){
        System.out.println("Consult top five players");
        System.out.println(videoGame.topFivePlayers());
    }
}
