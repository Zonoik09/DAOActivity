package com.jonathan;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class DaoSoftware {
    public void add(ObjSoftware software) {
        JSONArray softwares = loadSoftwares();
        JSONObject softwareJson = new JSONObject();
        softwareJson.put("id", software.getId());
        softwareJson.put("nom", software.getNom());
        softwareJson.put("any", software.getAny());
        softwareJson.put("llenguatges", software.getLlenguatges());

        softwares.put(softwareJson);
        saveSoftwares(softwares);
    }

    public void delete(int id) {
        JSONArray softwares = loadSoftwares();
        for (int i = 0; i < softwares.length(); i++) {
            JSONObject softwareJson = softwares.getJSONObject(i);
            if (softwareJson.getInt("id") == id) {
                softwares.remove(i);
                break;
            }
        }
        saveSoftwares(softwares);
    }

    public void print() {
        JSONArray softwares = loadSoftwares();
        for (int i = 0; i < softwares.length(); i++) {
            JSONObject softwareJson = softwares.getJSONObject(i);
            System.out.println(new ObjSoftware(
                    softwareJson.getInt("id"),
                    softwareJson.getString("nom"),
                    softwareJson.getInt("any"),
                    toArrayList(softwareJson.getJSONArray("llenguatges"))
            ));
        }
    }

    private JSONArray loadSoftwares() {
        File file = new File(Main.softwarePath);
        JSONArray softwares = new JSONArray();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return new JSONArray(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return softwares;
    }

    private void saveSoftwares(JSONArray softwares) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Main.softwarePath))) {
            writer.write(softwares.toString(2)); // Indentación de 2 espacios
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Integer> toArrayList(JSONArray jsonArray) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            arrayList.add(jsonArray.getInt(i));
        }
        return arrayList;
    }
}
