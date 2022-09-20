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
        List<Play> plays = ReadFileJson.plays(urlFilePlaysJson);
        Invoice invoice = ReadFileJson.invoice(urlFileInvoicesJson);

        System.out.println(Statement.statement(invoice, plays));
    }
}