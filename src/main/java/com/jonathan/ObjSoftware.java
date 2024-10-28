package com.jonathan;

import java.util.ArrayList;

public class ObjSoftware {
    private int id;
    private String nom;
    private int any;
    private ArrayList<Integer> llenguatges;

    public ObjSoftware(int id, String nom, int any, ArrayList<Integer> llenguatges) {
        this.id = id;
        this.nom = nom;
        this.any = any;
        this.llenguatges = llenguatges;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getAny() {
        return any;
    }

    public ArrayList<Integer> getLlenguatges() {
        return llenguatges;
    }

    @Override
    public String toString() {
        return "Software: " + id + " " + nom + ", " + any + " - " + llenguatges.toString();
    }
}
