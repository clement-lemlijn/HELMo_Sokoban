package io;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.*;

/**
 * Cette classe propose différentes fonctions permettant de créer une interface
 * graphique et d'interagir avec cette dernière.
 *
 * @author Arnaud Comblin
 * @version 1.2
 */
public class Fenetre {

	private Fenetre() {
	}

	private static Cadre instance = new Cadre();

	/**
	 * Crée une fenêtre permettant d'organiser ses élements à l'aide d'une grille.
	 * Il faut exécuter cette fonction en premier lieu.
	 * 
	 * @param titre      - le titre principal de la fenêtre
	 * @param hauteur    - la hauteur exprimée en pixels de l'intérieur de la
	 *                   fenêtre
	 * @param largeur    - la largeur exprimée en pixels de l'intérieur de la
	 *                   fenêtre
	 * @param nbLignes   - le nombre de lignes de la grille
	 * @param nbColonnes - le nombre de colonnes de la grille
	 * @since 1.0
	 */
	public static void creerFenetre(String titre, int hauteur, int largeur, int nbLignes, int nbColonnes) {
		instance = new Cadre(titre, hauteur, largeur, nbLignes, nbColonnes);
	}

	/**
	 * Affiche la fenêtre. Cette fonction doit être exécutée une fois tous les
	 * éléments ajoutés. Dans le cas contraire, certains éléments pourraient ne pas
	 * s'afficher correctement.
	 * 
	 * @since 1.0
	 */
	public static void afficher() {
		instance.afficher();
	}

	/**
	 * Modifie le titre principal de la fenêtre.
	 * 
	 * @param titre - le nouveau titre
	 * @since 1.0
	 */
	public static void setTitre(String titre) {
		instance.setTitre(titre);
	}

	/**
	 * Modifie la couleur de l'arrière-plan de la fenêtre.
	 * 
	 * @param couleur - la nouvelle couleur de l'arrière-plan
	 * @since 1.0
	 */
	public static void setCouleurArrierePlan(Color couleur) {
		instance.setCouleurArrierePlan(couleur);
	}

	/**
	 * Modifie la couleur du texte affiché dans la fenêtre.
	 * 
	 * @param couleur - la nouvelle couleur du texte
	 * @since 1.0
	 */
	public static void setCouleurTexte(Color couleur) {
		instance.setCouleurTexte(couleur);
	}

	/**
	 * Modifie la taille du texte affiché dans la fenêtre.
	 * 
	 * @param taille - la nouvelle taille du texte
	 * @since 1.0
	 */
	public static void setTailleTexte(int taille) {
		instance.setTailleTexte(taille);
	}

	/**
	 * Définit une marge de séparation entre chaque élément de l'interface graphique
	 * et le bord de leur cellule.
	 * 
	 * @param marge - la grandeur de la marge exprimée en pixels à appliquer en
	 *              haut, à droite, en bas et à gauche de chaque élément
	 * @since 1.1
	 */
	public static void setMargesElements(int marge) {
		setMargesElements(marge, marge);
	}

	/**
	 * Définit des marges de séparation entre chaque élément de l'interface
	 * graphique et le bord de leur cellule.
	 * 
	 * @param margeVerticale   - la grandeur de la marge exprimée en pixels à
	 *                         appliquer en haut et en bas de chaque élément
	 * @param margeHorizontale - la grandeur de la marge exprimée en pixels à
	 *                         appliquer à gauche et à droite de chaque élément
	 * @since 1.1
	 */
	public static void setMargesElements(int margeVerticale, int margeHorizontale) {
		instance.setMargesElements(margeVerticale, margeHorizontale);
	}

	/**
	 * Ajoute un élément permettant d'afficher du texte dans une cellule de la
	 * grille de la fenêtre.
	 * 
	 * @param nomElement - un nom unique permettant d'identifier l'étiquette
	 * @param texte      - le texte à afficher initialement dans l'étiquette
	 * @param ligne      - l'indice de la ligne à laquelle doit être affichée
	 *                   l'étiquette
	 * @param colonne    - l'indice de la colonne à laquelle doit être affichée
	 *                   l'étiquette
	 * @since 1.0
	 */
	public static void ajouterEtiquette(String nomElement, String texte, int ligne, int colonne) {
		ajouterEtiquette(nomElement, texte, AlignementTexte.GAUCHE, ligne, colonne);
	}

