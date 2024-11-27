import javax.swing.*;
import java.awt.*;

public class Compass extends JPanel {
    private double playerX, playerY; // Position joueur
    private double finishX, finishY; // Position objectif

    public Compass() {
        this.setPreferredSize(new Dimension(100, 100)); // Taille de la boussole
    }

    //Met à jour les positions pour recalculer l'angle
    public void updatePositions(double playerX, double playerY, double finishX, double finishY) {
        this.playerX = playerX;
        this.playerY = playerY;
        this.finishX = finishX;
        this.finishY = finishY;
        repaint(); // Redessiner la boussole
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Position et dimension de la boussole
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(getWidth(), getHeight()) / 2 - 10;

        // Dessiner le cercle de la boussole
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

        g2d.setColor(Color.BLACK);
        g2d.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

        // Calcul de l'angle entre le joueur et le finish
        double deltaX = finishX - playerX;
        double deltaY = finishY - playerY;
        double angle = Math.atan2(deltaY, deltaX); //Angle en radians

        //Dessin de la flèche (direction vers le finish)
        int arrowX = (int) (centerX + radius * Math.cos(angle));   //coordonnées de l’extrémité de l’aiguille
        int arrowY = (int) (centerY + radius * Math.sin(angle));

        g2d.setColor(Color.RED);
        g2d.drawLine(centerX, centerY, arrowX, arrowY);


        g2d.setColor(Color.BLACK);
        g2d.drawString("N", centerX - 5, centerY - radius + 10);
        g2d.drawString("Player", centerX - 20, centerY + 20);
    }
}
