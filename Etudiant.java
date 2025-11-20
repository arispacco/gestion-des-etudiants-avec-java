import java.util.HashMap;
import java.util.ArrayList;

public class Etudiant {

    private int id;
    private String nom;
    private String prenom;
    private String sexe;

    private HashMap<String, Double> notes = new HashMap<>();

    public Etudiant(int id, String nom, String prenom, String sexe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setNote(String matiere, double note) {
        notes.put(matiere, note);
    }

    public double getNote(String matiere) {
        return notes.getOrDefault(matiere, 0.0);
    }

    public double calculMoyenneGen(ArrayList<Matiere> listeMatieres) {
        double total = 0;
        int sommeCoef = 0;

        for (Matiere m : listeMatieres) {
            double n = notes.getOrDefault(m.getNom(), 0.0);
            total += n * m.getCoef();
            sommeCoef += m.getCoef();
        }

        if (sommeCoef == 0)
            return 0;

        return total / sommeCoef;
    }
}