	/**
	 * Ajoute un élément permettant d'afficher du texte dans une cellule de la
	 * grille de la fenêtre.
	 * 
	 * @param nomElement - un nom unique permettant d'identifier l'étiquette
	 * @param texte      - le texte à afficher initialement dans l'étiquette
	 * @param alignement - l'alignement utilisé pour afficher le texte
	 * @param ligne      - l'indice de la ligne à laquelle doit être affichée
	 *                   l'étiquette
	 * @param colonne    - l'indice de la colonne à laquelle doit être affichée
	 *                   l'étiquette
	 * @since 1.2
	 */
	public static void ajouterEtiquette(String nomElement, String texte, AlignementTexte alignement, int ligne,
			int colonne) {
		instance.ajouterEtiquette(nomElement, texte, alignement, ligne, colonne);
	}

	/**
	 * Ajoute un élément permettant à l'utilisateur de saisir du texte dans une
	 * cellule de la grille de la fenêtre.
	 * 
	 * @param nomElement - un nom unique permettant d'identifier la zone de saisie
	 * @param texte      - le texte à afficher initialement dans la zone de saisie
	 * @param ligne      - l'indice de la ligne à laquelle doit être affichée la
	 *                   zone de saisie
	 * @param colonne    - l'indice de la colonne à laquelle doit être affichée la
	 *                   zone de saisie
	 * @since 1.0
	 */
	public static void ajouterZoneSaisie(String nomElement, String texte, int ligne, int colonne) {
		instance.ajouterZoneSaisie(nomElement, texte, ligne, colonne);
	}

	/**
	 * Ajoute un élément permettant à l'utilisateur de sélectionner une valeur parmi
	 * plusieurs dans une cellule de la grille de la fenêtre.
	 * 
	 * @param nomElement - un nom unique permettant d'identifier la liste déroulante
	 * @param texte      - les différentes options à afficher initialement dans la
	 *                   liste déroulante (celles-ci doivent être séparées par un
	 *                   saut de ligne '\n')
	 * @param ligne      - l'indice de la ligne à laquelle doit être affichée la
	 *                   liste déroulante
	 * @param colonne    - l'indice de la colonne à laquelle doit être affichée la
	 *                   liste déroulante
	 * @since 1.0
	 */
	public static void ajouterListeDeroulante(String nomElement, String texte, int ligne, int colonne) {
		instance.ajouterListeDeroulante(nomElement, texte, ligne, colonne);
	}

	/**
	 * Ajoute un élément permettant à l'utilisateur de déclencher une action depuis
	 * une cellule de la grille de la fenêtre.
	 * 
	 * @param nomElement - un nom unique permettant d'identifier le bouton
	 * @param texte      - le texte à afficher initialement sur le bouton
	 * @param ligne      - l'indice de la ligne à laquelle doit être affiché le
	 *                   bouton
	 * @param colonne    - l'indice de la colonne à laquelle doit être affiché le
	 *                   bouton
	 * @since 1.0
	 */
	public static void ajouterBouton(String nomElement, String texte, int ligne, int colonne) {
		instance.ajouterBouton(nomElement, texte, ligne, colonne);
	}

	/**
	 * Définit la fonction à exécuter lorsqu'un évènement survient au niveau de
	 * l'interface graphique (par exemple : clic sur un bouton, pression d'une
	 * touche du clavier ...).
	 * 
	 * @param fonction - la fonction à exécuter pour traiter les évènements
	 * @since 1.0
	 */
	public static void setFonctionAuditeur(Method fonction) {
		instance.setFonctionAuditeur(fonction);
	}

	/**
	 * Retourne la valeur actuelle de l'élément spécifié.
	 * 
	 * @param nomElement - le nom unique identifiant l'élément
	 * @return la valeur actuelle de l'élément.
	 * @since 1.0
	 */
	public static String getValeur(String nomElement) {
		return instance.getValeur(nomElement);
	}

