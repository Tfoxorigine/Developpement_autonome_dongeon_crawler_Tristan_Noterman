import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FinishSprite extends Sprite {
    public FinishSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    //détection de chevauchement entre le joueur et la tuile
    public boolean isPlayerOnFinish(DynamicSprite player) {
        if (this.getHitBox().intersects(player.getHitBox())) {
            System.out.println("Player on finish!");
            return true;
        }
        return false;
    }
}
