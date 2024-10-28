package com.jonathan;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class DaoLlenguatge {
    public void add(ObjLlenguatge llenguatge) {
        JSONArray llenguatges = loadLlenguatges();
        JSONObject llenguatgeJson = new JSONObject();
        llenguatgeJson.put("id", llenguatge.getId());
        llenguatgeJson.put("nom", llenguatge.getNom());
        llenguatgeJson.put("any", llenguatge.getAny());
        llenguatgeJson.put("dificultat", llenguatge.getDificultat());
        llenguatgeJson.put("popularitat", llenguatge.getPopularitat());

        llenguatges.put(llenguatgeJson);
        saveLlenguatges(llenguatges);
    }

    public void update(int id, ObjLlenguatge llenguatge) {
        JSONArray llenguatges = loadLlenguatges();
        for (int i = 0; i < llenguatges.length(); i++) {
            JSONObject llenguatgeJson = llenguatges.getJSONObject(i);
            if (llenguatgeJson.getInt("id") == id) {
                llenguatgeJson.put("nom", llenguatge.getNom());
                llenguatgeJson.put("any", llenguatge.getAny());
                llenguatgeJson.put("dificultat", llenguatge.getDificultat());
                llenguatgeJson.put("popularitat", llenguatge.getPopularitat());
                break;
            }
        }
        saveLlenguatges(llenguatges);
    }

    public void delete(int id) {
        JSONArray llenguatges = loadLlenguatges();
        for (int i = 0; i < llenguatges.length(); i++) {
            JSONObject llenguatgeJson = llenguatges.getJSONObject(i);
            if (llenguatgeJson.getInt("id") == id) {
                llenguatges.remove(i);
                break;
            }
        }
        saveLlenguatges(llenguatges);
    }

    public void print() {
        JSONArray llenguatges = loadLlenguatges();
        for (int i = 0; i < llenguatges.length(); i++) {
            JSONObject llenguatgeJson = llenguatges.getJSONObject(i);
            System.out.println(new ObjLlenguatge(
                    llenguatgeJson.getInt("id"),
                    llenguatgeJson.getString("nom"),
                    llenguatgeJson.getInt("any"),
                    llenguatgeJson.getString("dificultat"),
                    llenguatgeJson.getInt("popularitat")
            ));
        }
    }

    public void setNom(int id, String nom) {
        JSONArray llenguatges = loadLlenguatges();
        for (int i = 0; i < llenguatges.length(); i++) {
            JSONObject llenguatgeJson = llenguatges.getJSONObject(i);
            if (llenguatgeJson.getInt("id") == id) {
                llenguatgeJson.put("nom", nom);
                break;
            }
        }
        saveLlenguatges(llenguatges);
    }

    public void setAny(int id, int any) {
        JSONArray llenguatges = loadLlenguatges();
        for (int i = 0; i < llenguatges.length(); i++) {
            JSONObject llenguatgeJson = llenguatges.getJSONObject(i);
            if (llenguatgeJson.getInt("id") == id) {
                llenguatgeJson.put("any", any);
                break;
            }
        }
        saveLlenguatges(llenguatges);
    }

    public void setDificultat(int id, String dificultat) {
        JSONArray llenguatges = loadLlenguatges();
        for (int i = 0; i < llenguatges.length(); i++) {
            JSONObject llenguatgeJson = llenguatges.getJSONObject(i);
            if (llenguatgeJson.getInt("id") == id) {
                llenguatgeJson.put("dificultat", dificultat);
                break;
            }
        }
        saveLlenguatges(llenguatges);
    }

    public void setPopularitat(int id, int popularitat) {
        JSONArray llenguatges = loadLlenguatges();
        for (int i = 0; i < llenguatges.length(); i++) {
            JSONObject llenguatgeJson = llenguatges.getJSONObject(i);
            if (llenguatgeJson.getInt("id") == id) {
                llenguatgeJson.put("popularitat", popularitat);
                break;
            }
        }
        saveLlenguatges(llenguatges);
    }

    private JSONArray loadLlenguatges() {
        File file = new File(Main.llenguatgesPath);
        JSONArray llenguatges = new JSONArray();
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
        return llenguatges;
    }

    private void saveLlenguatges(JSONArray llenguatges) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Main.llenguatgesPath))) {
            writer.write(llenguatges.toString(2)); // Indentación de 2 espacios
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