	/**
	 * Modifie la valeur de l'élément spécifié.
	 * 
	 * @param nomElement - le nom unique identifiant l'élément
	 * @param valeur     - la nouvelle valeur
	 * @since 1.0
	 */
	public static void setValeur(String nomElement, String valeur) {
		instance.setValeur(nomElement, valeur);
	}

	/**
	 * Efface toutes les images actuellement dessinées dans la fenêtre.
	 * 
	 * @since 1.0
	 */
	public static void effacerImages() {
		instance.effacerImages();
	}

	/**
	 * Fournit une image qui sera dessinée à la position spécifiée au sein de la
	 * fenêtre une fois la fonction {@link #dessinerImages} exécutée.
	 * 
	 * @param cheminFichier - le chemin d'accès au fichier (par exemple :
	 *                      "img\logo.png")
	 * @param x             - une position horizontale supérieure ou égale à 0
	 *                      exprimée en pixels (0 étant la position du bord gauche
	 *                      de la fenêtre)
	 * @param y             - une position verticale supérieure ou égale à 0
	 *                      exprimée en pixels (0 étant la position du bord
	 *                      supérieur de la fenêtre et l'axe étant orienté de haut
	 *                      en bas)
	 * @since 1.0
	 */
	public static void preparerImage(String cheminFichier, int x, int y) {
		instance.preparerImage(cheminFichier, x, y);
	}

	/**
	 * Dessine toutes les images fournies préalablement à l'aide de la fonction
	 * {@link #preparerImage}.
	 * 
	 * @since 1.0
	 */
	public static void dessinerImages() {
		instance.dessinerImages();
	}

}

class Cadre implements ActionListener {

	private JFrame cadre;
	private Panneau panneau;
	private final int NB_LIGNES;
	private final int NB_COLONNES;
	private Map<String, ElementFenetre> elementsParNom = new HashMap<String, ElementFenetre>();
	private Map<Position, ElementFenetre> elementsParPosition = new HashMap<Position, ElementFenetre>();
	private List<ElementFenetre> elements = new LinkedList<ElementFenetre>();
	private Method fonctionAuditeur;
	private Font police = new Font(Theme.NOM_POLICE, Font.PLAIN, Theme.TAILLE_POLICE);
	private Color couleurTexte = Theme.COULEUR_TEXTE;
	private int margeVerticale = 0, margeHorizontale = 0;

	public Cadre() {
		this(Theme.TITRE, Theme.HAUTEUR, Theme.LARGEUR, Theme.NB_LIGNES, Theme.NB_COLONNES);
	}

