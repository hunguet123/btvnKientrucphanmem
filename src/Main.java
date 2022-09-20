import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        String urlFilePlaysJson = "./res/plays.json";
        String urlFileInvoicesJson = "./res/invoices.json";
        List<Play> plays = new ArrayList<>();

        Invoice invoice = new Invoice();

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(urlFilePlaysJson)) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            List<String> arrKey = jsonObject.keySet().stream().toList();

            for (String key: arrKey) {
                JSONObject jsonPlay = (JSONObject) jsonObject.get(key);
                String name = jsonPlay.get("name").toString();
                String type = jsonPlay.get("type").toString();
                plays.add(new Play(key,name, type));
            }
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }

        try (FileReader reader = new FileReader(urlFileInvoicesJson)) {
            Object invoiceObject = jsonParser.parse(reader);
            JSONArray invoiceJsonArray = (JSONArray) invoiceObject;
            for (Object i: invoiceJsonArray) {
                List<Performance> performances = new ArrayList<>();
                JSONObject invoiceJson = (JSONObject) i;
                String customer = invoiceJson.get("customer").toString();
                JSONArray performancesJson = (JSONArray) invoiceJson.get("performances");
                for (Object performance: performancesJson) {
                    JSONObject performanceJson = (JSONObject) performance;
                    String playID = performanceJson.get("playID").toString();
                    int audience = Integer.parseInt(performanceJson.get("audience").toString());
                    performances.add(new Performance(playID, audience));
                }
                invoice.setCustomer(customer);
                invoice.setPerformances(performances);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(Statement.statement(invoice, plays));
    }
}