package sg.edu.nus.iss.SSFNews.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
