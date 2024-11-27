import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EcranTitre extends JPanel {
    //Référence à la JFrame principale
    private JFrame displayZoneFrame;
    private GameEngine gameEngine;
    //Référence au moteur de rendu
    private RenderEngine renderEngine;
    private GameTimer gameTimer;
    private Compass compass;

    //Image pour l'arrière-plan
    private Image backgroundImage;

    private Image resizeImage(Image originalImage, int width, int height) {
        return originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public EcranTitre(JFrame displayZoneFrame, RenderEngine renderEngine, GameEngine gameEngine, GameTimer gameTimer, Compass compass) {
        this.displayZoneFrame = displayZoneFrame;
        this.renderEngine = renderEngine;

        try {
            backgroundImage = ImageIO.read(new File("./img/background3.png"));
            System.out.println("Image chargée : Dimensions = " + backgroundImage.getWidth(null) + "x" + backgroundImage.getHeight(null));
        } catch (IOException e) {
            System.err.println("Erreur : Impossible de charger l'image !");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erreur inattendue :");
            e.printStackTrace();
        }

        setLayout(new BorderLayout());

        // Titre principal
        JLabel title = new JLabel("Bienvenue au Jeu", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE); // Ajustez la couleur pour qu'elle soit visible sur l'image
        add(title, BorderLayout.NORTH);

        // Bouton pour commenc
        // er le jeu
        JButton startButton = new JButton("Commencer");
        startButton.addActionListener(e -> {
            gameTimer.start();
            displayZoneFrame.setContentPane(renderEngine); // Remplacer le contenu
            displayZoneFrame.add(compass, BorderLayout.EAST);
            displayZoneFrame.revalidate(); // Revalider le contenu
            displayZoneFrame.repaint(); // Repeindre pour appliquer
            renderEngine.addKeyListener(gameEngine); // Ajouter le KeyListener au panneau de jeu
            renderEngine.setFocusable(true); // Permettre au panneau de recevoir le focus
            renderEngine.requestFocusInWindow(); // Demander explicitement le focus
        });


        add(title, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Dessine l'image à la taille de la fenêtre
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