	public Cadre(String titre, int hauteur, int largeur, int nbLignes, int nbColonnes) {
		cadre = new JFrame(titre);
		cadre.setFocusable(true);
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadre.setResizable(false);
		cadre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evenement) {
				signalerEvenement(cadre.getTitle(), ActionFenetre.PRESSION_TOUCHE,
						String.valueOf(KeyEvent.getKeyText(evenement.getKeyCode())));
			}
		});

		panneau = new Panneau();
		panneau.setLayout(null);
		panneau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evenement) {
				ActionFenetre action = (evenement.getButton() == MouseEvent.BUTTON3) ? ActionFenetre.CLIC_DROIT
						: ActionFenetre.CLIC_GAUCHE;
				signalerEvenement(cadre.getTitle(), action, evenement.getX() + "," + evenement.getY());
			}
		});
		cadre.add(panneau);

		setCouleurArrierePlan(Theme.COULEUR_ARRIERE_PLAN);
		setDimensions(largeur, hauteur);

		NB_LIGNES = Math.min(Math.max(nbLignes, 1), panneau.getHeight() / 10);
		NB_COLONNES = Math.min(Math.max(nbColonnes, 1), panneau.getWidth() / 10);
	}

	public void setFonctionAuditeur(Method fonction) {
		Type[] typesParametres = fonction.getParameterTypes();
		if (typesParametres.length != 3 || !typesParametres[0].equals(String.class)
				|| !typesParametres[1].equals(ActionFenetre.class) || !typesParametres[2].equals(String.class)) {
			throw new IllegalArgumentException("La fonction \"" + fonction.getName()
					+ "\" doit avoir trois paramètres de types (String, ActionFenetre, String) !");
		}
		this.fonctionAuditeur = fonction;
	}

	public void afficher() {
		cadre.pack();
		cadre.setVisible(true);
	}

	public void setTitre(String titre) {
		cadre.setTitle(titre);
	}

	private void setDimensions(int largeur, int hauteur) {
		Dimension resolutionEcran = Toolkit.getDefaultToolkit().getScreenSize();
		largeur = Math.min(Math.max(50, largeur), (int) resolutionEcran.getWidth());
		hauteur = Math.min(Math.max(50, hauteur), (int) resolutionEcran.getHeight());
		panneau.setPreferredSize(new Dimension(largeur, hauteur));
		cadre.pack();
	}

	public void setCouleurArrierePlan(Color couleur) {
		panneau.setBackground(couleur);
	}

	public void setCouleurTexte(Color couleur) {
		couleurTexte = couleur;
		for (ElementFenetre element : elements) {
			element.setCouleurTexte(couleur);
		}
	}

	public void setTailleTexte(int taille) {
		police = new Font(Theme.NOM_POLICE, Font.PLAIN, taille);
		for (ElementFenetre element : elements) {
			element.getComposant().setFont(police);
		}
	}

	public void setMargesElements(int margeVerticale, int margeHorizontale) {
		this.margeVerticale = Math.min(Math.max(0, margeVerticale), getHauteurCellule() / 2);
		this.margeHorizontale = Math.min(Math.max(0, margeHorizontale), getLargeurCellule() / 2);
		for (Position position : elementsParPosition.keySet()) {
			ElementFenetre element = elementsParPosition.get(position);
			positionnerElement(element, position);
		}
	}

	public void ajouterEtiquette(String nomElement, String texte, AlignementTexte alignement, int ligne, int colonne) {
		verifierArguments(nomElement, ligne, colonne);
		int alignementSwing = SwingConstants.LEFT;
		if (alignement == AlignementTexte.CENTRE) {
			alignementSwing = SwingConstants.CENTER;
		} else if (alignement == AlignementTexte.DROITE) {
			alignementSwing = SwingConstants.RIGHT;
		}
		ajouterElement(new Etiquette(nomElement, new JLabel(texte, alignementSwing)), nomElement, ligne, colonne);
	}

	public void ajouterZoneSaisie(String nomElement, String texte, int ligne, int colonne) {
		verifierArguments(nomElement, ligne, colonne);
		ajouterElement(new ZoneSaisie(nomElement, new JTextField(texte)), nomElement, ligne, colonne);
	}

	public void ajouterListeDeroulante(String nomElement, String texte, int ligne, int colonne) {
		verifierArguments(nomElement, ligne, colonne);
		ElementFenetre element = new ListeDeroulante(nomElement, new JComboBox<String>());
		element.setValeur(texte);
		ajouterElement(element, nomElement, ligne, colonne);
	}

	public void ajouterBouton(String nomElement, String texte, int ligne, int colonne) {
		verifierArguments(nomElement, ligne, colonne);
		JButton bouton = new JButton(texte);
		bouton.addActionListener(this);
		ajouterElement(new Bouton(nomElement, bouton), nomElement, ligne, colonne);
	}

	private void verifierArguments(String nomElement, int ligne, int colonne) {
		verifierSiNomValide(nomElement);
		verifierSiPositionValide(new Position(ligne, colonne));
	}

	private void verifierSiNomValide(String nomElement) {
		if (nomElement == null || nomElement.isBlank()) {
			throw new IllegalArgumentException("Le nom \"" + nomElement + "\" n'est pas valide !");
		}
		if (elementsParNom.containsKey(nomElement)) {
			throw new IllegalArgumentException("Le nom \"" + nomElement + "\" est déjà attribué à un autre élément !");
		}
	}

	private void verifierSiPositionValide(Position positionElement) {
		if (positionElement.j < 0 || positionElement.j >= NB_COLONNES) {
			throw new IllegalArgumentException("La colonne " + positionElement.j + " n'existe pas !");
		}
		if (positionElement.i < 0 || positionElement.i >= NB_LIGNES) {
			throw new IllegalArgumentException("La ligne " + positionElement.i + " n'existe pas !");
		}
		if (elementsParPosition.containsKey(positionElement)) {
			throw new IllegalArgumentException(
					"La position " + positionElement + " est déjà attribuée à un autre élément !");
		}
	}

	private void ajouterElement(ElementFenetre element, String nom, int ligne, int colonne) {
		Position position = new Position(ligne, colonne);
		positionnerElement(element, position);
		element.setPolice(police);
		element.setCouleurTexte(couleurTexte);
		elementsParNom.put(nom, element);
		elementsParPosition.put(position, element);
		elements.add(element);
		panneau.add(element.getComposant());
	}

	private int getHauteurCellule() {
		return panneau.getHeight() / NB_LIGNES;
	}

	private int getLargeurCellule() {
		return panneau.getWidth() / NB_COLONNES;
	}

	private void positionnerElement(ElementFenetre element, Position position) {
		int largeurCellule = getLargeurCellule();
		int hauteurCellule = getHauteurCellule();
		int x = position.j * largeurCellule + margeHorizontale;
		int y = position.i * hauteurCellule + margeVerticale;
		int largeur = largeurCellule - 2 * margeHorizontale;
		int hauteur = hauteurCellule - 2 * margeVerticale;
		element.positionner(x, y, largeur, hauteur);
	}

	public String getValeur(String nomElement) {
		ElementFenetre element = verifierSiElementExiste(nomElement);
		return element.getValeur();
	}

	public void setValeur(String nomElement, String texte) {
		ElementFenetre element = verifierSiElementExiste(nomElement);
		element.setValeur(texte);
	}

	private ElementFenetre verifierSiElementExiste(String nomElement) {
		ElementFenetre element = elementsParNom.get(nomElement);
		if (element == null) {
			throw new IllegalArgumentException("L'élément \"" + nomElement + "\" n'existe pas !");
		}
		return element;
	}

	public void effacerImages() {
		panneau.effacerImages();
	}

	public void preparerImage(String cheminFichier, int x, int y) {
		try {
			panneau.preparerImage(cheminFichier, x, y);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Le fichier \"" + cheminFichier + "\" n'a pas été trouvé !");
		}
	}

	public void dessinerImages() {
		panneau.dessinerImages();
	}

	@Override
	public void actionPerformed(ActionEvent evenement) {
		String nomElement = getElement((Component) evenement.getSource());
		signalerEvenement(nomElement, ActionFenetre.CLIC_BOUTON, null);
	}

	private String getElement(Component composant) {
		for (ElementFenetre element : elements) {
			if (element.possedeComposant(composant)) {
				return element.getNom();
			}
		}
		return null;
	}

	private void signalerEvenement(String nomElement, ActionFenetre action, String valeur) {
		if (fonctionAuditeur != null) {
			try {
				fonctionAuditeur.invoke(null, nomElement, action, valeur);
			} catch (Exception exception) {
				exception.getCause().printStackTrace();
			}
		}
	}

	private class Position {

		public int i, j;

		public Position(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public int hashCode() {
			return Objects.hash(i, j);
		}

		@Override
		public boolean equals(Object autre) {
			if (autre == this) {
				return true;
			}
			if (!(autre instanceof Position)) {
				return false;
			}
			Position position = (Position) autre;
			return i == position.i && j == position.j;
		}

		@Override
		public String toString() {
			return "[ligne = " + i + ", colonne = " + j + "]";
		}

	}

	private class Panneau extends JPanel {

		private static final long serialVersionUID = -2259378431692527399L;

		private Map<String, Image> cheminsEtImages = new HashMap<String, Image>();
		private List<ImageEtPosition> imagesEtPositions = new LinkedList<ImageEtPosition>();

		public void effacerImages() {
			imagesEtPositions.clear();
		}

		public void preparerImage(String cheminFichier, int x, int y) throws FileNotFoundException {
			if (!cheminsEtImages.containsKey(cheminFichier)) {
				cheminsEtImages.put(cheminFichier, ImageHelper.chargerImage(cheminFichier));
			}
			imagesEtPositions.add(new ImageEtPosition(cheminsEtImages.get(cheminFichier), new Position(y, x)));
		}

		public void dessinerImages() {
			repaint();
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			for (ImageEtPosition imageEtPosition : imagesEtPositions) {
				g.drawImage(imageEtPosition.image, imageEtPosition.position.j, imageEtPosition.position.i, this);
			}
		}

		private class ImageEtPosition {

			public Image image;
			public Position position;

			public ImageEtPosition(Image image, Position position) {
				this.image = image;
				this.position = position;
			}

		}

	}

}

