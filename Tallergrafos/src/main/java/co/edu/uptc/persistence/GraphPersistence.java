package co.edu.uptc.persistence;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import co.edu.uptc.model.Connection;
import co.edu.uptc.model.Substation;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GraphPersistence {
    private static final String FILE_PATH = "graph.json";
    private Gson gson = new Gson();

    public void saveGraph(Map<Substation, List<Connection>> graph) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(graph, writer);
        }
    }

    public Map<Substation, List<Connection>> loadGraph() throws IOException {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<Map<Substation, List<Connection>>>() {}.getType();
            return gson.fromJson(reader, type);
        }
    }
}
