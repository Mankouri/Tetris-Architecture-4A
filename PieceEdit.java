/*
 Editeur de pieces de Tetris.
 @author Ange FOFE, Jalil MANKOURI & Andy HARVEL;
  */
 
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class PieceEdit extends JPanel implements MouseListener, ActionListener {
	//Matrice d'edition de la piece
	private Matrix matrice;

	//Jeu a qui il faut retourner la piece creee
	private Jeu jeu;

	// Frame d'affichage de l'editeur
	private static JFrame frame;

	//Canvas d'affichage de la matrice de la piece
	private static PieceEditCanvas canvas;

	// Bouton d'ajout de la piece au jeu
	private static JButton buttonAdd;

	// Liste des tailles de la matrice d'edition de la piece
	private static JComboBox listTailles;

	// Liste des couleurs
	private static JComboBox listColors;

	// Bouton de sauvegarde dans un fichier
	private static JButton buttonSave;

	// Bouton de chargement a partir d'un fichier
	private static JButton buttonLoad;

	// Bouton annulation de l'edition
	private static JButton buttonCancel;
/*
 Constructeur de l'editeur de pieces
 @param size Taille de la matrice de la piece
 @param jeu Jeu a qui retourner la piece a ajouter
 */
 
public PieceEdit(int size, Jeu jeu) {
	super();
	this.matrice = new Matrix(size);
	this.jeu = jeu;
	// Traitement du Look & Feel : L&F Java
	try {
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	} catch (Exception error) {
	}

	// Creation d'une nouvelle frame
	frame = new JFrame("Editeur de pieces");

	// Gestion de la fermeture de cette frame
	frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			frame.dispose(); // On ferme la fenetre
		}
	});
	setLayout(new BorderLayout());
	canvas = new PieceEditCanvas(matrice.getSize());
	canvas.setMat(matrice);
	canvas.repaint();
	canvas.addMouseListener(this);
	add(canvas, "Center");

	JPanel statPanel = new JPanel();
	statPanel.setLayout(new BorderLayout());
	
	JPanel pan1 = new JPanel();
	/* Taille de la matrice. */
	listTailles = new JComboBox();
	for (int i = 0; i < 5; i++) {
		listTailles.addItem((i + 1) + " x " + (i + 1));
	}
	listTailles.setSelectedIndex(size-1);
	listTailles.setActionCommand("TailleMatrice");
	listTailles.addActionListener(this);

	pan1.add(new JLabel("Taille: "));
	pan1.add(listTailles);
	
	statPanel.add(pan1, "North");
	
	JPanel pan2 = new JPanel();
	// Couleurs des blocs de la matrice
	listColors = new JComboBox();
	for (int i = 0; i < Couleur.getSize(); i++) {
		listColors.addItem(Couleur.getNomCouleur(i));
	}
	listColors.setSelectedIndex(0);

	pan2.add(new JLabel("Couleur: "));
	pan2.add(listColors);

	statPanel.add(pan2, "Center");
	add(statPanel,"East");
	// Boutons
	JPanel pan3 = new JPanel();
	buttonSave = new JButton("Sauver");
	buttonSave.addActionListener(this);
	pan3.add(buttonSave);
	buttonLoad = new JButton("Charger");
	buttonLoad.addActionListener(this);
	pan3.add(buttonLoad);
	buttonCancel = new JButton("Annuler");
	buttonCancel.addActionListener(this);
	pan3.add(buttonCancel);
	buttonAdd = new JButton("Ajouter au jeu");
	buttonAdd.addActionListener(this);
	pan3.add(buttonAdd);
	add(pan3, "South");
	frame.getContentPane().add("Center", this);
	frame.pack();
	frame.show();
}
/*
 Methode d'appel dans le cas d'appui sur un bouton ou une liste.
 @param e Type d'action.
 */
public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand().equals("Charger")) {
		new LoadFrame("", this);
	} else if (e.getActionCommand().equals("Sauver")) {
		new SaveFrame("", matrice);
	} else if (e.getActionCommand().equals("Annuler")) {
		frame.dispose();
	}else if (e.getActionCommand().equals("Ajouter au jeu")) {
		frame.dispose();
		jeu.addUserPiece(matrice);
	} else if (e.getActionCommand().equals("TailleMat")) {
		setSizeMat(listTailles.getSelectedIndex() + 1);
	}
}
/*
 Gestionnaire de clic sur le bouton de la souris (obligatoire pour MouseListener).
 @param e Type d'evenement.
 */
public void mouseClicked(MouseEvent e) {
}
/*
 Gestionnaire de clic sur le bouton de la souris (obligatoire pour MouseListener).
 @param e Type d'evenement.
 */
public void mouseEntered(MouseEvent e) {
}
/*
 Gestionnaire de clic sur le bouton de la souris (obligatoire pour MouseListener).
 @param e Type d'evenement.
 */
public void mouseExited(MouseEvent e) {
}
/*
 Gestionnaire de clic sur le bouton de la souris (obligatoire pour MouseListener).
 @param e Type d'evenement.
 */
public void mousePressed(MouseEvent e) {
}
/*
 Gestionnaire de clic sur le bouton de la souris (obligatoire pour MouseListener).
 @param e Type d'evenement.
 */
public void mouseReleased(MouseEvent e) {
	//Un clic de souris sur le canvas de visualisation de la matrice permet de
	//- selectionner la case avec la couleur en cours si elle etait vide ou remplir d'une autre couleur que celle en cours;                          
	//- deselectionner la case sinon                                                
	if (matrice.getVal(e.getX()/24, e.getY()/24) != listColors.getSelectedIndex()) {
		matrice.setVal(e.getX()/24, e.getY()/24, listColors.getSelectedIndex());
	} else {
		matrice.setVal(e.getX()/24, e.getY()/24, -1);
	}
	// On repercute l'effet dans la matrice
	canvas.setMat(matrice);
	canvas.repaint();
}
/*
 Change la matrice d'edition.
 @param mat Nouvelle matrice a editer
 */
public void setMatrix(Matrix matrice) {
	this.matrice = matrice;
	// La taille courante d'edition se met a la valeur de la taille de la matrice
	listTailles.setSelectedIndex(matrice.getSize()-1);
	canvas.setMat(matrice);
	repaint();
}
/*
 Change la taille de la matrice d'edition
 @param size Nouvelle taille de la matrice d'edition
 */
public void setSizeMat(int size) {
	Matrix matrice = new Matrix(size);
	/* On copie tout en recentrant le contenu de la matrice precedente dans la 
	nouvelle matrice redimensionnee */
	int miyeu1X = matrice.getSize() / 2;
	int miyeu1Y = matrice.getSize() / 2;
	int miyeu2X = this.matrice.getSize() / 2;
	int miyeu2Y = this.matrice.getSize() / 2;
	for (int x = 0; x < this.matrice.getSize(); x++) {
		for (int y = 0; y < this.matrice.getSize(); y++) {
			matrice.setVal(x + miyeu1X - miyeu2X, y + miyeu1Y - miyeu2Y, this.matrice.getVal(x, y));
		}
	}
	this.matrice = matrice;
	canvas.setMat(this.matrice);
	canvas.repaint();
}
}
