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
	
	private static JFrame frame;
	private ConnectFrame connectFrame;
	/** Pour le serveur. */
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

	
	frame = new JFrame("Attendre un appel");

	/* Gestion de la fermeture de cette frame. */
	frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			frame.dispose(); // On ferme la fenetre
		}
	});
	
	setLayout(new BorderLayout());

	/* Message au centre.  */
	JPanel pan1 = new JPanel();
	pan1.add(new JLabel("Attente d'une connexion en cours..."));
	add(pan1, "Center");

	/* Bouton au sud. */
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

 
public void actionPerformed(ActionEvent e) {
	String action = e.getActionCommand();
	if (action.equals("Annuler")) {
		if (thread != null) { 
			frame.dispose();
			/** On arrête le serveur grâce au thread. */
			thread.stop(); 
			/** Pas de connexion. */
			connectFrame.setSocket(null); 
		}
	}
}

public void setSocket(Socket socket) {
	connectFrame.setSocket(socket); 
	frame.dispose();                            /** Ferme la fenetre. */
	thread.stop();                                /** Stop le socket. */
}

 
public void setThread(Thread thread) {
	this.thread = thread;
}
}