package com.projetjava;

import com.projetjava.models.Etudiant;
import com.projetjava.models.Matiere;
import com.projetjava.utils.Helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Etudiant> etudiants = new ArrayList<>();
    private static final List<Matiere> matieres = new ArrayList<>();
    private static int nextStudentId = 1;

    public static void main(String[] args) {
        System.out.println("Bienvenue dans l'application de gestion des étudiants");
        while (true) {
            printMenu();
            String choix = scanner.nextLine().trim();
            switch (choix) {
                case "1" -> ajouterMatiere();
                case "2" -> listerMatieres();
                case "3" -> ajouterEtudiant();
                case "4" -> listerEtudiants();
                case "5" -> saisirNote();
                case "6" -> afficherMoyenneEtudiant();
                case "7" -> afficherToutesMoyennes();
                case "8" -> supprimerEtudiant();
                case "9" -> supprimerMatiere();
                case "0" -> {
                    System.out.println("Au revoir !");
                    return;
                }
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1) Ajouter une matière");
        System.out.println("2) Lister les matières");
        System.out.println("3) Ajouter un étudiant");
        System.out.println("4) Lister les étudiants");
        System.out.println("5) Saisir une note pour un étudiant");
        System.out.println("6) Afficher la moyenne d'un étudiant");
        System.out.println("7) Afficher les moyennes de tous les étudiants");
        System.out.println("8) Supprimer un étudiant");
        System.out.println("9) Supprimer une matière");
        System.out.println("0) Quitter");
        System.out.print("Choix: ");
    }

    private static void ajouterMatiere() {
        System.out.print("Nom de la matière: ");
        String nom = scanner.nextLine().trim();
        if (nom.isEmpty()) {
            System.out.println("Nom vide, annulation.");
            return;
        }
        System.out.print("Coefficient (entier): ");
        try {
            int coef = Integer.parseInt(scanner.nextLine().trim());
            matieres.add(new Matiere(nom, coef));
            System.out.println("Matière ajoutée: " + nom + " (coef=" + coef + ")");
        } catch (NumberFormatException e) {
            System.out.println("Coefficient invalide.");
        }
    }

    private static void listerMatieres() {
        if (matieres.isEmpty()) {
            System.out.println("Aucune matière définie.");
            return;
        }
        System.out.println("Matières:");
        for (int i = 0; i < matieres.size(); i++) {
            Matiere m = matieres.get(i);
            System.out.printf("%d) %s (coef=%d)%n", i + 1, m.getNom(), m.getCoef());
        }
    }

    private static void ajouterEtudiant() {
        System.out.print("Nom: ");
        String nom = scanner.nextLine().trim();
        System.out.print("Prénom: ");
        String prenom = scanner.nextLine().trim();
        System.out.print("Sexe (M/F): ");
        String sexe = scanner.nextLine().trim();
        Etudiant e = new Etudiant(nextStudentId++, nom, prenom, sexe);
        etudiants.add(e);
        System.out.println("Étudiant ajouté: " + Helpers.formatName(nom, prenom) + " (id=" + e.getId() + ")");
    }

    private static void listerEtudiants() {
        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant.");
            return;
        }
        System.out.println("Étudiants:");
        for (Etudiant e : etudiants) {
            System.out.printf("id=%d - %s %s%n", e.getId(), e.getNom(), e.getPrenom());
        }
    }

    private static Optional<Etudiant> findEtudiantById(int id) {
        return etudiants.stream().filter(s -> s.getId() == id).findFirst();
    }

    private static void saisirNote() {
        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant.");
            return;
        }
        if (matieres.isEmpty()) {
            System.out.println("Aucune matière définie.");
            return;
        }
        listerEtudiants();
        System.out.print("Id étudiant: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Optional<Etudiant> opt = findEtudiantById(id);
            if (opt.isEmpty()) {
                System.out.println("Étudiant introuvable.");
                return;
            }
            Etudiant e = opt.get();
            listerMatieres();
            System.out.print("Choisir le numéro de la matière: ");
            int mIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (mIndex < 0 || mIndex >= matieres.size()) {
                System.out.println("Matière invalide.");
                return;
            }
            Matiere m = matieres.get(mIndex);
            System.out.print("Note (0-20): ");
            double note = Double.parseDouble(scanner.nextLine().trim());
            if (!Helpers.isValidNote(note)) {
                System.out.println("Note invalide. Doit être entre 0 et 20.");
                return;
            }
            e.setNote(m.getNom(), note);
            System.out.println("Note enregistrée: " + note + " pour " + e.getPrenom() + " " + e.getNom() + " en " + m.getNom());
        } catch (NumberFormatException ex) {
            System.out.println("Entrée numérique invalide.");
        }
    }

    private static void afficherMoyenneEtudiant() {
        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant.");
            return;
        }
        listerEtudiants();
        System.out.print("Id étudiant: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Optional<Etudiant> opt = findEtudiantById(id);
            if (opt.isEmpty()) {
                System.out.println("Étudiant introuvable.");
                return;
            }
            Etudiant e = opt.get();
            double moy = e.calculMoyenneGen(new ArrayList<>(matieres));
            System.out.println("Moyenne de " + e.getPrenom() + " " + e.getNom() + " = " + Helpers.round(moy, 2));
        } catch (NumberFormatException ex) {
            System.out.println("Id invalide.");
        }
    }

    private static void afficherToutesMoyennes() {
        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant.");
            return;
        }
        for (Etudiant e : etudiants) {
            double moy = e.calculMoyenneGen(new ArrayList<>(matieres));
            System.out.println(e.getPrenom() + " " + e.getNom() + " (id=" + e.getId() + ") -> " + Helpers.round(moy, 2));
        }
    }

    private static void supprimerEtudiant() {
        listerEtudiants();
        System.out.print("Id à supprimer: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Optional<Etudiant> opt = findEtudiantById(id);
            if (opt.isEmpty()) {
                System.out.println("Étudiant introuvable.");
                return;
            }
            etudiants.remove(opt.get());
            System.out.println("Étudiant supprimé.");
        } catch (NumberFormatException ex) {
            System.out.println("Id invalide.");
        }
    }

    private static void supprimerMatiere() {
        listerMatieres();
        System.out.print("Numéro de la matière à supprimer: ");
        try {
            int n = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (n < 0 || n >= matieres.size()) {
                System.out.println("Indice invalide.");
                return;
            }
            String name = matieres.remove(n).getNom();
            // Remove notes for that subject from all students
            for (Etudiant e : etudiants) {
                // there's no removeNote method; set to 0 or ignore (getNote uses default 0)
                // If desired, we could add such a method. For now, do nothing.
            }
            System.out.println("Matière supprimée: " + name);
        } catch (NumberFormatException ex) {
            System.out.println("Entrée invalide.");
        }
    }
}
