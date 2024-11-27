import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    // La fenêtre principale où tout sera affiché
    JFrame displayZoneFrame;

    // les Différents moteurs nécessaires au fonctionnement du jeu
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;
    Compass compass = new Compass();

    private GameTimer gameTimer;


    //constructeur principal de la classe
    public Main() throws Exception{
        // Crée une nouvelle fenêtre pour le jeu

        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(800,600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Initialisation du héros (le joueur)
        DynamicSprite hero = new DynamicSprite(200,300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")),48,50);


        System.out.println("coucou!");
        renderEngine = new RenderEngine(displayZoneFrame);
        gameEngine = new GameEngine(hero);
        gameTimer = new GameTimer(); // Initialisation du timer
        compass = new Compass();  // Instanciation correcte de Compass
        physicEngine = new PhysicEngine(compass);

        physicEngine.setGameTimer(gameTimer);

        renderEngine.setGameTimer(gameTimer);

        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer gameTimerEngine = new Timer(10, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(1, (time) -> {
            physicEngine.update();
            //Met à jour la boussole avec les nouvelles positions

        });

        renderTimer.start();
        gameTimerEngine.start();
        physicTimer.start();



        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

         EcranTitre ecranTitre = new EcranTitre(displayZoneFrame, renderEngine, gameEngine, gameTimer,compass);
         displayZoneFrame.setContentPane(ecranTitre);
     //   displayZoneFrame.setVisible(true);


        Playground level = new Playground("./data/level1.txt");
        ArrayList<FinishSprite> finishSprites = new ArrayList<>();
        for (Displayable displayable : level.getSpriteList()) {
            if (displayable instanceof FinishSprite) {
                finishSprites.add((FinishSprite) displayable);
            }
        }
        physicEngine.setFinishSprites(finishSprites);
        //SolidSprite testSprite = new DynamicSprite(100,100,test,0,0);
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
    }

    public static void main (String[] args) throws Exception {

        Main main = new Main();
    }
}