abstract class ElementFenetre {

	private String nom;
	private Component composant;

	public ElementFenetre(String nom, Component composant) {
		this.nom = nom;
		this.composant = composant;
	}

	public String getNom() {
		return nom;
	}

	abstract String getValeur();

	abstract void setValeur(String valeur);

	public void positionner(int x, int y, int largeur, int hauteur) {
		composant.setBounds(x, y, largeur, hauteur);
	}

	public void setPolice(Font police) {
		composant.setFont(police);
	}

	public void setCouleurTexte(Color couleur) {
		composant.setForeground(couleur);
	}

	public Component getComposant() {
		return composant;
	}

	public boolean possedeComposant(Component composant) {
		return this.composant.equals(composant);
	}

	@Override
	public int hashCode() {
		return nom.hashCode();
	}

	@Override
	public boolean equals(Object autre) {
		if (this == autre) {
			return true;
		}
		if (!(autre instanceof ElementFenetre)) {
			return false;
		}
		ElementFenetre element = (ElementFenetre) autre;
		return nom.equals(element.nom);
	}
}

class Etiquette extends ElementFenetre {

	public Etiquette(String nom, JLabel etiquette) {
		super(nom, etiquette);
	}

	@Override
	public String getValeur() {
		return ((JLabel) getComposant()).getText();
	}

