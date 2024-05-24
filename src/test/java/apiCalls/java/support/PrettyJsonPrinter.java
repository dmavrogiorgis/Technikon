package apiCalls.java.support;

public class PrettyJsonPrinter {

    public static void printJson(String pjson) {
        pjson = pjson.trim().substring(1, pjson.length() - 1);
        String[] keyValuePairs = pjson.split(",");
        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split(":");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            System.out.println(key + ": " + value);
        }
    }
}
