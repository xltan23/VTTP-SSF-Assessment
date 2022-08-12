package sg.edu.nus.iss.SSFNews.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.SSFNews.models.News;
import sg.edu.nus.iss.SSFNews.services.NewsService;

@Controller
@RequestMapping(path = "/news")
public class NewsController {
    
    @Autowired
    private NewsService nSvc;

    @GetMapping
    public String getNews(Model model) {
        List<News> newsList = nSvc.getArticles();
        model.addAttribute("newsList", newsList);
        return "news";
    }

    @PostMapping(value = "/articles", consumes="application/x-www-form-urlencoded", produces="text/html")
    public String saveArticles(@RequestBody MultiValueMap<String,String> form, Model model) {
        // For single article saving
        News article = new News();
        article.setId(form.getFirst("id"));
        article.setPublishedOn(Integer.parseInt(form.getFirst("published")));
        article.setTitle(form.getFirst("title"));
        article.setUrl(form.getFirst("url"));
        article.setImageurl(form.getFirst("imageurl"));
        article.setBody(form.getFirst("body"));
        article.setTags(form.getFirst("tags"));
        article.setCategories(form.getFirst("categories"));
        nSvc.saveArticle(article);
        return "redirect:/news";
    }

}