	@Override
	public void setValeur(String valeur) {
		((JLabel) getComposant()).setText(valeur);
	}

}

class ZoneSaisie extends ElementFenetre {

	public ZoneSaisie(String nom, JTextField zoneSaisie) {
		super(nom, zoneSaisie);
	}

	@Override
	public String getValeur() {
		return ((JTextField) getComposant()).getText();
	}

	@Override
	public void setValeur(String valeur) {
		((JTextField) getComposant()).setText(valeur);
	}

}

class ListeDeroulante extends ElementFenetre {

	public ListeDeroulante(String nom, JComboBox<String> listeDeroulante) {
		super(nom, listeDeroulante);
	}

	@Override
	public String getValeur() {
		@SuppressWarnings("unchecked")
		JComboBox<String> listDeroulante = (JComboBox<String>) getComposant();
		return String.valueOf(listDeroulante.getSelectedItem());
	}

	@Override
	public void setValeur(String valeur) {
		@SuppressWarnings("unchecked")
		JComboBox<String> listDeroulante = (JComboBox<String>) getComposant();
		listDeroulante.removeAllItems();
		for (String option : valeur.split("\n")) {
			listDeroulante.addItem(option);
		}
		listDeroulante.setSelectedIndex(0);
	}

}

class Bouton extends ElementFenetre {

	public Bouton(String nom, JButton bouton) {
		super(nom, bouton);
	}

	@Override
	public String getValeur() {
		return ((JButton) getComposant()).getText();
	}

	@Override
	public void setValeur(String valeur) {
		((JButton) getComposant()).setText(valeur);
	}

}

final class Theme {

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Dimensions
	public static final String TITRE = "Sans nom";
	public static final int LARGEUR = 640;
	public static final int HAUTEUR = 480;
	public static final int NB_COLONNES = 5;
	public static final int NB_LIGNES = 14;

	// Police de caractères
	public static final String NOM_POLICE = "Arial";
	public static final int TAILLE_POLICE = 13;

	// Couleurs
	public static final Color COULEUR_TEXTE = Color.BLACK;
	public static final Color COULEUR_ARRIERE_PLAN = new Color(240, 240, 240);

}

class ImageHelper {

	public static Image chargerImage(String cheminFichier) throws FileNotFoundException {
		File file = new File(cheminFichier);
		if (!file.exists() || !file.isFile()) {
			throw new FileNotFoundException("Fichier \"" + cheminFichier + "\" non trouvé !");
		}
		return Toolkit.getDefaultToolkit().getImage(cheminFichier);
	}

}