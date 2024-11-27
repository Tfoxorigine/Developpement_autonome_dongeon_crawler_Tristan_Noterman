import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {
    private ArrayList<Displayable> renderList;
    private GameTimer gameTimer; // Référence au timer



    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
    }

    public void setGameTimer(GameTimer gameTimer) {
        this.gameTimer = gameTimer;
    }

    public void addToRenderList(Displayable displayable) {
        if (!renderList.contains(displayable)) {
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable) {
        if (!renderList.contains(displayable)) {
            renderList.addAll(displayable);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Dessiner tous les objets affichables
        for (Displayable renderObject : renderList) {
            renderObject.draw(g);
        }

        // Dessiner le cadre contenant le temps de jeu
        if (gameTimer != null) {
            drawGameTime(g);
        }
    }

    @Override
    public void update() {
        this.repaint();
    }

    // Méthode pour dessiner le temps de jeu
    private void drawGameTime(Graphics g) {
        String timeText = "Temps : " + gameTimer.getGameTimeInSeconds() + " s";

        // Définir la position et la taille du cadre
        int width = 150;
        int height = 50;
        int x = getWidth() - width - 10; // En haut à droite
        int y = 10;

        // Dessiner le fond du cadre
        g.setColor(new Color(0, 0, 0, 150)); // Fond semi-transparent
        g.fillRect(x, y, width, height);

        // Dessiner les bordures
        g.setColor(Color.WHITE);
        g.drawRect(x, y, width, height);

        // Dessiner le texte du temps
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString(timeText, x + 10, y + 30);
    }
}
