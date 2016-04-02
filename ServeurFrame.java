import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Attente de connexion.
 * @author Ange FOFE, Jalil MANKOURI & Andy HARVEL;
 * @version Version 1.0
 */
 
public class ServeurFrame extends JPanel implements ActionListener {
	/** La frame d'affichage. */
	private static JFrame frame;
	/** La frame qui appele de serveur. */
	private ConnectFrame connectFrame;
	/** Le thread du serveur. */
	private Thread thread;
/**
 * Constructeur de la Fenetre d'attente de connexion.
 * param connecFrame La fenetre qui a appele le serveur.
 */
 
public ServeurFrame(ConnectFrame connectFrame) {
	super();
	this.connectFrame = connectFrame;
	
	/* Traitement du Look & Feel : L&F Java.*/
	try {
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	} catch (Exception error) {
	}

	/* Creation d'une nouvelle frame. */
	frame = new JFrame("Attendre un appel");

	/* Gestion de la fermeture de cette frame. */
	frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			frame.dispose(); // On ferme la fenetre
		}
	});
	
	setLayout(new BorderLayout());

	/* Declaration d'un panel contenant un message. Affichage au centre. */
	JPanel pan1 = new JPanel();
	pan1.add(new JLabel("Attente d'une connexion en cours..."));
	add(pan1, "Center");

	/* Declaration d'un panel contenant un bouton. Affichage au sud. */
	JPanel pan2 = new JPanel();
	JButton buttonAnnuler = new JButton("Annuler");
	buttonAnnuler.addActionListener(this);
	pan2.add(buttonAnnuler);
	add(pan2, "South");

	/* Insertion du panel dans la frame et affichage de la frame. */
	frame.getContentPane().add("Center", this);
	frame.pack();
	frame.show();
	
	/* Lancement du serveur. */
	new Serveur(this);
}
/**
 * Evenement lie au bouton Annuler!
 * @param e L'evenement genere par le bouton.
 */
 
public void actionPerformed(ActionEvent e) {
	String action = e.getActionCommand();
	if (action.equals("Annuler")) {
		if (thread != null) { // Le serveur est actif.
			frame.dispose();
			thread.stop(); // On arrete le serveur.
			connectFrame.setSocket(null); // Il n'y a pas eu de connexion.
		}
	}
}
/**
 * Affecte le socket a la frame de Connexion.
 * @param socket Le socket de connxion par lequel va se faire le jeu.
 */
public void setSocket(Socket socket) {
	connectFrame.setSocket(socket);  // On envoie le socket.
	frame.dispose();                            // On ferme la fenetre.
	thread.stop();                                // On arrete le socket.
}
/**
 * This method was created in VisualAge.
 */
 
public void setThread(Thread thread) {
	this.thread = thread;
}
}