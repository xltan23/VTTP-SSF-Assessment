package sg.edu.nus.iss.SSFNews.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class News {
    private String id;
    private Integer publishedOn;
    private String title;
    private String url;
    private String imageurl;
    private String body;
    private String tags;
    private String categories;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getPublishedOn() {
        return publishedOn;
    }
    public void setPublishedOn(Integer publishedOn) {
        this.publishedOn = publishedOn;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImageurl() {
        return imageurl;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getCategories() {
        return categories;
    }
    public void setCategories(String categories) {
        this.categories = categories;
    }

    public static News create(JsonObject jo) {
        News n = new News();
        n.setId(jo.getString("id"));
        n.setPublishedOn(jo.getInt("published_on"));
        n.setTitle(jo.getString("title"));
        n.setUrl(jo.getString("url"));
        n.setImageurl(jo.getString("imageurl"));
        n.setBody(jo.getString("body"));
        n.setTags(jo.getString("tags"));
        n.setCategories(jo.getString("categories"));
        return n;
    }

    public static News create(String jsonString) {
        StringReader reader = new StringReader(jsonString);
        JsonReader jr = Json.createReader(reader);
        return create(jr.readObject());
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("id", id)
                .add("published_on", publishedOn)
                .add("title", title)
                .add("url", url)
                .add("imageurl", imageurl)
                .add("body", body)
                .add("tags", tags)
                .add("categories", categories)
                .build();
    }

    
}
