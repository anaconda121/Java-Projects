import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class Toilet {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://www.walmart.com/search/?query=toilet%20paper").userAgent("chrome/81.0.4044.92").get();
            String docStr  = doc.toString();

            Elements temp = doc.select("div.product-price-with-fulfillment");
            int i = 0;
            for (Element ToiletPrices : temp) {
                i++;
                final String answer = ToiletPrices.getElementsByTag("span").first().text();
                if(answer.equals("Current Price In-store purchase only")){
                    System.out.print("");
                }
                System.out.println(i + ". " + answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}