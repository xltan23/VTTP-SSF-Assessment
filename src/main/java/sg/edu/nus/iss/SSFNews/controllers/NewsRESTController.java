package sg.edu.nus.iss.SSFNews.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.SSFNews.models.News;
import sg.edu.nus.iss.SSFNews.services.NewsService;

@RestController
@RequestMapping(path = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsRESTController {
    
    @Autowired
    private NewsService nSvc;

    @GetMapping(value = "{nid}")
    public ResponseEntity<String> getNewsById(@PathVariable String nid) {
        Optional<News> opt = nSvc.get(nid);

        // If empty, build a JSON Object to be shown 
        if (opt.isEmpty()) {
            JsonObject error = Json.createObjectBuilder()
                .add("error", "Cannot find news article %s".formatted(nid))
                .build();
            // Convert to JSON String
            String payload = error.toString();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(payload);
        }
        // If available, retrieve news from Box
        News article = opt.get();
        return ResponseEntity.ok(article.toJson().toString());
    }
}
