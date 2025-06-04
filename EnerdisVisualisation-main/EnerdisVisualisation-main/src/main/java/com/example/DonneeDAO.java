package com.example;

import java.sql.*;
import java.util.*;

public class DonneeDAO {

    public static List<Donnee> getAllDonnees() {
        List<Donnee> liste = new ArrayList<>();

        try {
            Connection conn = Database.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM donnees ORDER BY date ASC");

            while (rs.next()) {
                Donnee donnee = new Donnee(
                        rs.getInt("id"),
                        rs.getString("date"),
                        rs.getFloat("valeur"),
                        rs.getString("unite")
                );
                liste.add(donnee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    public static List<Donnee> getAllDonneesByUnit(String unit) {
        List<Donnee> liste = new ArrayList<>();

        try {

            Connection conn = Database.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM donnees WHERE unite = \"" + unit + "\" ORDER BY date ASC");

            while (rs.next()) {
                Donnee donnee = new Donnee(
                        rs.getInt("id"),
                        rs.getString("date"),
                        rs.getFloat("valeur"),
                        rs.getString("unite")
                );
                liste.add(donnee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

}
