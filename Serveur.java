import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Mise en place d'un serveur et attente de connection du joueur.
 * @author Ange FOFE, Jalil MANKOURI & Andy HARVEL;
 * @version Version 1.0
 */

public class Serveur implements Runnable {

	/**Frame serveur. */
	private ServeurFrame serveurFrame;
	/** Port de connexion.*/
	private static final int PORT = 1313;
	/** Initialisation du Thread pour la mise en place du serveur en parralèle. */
	private Thread thread;


/** D?marrage du serveur dans un thread. */
public Serveur(ServeurFrame serveurFrame) {

	this.serveurFrame = serveurFrame;
	thread = new Thread(this);
	serveurFrame.setThread(thread);
	thread.start();

}


/** Lancement du serveur socket avec attente de connexion. */

public void run() {

	/** Accès au serveur par socket. */
	ServerSocket socketServeur;
	try {
		socketServeur = new ServerSocket(PORT, 5);
	} catch (IOException e) {
		/** Si un problème est rencontré alors on affiche l'erreur et on ferme le thread. */
		serveurFrame.setSocket(null);
		thread.stop();
		return;
	}
	Socket socket = null;
	
	try {
		/** Attente de connexion. */
		socket = socketServeur.accept();
	} catch (IOException e) {
		socket = null;
	}
	/* On attribue le socket à la frame de notre serveur. */
	serveurFrame.setSocket(socket);
}
}