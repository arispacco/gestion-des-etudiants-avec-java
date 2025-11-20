import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Etudiant> classe = new ArrayList<>();
    static ArrayList<Matiere> listeMatieres = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean continuer = true;

        while (continuer) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Ajouter un étudiant");
            System.out.println("2. Ajouter une matière");
            System.out.println("3. Ajouter une note à un étudiant");
            System.out.println("4. Afficher le classement");
            System.out.println("5. Quitter");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1 -> ajouterEtudiant();
                case 2 -> ajouterMatiereGlobale();
                case 3 -> ajouterNoteEtudiant();
                case 4 -> afficherRang();
                case 5 -> continuer = false;
            }
        }
    }

    public static void ajouterMatiereGlobale() {
        System.out.print("Nom de la matière : ");
        String nom = scanner.nextLine();

        System.out.print("Coefficient : ");
        int coef = scanner.nextInt();
        scanner.nextLine();

        listeMatieres.add(new Matiere(nom, coef));
        System.out.println("Matière ajoutée pour toute la classe !");
    }

    public static void ajouterEtudiant() {
        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        System.out.print("Sexe : ");
        String sexe = scanner.nextLine();

        classe.add(new Etudiant(classe.size() + 1, nom, prenom, sexe));
        System.out.println("Étudiant ajouté !");
    }

    public static void ajouterNoteEtudiant() {
        if (classe.isEmpty() || listeMatieres.isEmpty()) {
            System.out.println("Ajoutez d'abord des étudiants et des matières !");
            return;
        }

        System.out.println("\n-- Choisir étudiant --");
        for (int i = 0; i < classe.size(); i++) {
            System.out.println(i + " - " + classe.get(i).getNom());
        }

        int ind = scanner.nextInt();
        scanner.nextLine();

        Etudiant e = classe.get(ind);

        System.out.println("\n-- Choisir matière --");
        for (int i = 0; i < listeMatieres.size(); i++) {
            System.out.println(i + " - " + listeMatieres.get(i).getNom());
        }

        int matInd = scanner.nextInt();
        scanner.nextLine();

        Matiere m = listeMatieres.get(matInd);

        System.out.print("Note : ");
        double note = scanner.nextDouble();

        e.setNote(m.getNom(), note);
        System.out.println("Note enregistrée !");
    }

    public static void afficherRang() {
        classe.sort((a, b) -> Double.compare(
                b.calculMoyenneGen(listeMatieres),
                a.calculMoyenneGen(listeMatieres)
        ));

        System.out.println("\n=== RANG ===");

        int rang = 1;
        for (Etudiant e : classe) {
            double m = e.calculMoyenneGen(listeMatieres);
            System.out.printf("Rang #%d - %s %s : %.2f/20\n",
                    rang, e.getNom(), e.getPrenom(), m);
            rang++;
        }
    }
}
