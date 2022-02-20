package service;

import entity.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class JSONConverter {

    //    private final static String PATH_JSON = "C:\\Users\\ANYA\\IdeaProjects\\front-end\\Andersen_MailSender\\output.json";
//    public static List<User> listUser = new ArrayList<>();

    public List<User> parse() throws IOException {
        List<User> listUser = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
//        File file = new File(PATH_JSON);

//        try (FileReader fileReader = new FileReader(file)) {
            try {
//                JSONObject object = (JSONObject) jsonParser.parse(fileReader);
        JSONObject object = (JSONObject) jsonParser.parse(JsoupParser.parsURL());
        JSONArray jsonArray = (JSONArray) object.get("Players_data");
        for (Object o :
                jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            String user_name = (String) jsonObject.get("user_name");
            double spend_time = (Double) jsonObject.get("spend_time");
            String activities = (String) jsonObject.get("activities");
            User user = new User(user_name, String.valueOf(spend_time), activities);
            listUser.add(user);
            System.out.println(listUser);
        }
        Collections.sort(listUser);

            } catch (Exception e) {
                e.printStackTrace();
            }
        return listUser;
    }
}
