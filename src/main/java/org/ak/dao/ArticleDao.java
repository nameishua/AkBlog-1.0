package org.ak.dao;


import org.apache.ibatis.annotations.Param;
import org.ak.entity.Article;
import org.ak.entity.Category;
import org.ak.entity.Page;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticleDao {
    public Article getArticleById(@Param("id") Long id);

    public List<Article> getFirst10Article();

    public int  findRows(Integer categoryId);

    public List<Article> findSuccessDemoByPage(Page page);

    public List<Article> getArticlesByCategoryName(Long categoryId);

    public List<Category> getCategories();

    public void writeBlog(Article article);

    public Long getCategoryIdByName(String name);

    public void deleteArticleById(Long id);

    public void updateArticleById(Article article);

    public Category getCategoryById(Long id);

    int findallRows();

    List<Article> findallSuccessDemoByPage(Page page);
}
