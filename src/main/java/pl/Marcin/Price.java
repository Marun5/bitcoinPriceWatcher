package pl.Marcin;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

public class Price {

    private float price;
    private float min = 16455;
    private float max = 16466;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public Price() {
    }

    public void getPrice(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject obj = new JSONObject(response.body());
        setPrice(obj.getJSONObject("bpi").getJSONObject("USD").getFloat("rate_float"));

        System.out.println(LocalDateTime.now());
        System.out.println("USD: "+getPrice()+"\n");
    }
    public boolean isHigher() {
        return getPrice() > getMax();
    }
    public boolean isLower() {
        return getPrice() < getMin();
    }

    public String msgLower() {
        return "Bitcoin price: "+getPrice()+"$ is lower than "+getMin()+"$";
    }
    public String msgHigher() {
        return "Bitcoin price: "+getPrice()+"$ is higher than "+getMax()+"$";
    }
}
