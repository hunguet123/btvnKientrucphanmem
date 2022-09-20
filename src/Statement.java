import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Statement {

    private static Play playFor(Performance performance, List<Play> plays) {
        for(int i = 0; i < plays.size(); i++) {
            if(performance.getPlayID().equals(plays.get(i).getPlayID())) {
                return plays.get(i);
            }
        }
        return null;
    }

    private static int calculateAmount(Performance performance, Play playTemp) {
        int thisAmount = 0;
        switch (playTemp.getType()) {
            case "tragedy":
                thisAmount = 40000;
                if (performance.getAudience() > 30) {
                    thisAmount += 1000 * (performance.getAudience() - 30);
                }
                break;
            case "comedy":
                thisAmount = 30000;
                if (performance.getAudience() > 20) {
                    thisAmount += 10000 + 500 * (performance.getAudience() - 20);
                }
                thisAmount += 300 * performance.getAudience();
                break;
            default:
                throw new Error("unknow type: " + playTemp.getType() );
        }

        return thisAmount;
    }

    public static String statement(Invoice invoice, List<Play> plays) {
        int totalAmount = 0;
        Double volumeCredits = 0.0;
        String result = "Statement for " + invoice.getCustomer() + "\n";

        NumberFormat formatter1 = NumberFormat.getCurrencyInstance(new Locale("en", "US"));

        for (int i = 0; i < invoice.getPerformances().size(); i++) {
            Performance performanceTemp = invoice.getPerformances().get(i);

            Play playTemp = playFor(performanceTemp, plays);
            int thisAmount = calculateAmount(performanceTemp, playTemp);

            volumeCredits += Math.max(performanceTemp.getAudience() - 30, 0);

            if("comedy" == playTemp.getType()) {
                volumeCredits += Math.floor(performanceTemp.getAudience() / 5);
            }

            result += playTemp.getName() + " " + formatter1.format(performanceTemp.getAudience()) + "\n";
            totalAmount += thisAmount;
        }
        result += "Amount owed is " + formatter1.format(totalAmount/100) + "\n";
        result += "You earned " + volumeCredits + " credits";

        return result;
    }

}
