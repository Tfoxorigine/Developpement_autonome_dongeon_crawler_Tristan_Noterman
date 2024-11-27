import java.util.ArrayList;
import java.util.List;

public class PhysicEngine implements Engine {
    private ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList<Sprite> environment;
    private ArrayList<FinishSprite> finishSprites = new ArrayList<>();
    private GameTimer gameTimer; //référence au GameTimer
    private Compass compass; //Ajout de la boussole

    public PhysicEngine(Compass compass) {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
        this.compass = compass; // Initialisation de la boussole
    }

    public void setGameTimer(GameTimer gameTimer) {
        this.gameTimer = gameTimer;
    }

    public void addToEnvironmentList(Sprite sprite) {
        if (!environment.contains(sprite)) {
            environment.add(sprite);
        }
    }

    public void setFinishSprites(ArrayList<FinishSprite> finishSprites) {
        this.finishSprites = finishSprites;
    }

    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    public void addToMovingSpriteList(DynamicSprite sprite) {
        if (!movingSpriteList.contains(sprite)) {
            movingSpriteList.add(sprite);
        }
    }

    @Override
    public void update() {
        for (DynamicSprite dynamicSprite : movingSpriteList) {
            dynamicSprite.moveIfPossible(environment);
            for (FinishSprite finishSprite : finishSprites) {
                //System.out.println("Checking if player at (" + dynamicSprite.getX() + ", " + dynamicSprite.getY() + ") intersects with finish at (" + finishSprite.getX() + ", " + finishSprite.getY() + ")");
                compass.updatePositions(dynamicSprite.getX(), dynamicSprite.getY(), finishSprite.getX(), finishSprite.getY());
                if (finishSprite.isPlayerOnFinish(dynamicSprite)) {
                    System.out.println("Fin du jeu !");
                    if (gameTimer != null) {
                        gameTimer.stop();
                    }
                    return;
                }
            }
        }
    }
}
