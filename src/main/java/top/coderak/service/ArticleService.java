package top.coderak.service;

import top.coderak.dao.ArticleDao;
import top.coderak.entity.Article;
import top.coderak.entity.Category;
import top.coderak.entity.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class ArticleService {
    @Resource
    private ArticleDao articleDao;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public Article getArticleById(Long id) {
        Article article = articleDao.getArticleById(id);
        article.setCategory(articleDao.getCategoryById(article.getCategoryId()).getDisplayName());
        return article;
    }

    public List<Article> getFirst10Article() {
        //System.out.println("getFirst10Article");
        return articleDao.getFirst10Article();
    }


    public Page getFirst10ArticlePage(Integer currentPage, Integer categoryId) {
       /* System.out.println(currentPage);
        System.out.println("getFirst10ArticlePage");
        return articleDao.getFirst10ArticlePage();*/
        System.out.println("findSuccessDemoByPage");
        Page page = new Page();
        page.setPageSize(2);
        int rows = articleDao.findRows(categoryId);
        System.out.println("rows:"+rows);
        int pages = rows % page.getPageSize() == 0 ? rows / page.getPageSize() : rows / page.getPageSize() + 1;
        if(currentPage <= 1){
            currentPage = 1;
        }
        if(currentPage >= pages){
            currentPage = pages;
        }
        int startRow = (currentPage - 1) * page.getPageSize();

        page.setCurrentPage(currentPage);
        page.setPages(pages);
        page.setRows(rows);
        page.setStartRow(startRow);
        page.setCategoryId(categoryId);

        Integer startRow1 = page.getStartRow();
        Integer pagePageSize = page.getPageSize();
        System.out.println("起始行:"+startRow1+"....."+"每页条数:"+pagePageSize);
        List<Article>successDemos = articleDao.findSuccessDemoByPage(page);
        page.setList(successDemos);

        return page;
    }

    public List<Category> getCategories() {
        return articleDao.getCategories();
    }

    public void writeBlog(Article article) {
        Long categoryId = articleDao.getCategoryIdByName(article.getCategory());
        article.setCategoryId(categoryId);
        article.setDate(sdf.format(new Date()));
        if (article.getSummary() == null || "".equals(article.getSummary())) {
            if (article.getContent().length() > 20) {
                article.setSummary(article.getContent().substring(0, 20));
            } else {
                article.setSummary(article.getContent().substring(0, article.getContent().length()));
            }
        }
        articleDao.writeBlog(article);
    }

    public void deleteArticleById(Long id) {
        articleDao.deleteArticleById(id);
    }

    public void updateBlog(Article article) {
        article.setDate(sdf.format(new Date()));
        if (article.getSummary() == null || "".equals(article.getSummary())) {
            if (article.getContent().length() > 20) {
                article.setSummary(article.getContent().substring(0, 20));
            } else {
                article.setSummary(article.getContent().substring(0, article.getContent().length()));
            }
        }
        articleDao.updateArticleById(article);
    }

    public List<Article> getArticlesByCategoryName(String name) {
        Long categoryId = articleDao.getCategoryIdByName(name);
        List<Article> articles = articleDao.getArticlesByCategoryName(categoryId);
        return articles;
    }

    public Page getFirstpageArticlePage(Integer currentPage) {
         /* System.out.println(currentPage);
        System.out.println("getFirst10ArticlePage");
        return articleDao.getFirst10ArticlePage();*/
        System.out.println("findSuccessDemoByPage");
        Page page = new Page();
        page.setPageSize(2);
        int rows = articleDao.findallRows();
        System.out.println("rows:"+rows);
        int pages = rows % page.getPageSize() == 0 ? rows / page.getPageSize() : rows / page.getPageSize() + 1;
        if(currentPage <= 1){
            currentPage = 1;
        }
        if(currentPage >= pages){
            currentPage = pages;
        }
        int startRow = (currentPage - 1) * page.getPageSize();

        page.setCurrentPage(currentPage);
        page.setPages(pages);
        page.setRows(rows);
        page.setStartRow(startRow);
        //page.setCategoryId(categoryId);

        Integer startRow1 = page.getStartRow();
        Integer pagePageSize = page.getPageSize();
        //System.out.println("page:"+page);
        System.out.println("起始行:"+startRow1+"....."+"每页条数:"+pagePageSize);
        List<Article>successDemos = articleDao.findallSuccessDemoByPage(page);
        page.setList(successDemos);

        return page;
    }
}
