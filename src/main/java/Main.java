import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        final String jsonFileName = "data.json";

        String json = readString(jsonFileName);
        List<Employee> list = jsonToList(json);

        if (list.size() > 0) {
            for (Employee employee : list) {
                System.out.println(employee);
            }
        } else {
            System.out.println("No objects in list!");
        }
    }

    public static String readString(String jsonFileName) {

        String s;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(jsonFileName))) {
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static List<Employee> jsonToList(String json) {

        List<Employee> list = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(json);
            JSONArray jsonArray = (JSONArray) obj;

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            for (Object oneObject : jsonArray) {

                JSONObject jsonObject = (JSONObject) oneObject;
                Employee employee = gson.fromJson(String.valueOf(jsonObject), Employee.class);
                list.add(employee);

            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }
}