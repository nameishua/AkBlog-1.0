package top.coderak.controller;

import top.coderak.entity.Page;
import top.coderak.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/page")
public class PageController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Page index(Integer currentPage , Integer categoryId) {

        if (!(categoryId==1)){
            System.out.println("I am pagecontroller");
            //System.out.println("currentPage:"+currentPage+"...."+"categoryId:"+categoryId);
            if (currentPage == null){
                currentPage = 1;
            }
            Page page = articleService.getFirst10ArticlePage(currentPage,categoryId);
            return page;
            /*List list = page.getList();
            List<Article> articles = new ArrayList<Article>();

            for (Object o : list) {
                Article article = (Article)o;
                article.setCurrentPage(currentPage);
                articles.add(article);

                System.out.println(article.getTitle());
            }
            for (Article ar : articles) {
                System.out.println(ar.getCurrentPage());
            }
            System.out.println("PageController..:");
            return articles;*/
        }else {
            //System.out.println("I am pagecontroller");
            //System.out.println("currentPage:" + currentPage + "...." + "categoryId:" + categoryId);
            if (currentPage == null) {
                currentPage = 1;
            }
            Page page = articleService.getFirstpageArticlePage(currentPage);
            return  page;
            /*List list = page.getList();

            List<Article> articles = new ArrayList<Article>();

            for (Object o : list) {
                Article article = (Article) o;
                article.setCurrentPage(currentPage);
                articles.add(article);

                System.out.println(article.getTitle());
            }
            for (Article ar : articles) {
                System.out.println(ar.getCurrentPage());
            }
            System.out.println("PageController..:");
            return articles;*/
        }

    }
}
