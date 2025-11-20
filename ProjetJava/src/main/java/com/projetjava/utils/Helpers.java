package com.projetjava.utils;

public class Helpers {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String formatName(String nom, String prenom) {
        return prenom + " " + nom;
    }

    public static boolean isValidNote(double note) {
        return note >= 0 && note <= 20; // Assuming the note is out of 20
    }
}