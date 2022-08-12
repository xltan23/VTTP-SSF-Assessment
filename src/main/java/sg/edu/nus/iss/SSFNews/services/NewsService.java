package sg.edu.nus.iss.SSFNews.services;

import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.SSFNews.models.News;

@Service
public class NewsService {
    
    private static final String URL = "https://min-api.cryptocompare.com/data/v2/news/";

    @Value("${API_KEY}")
    private String key;

    public List<News> getArticles() {
        String payload;

        try {
            System.out.println("Obtaining news articles from CryptoCompare");

            String url = UriComponentsBuilder.fromUriString(URL)
                .queryParam("api_key", key)
                .toUriString();

            // Create GET request
            RequestEntity<Void> req = RequestEntity.get(url).build();

            // Make the call to Cryptocompare
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> resp;

            resp = restTemplate.exchange(req, String.class);
            payload = resp.getBody();
            System.out.printf("Payload: %s", payload);

        } catch (Exception ex) {
            System.err.printf("Error: %s\n", ex.getMessage());
            return Collections.emptyList();
        }

        // Convert payload to JSON Object
        StringReader r = new StringReader(payload);
        JsonReader jr = Json.createReader(r);
        // Read payload as a JSON Object
        JsonObject newsResult = jr.readObject();
        //Retrieve the array for news, where each element is an article
        JsonArray articles = newsResult.getJsonArray("Data");
        System.out.println("The number of articles is:" + articles.size());
        List<News> newsList = new LinkedList<>();
        for (int i = 0; i < articles.size(); i++) {
            // For each article, obtain a JSON object
            JsonObject jo = articles.getJsonObject(i);
            // Convert JSON Object into News Object and add to list
            newsList.add(News.create(jo));
        }
        return newsList;
    } 

    public void saveArticles(List<News> articles) {
        
    }
}
