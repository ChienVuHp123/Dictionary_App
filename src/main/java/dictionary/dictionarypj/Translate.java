package dictionary.dictionarypj;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Translate {

    /**.
    * Method có 3 đối số : + langFrom: Ngôn ngữ của text đầu vào
                           + langTo: Ngôn ngữ của text dịch 
                           + word: là văn bản cần dịch
    */
    public static String callUrlAndParseResult(String langFrom, String langTo,
                                        String word) throws Exception {

        // Url được tạo ra để truy cập được API 
        String url = "https://translate.googleapis.com/translate_a/single?" +
                "client=gtx&" +
                "sl=" + langFrom +
                "&tl=" + langTo +
                "&dt=t&q=" + URLEncoder.encode(word, StandardCharsets.UTF_8);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection(); // Kết nối URL với API
        con.setRequestProperty("User-Agent", "Mozilla/5"); // Gửi request

        // BufferedReader để đọc những response từ máy chủ
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        String inputLine; // biến này để lưu trữ từng dòng String
        StringBuffer response = new StringBuffer();

        // Vòng lặp While sẽ kết thúc khi không còn dòng String nào nữa
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return parseResult(response.toString());
    }

    public static String callUrlAndParseResult2(String langFrom, String langTo,
                                         String word) throws Exception {

        String url = "https://translate.googleapis.com/translate_a/single?" +
                "client=gtx&" +
                "sl=" + langFrom +
                "&tl=" + langTo +
                "&dt=t&q=" + URLEncoder.encode(word, StandardCharsets.UTF_8);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return parseResult(response.toString());
    }

    /**.
    * Phân tích String inputJson thành JsonArray và lấy phần tử đầu tiên của mảng JsonArray
    */
    private static String parseResult(String inputJson) throws Exception {
        JSONArray jsonArray = new JSONArray(inputJson);
        JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
        JSONArray jsonArray3 = (JSONArray) jsonArray2.get(0);

        return jsonArray3.get(0).toString();
    }


}
