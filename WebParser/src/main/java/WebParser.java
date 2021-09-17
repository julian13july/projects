import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

/**
 * WebParser - parser information about products of the given web page of the online store.
 */
public class WebParser {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.aboutyou.de/maenner/")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/92.0.4515.107 Safari/537.36")
                .referrer("http://www.google.com")
                .get();
        Elements newsHeadlines = doc.select(".sc-nuloix-0.kPrwII");
        int countProducts = 0;
        for (Element headline : newsHeadlines) {
            String productName = headline.select(".sc-d98d3j-0.jlNXKl img").attr("alt");
            String brand = headline.select(".sc-g6odp7-1.eNGsWF p").text();
            String color = headline.select(".sc-g6odp7-8.jrXTlT ul li").attr("color");
            String size = headline.select(".sc-g6odp7-8.jrXTlT span").text();
            String price = headline.select(".sc-g6odp7-7.kdVFBD span").text();
            String id = headline.id();
            countProducts++;
            System.out.println("Product {" +
                    "\n\t productName = '" + productName + '\'' +
                    ",\n\t brand = '" + brand + '\'' +
                    ",\n\t color = '" + color + '\'' +
                    ",\n\t size = '" + size + '\'' +
                    ",\n\t price = '" + price + '\'' +
                    ",\n\t id = '" + id + '\'' +
                    "\n}\n");
        }
        System.out.println("Products: " + countProducts);
    }
}