import javax.swing.Timer;

public class GameTimer {
    private int gameTimeInSeconds; // Temps de jeu en secondes
    private Timer timer; // Timer d'instance

    // Constructeur
    public GameTimer() {
        this.gameTimeInSeconds = 0;
    }

    // Démarrer le timer
    public void start() {
        timer = new Timer(1000, e -> gameTimeInSeconds++); // Incrémenter toutes les secondes
        timer.start();
    }

    // Arrêter le timer
    public void stop() {
        if (timer != null) {
            timer.stop();
        }
    }

    // Récupérer le temps écoulé
    public int getGameTimeInSeconds() {
        return gameTimeInSeconds;
    }
}
