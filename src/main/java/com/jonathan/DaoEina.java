package com.jonathan;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class DaoEina {
    public void add(ObjEina eina) {
        JSONArray eines = loadEines();
        JSONObject einaJson = new JSONObject();
        einaJson.put("id", eina.getId());
        einaJson.put("nom", eina.getNom());
        einaJson.put("any", eina.getAny());
        einaJson.put("llenguatges", eina.getLlenguatges());

        eines.put(einaJson);
        saveEines(eines);
    }

    public void update(int id, ObjEina eina) {
        JSONArray eines = loadEines();
        for (int i = 0; i < eines.length(); i++) {
            JSONObject einaJson = eines.getJSONObject(i);
            if (einaJson.getInt("id") == id) {
                einaJson.put("nom", eina.getNom());
                einaJson.put("any", eina.getAny());
                einaJson.put("llenguatges", eina.getLlenguatges());
                break;
            }
        }
        saveEines(eines);
    }

    public void delete(int id) {
        JSONArray eines = loadEines();
        for (int i = 0; i < eines.length(); i++) {
            JSONObject einaJson = eines.getJSONObject(i);
            if (einaJson.getInt("id") == id) {
                eines.remove(i);
                break;
            }
        }
        saveEines(eines);
    }

    public void setLlenguatgesAdd(int id, int llenguatgeId) {
        JSONArray eines = loadEines();
        for (int i = 0; i < eines.length(); i++) {
            JSONObject einaJson = eines.getJSONObject(i);
            if (einaJson.getInt("id") == id) {
                JSONArray llenguatges = einaJson.getJSONArray("llenguatges");
                llenguatges.put(llenguatgeId);
                break;
            }
        }
        saveEines(eines);
    }

    public void setLlenguatgesDelete(int id, int llenguatgeId) {
        JSONArray eines = loadEines();
        for (int i = 0; i < eines.length(); i++) {
            JSONObject einaJson = eines.getJSONObject(i);
            if (einaJson.getInt("id") == id) {
                JSONArray llenguatges = einaJson.getJSONArray("llenguatges");
                for (int j = 0; j < llenguatges.length(); j++) {
                    if (llenguatges.getInt(j) == llenguatgeId) {
                        llenguatges.remove(j);
                        break;
                    }
                }
                break;
            }
        }
        saveEines(eines);
    }

    public void print() {
        JSONArray eines = loadEines();
        for (int i = 0; i < eines.length(); i++) {
            JSONObject einaJson = eines.getJSONObject(i);
            System.out.println(new ObjEina(
                    einaJson.getInt("id"),
                    einaJson.getString("nom"),
                    einaJson.getInt("any"),
                    toArrayList(einaJson.getJSONArray("llenguatges"))
            ));
        }
    }

    private JSONArray loadEines() {
        File file = new File(Main.einesPath);
        JSONArray eines = new JSONArray();
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
        return eines;
    }

    private void saveEines(JSONArray eines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Main.einesPath))) {
            writer.write(eines.toString(2)); // Indentación de 2 espacios
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
