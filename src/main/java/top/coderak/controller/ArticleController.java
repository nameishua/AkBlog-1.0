package top.coderak.controller;

import top.coderak.entity.Article;
import top.coderak.entity.Category;
import top.coderak.entity.User;
import top.coderak.service.ArticleService;
import top.coderak.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.tautua.markdownpapers.Markdown;
import org.tautua.markdownpapers.parser.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;


@Controller
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @Resource
    private UserService userService;

   @RequestMapping("/")
    public String indexPage() {
        return "views/index";
    }

    @RequestMapping("/column/{displayName}/{category}")
    public String column(@PathVariable("category") String category,Model model,@PathVariable("displayName") String displayName) {
        model.addAttribute("articles", articleService.getArticlesByCategoryName(category));
        model.addAttribute("displayName", displayName);
        return "views/columnPage";
    }

    @RequestMapping("/detail/{id}/{category}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Article article = articleService.getArticleById(id);
        System.out.println(article.getContent());
        Markdown markdown = new Markdown();
        try {
            StringWriter out = new StringWriter();
            markdown.transform(new StringReader(article.getContent()), out);
            out.flush();
            article.setContent(out.toString());
            System.out.println("------------------");
            System.out.println(article.getContent());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        model.addAttribute("article", article);
        return "views/detail";
    }

    @RequestMapping("/my")
    public String admin(Model model) {
        List<Article> first10Article = articleService.getFirst10Article();
        //System.out.println(first10Article);
        model.addAttribute("articles",first10Article );
        return "admin/index";
    }

    @RequestMapping("/my/login")
    public String login() {
        return "admin/login";
    }

    @RequestMapping(value = "/my/dologin", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, User user, Model model) {
        //System.out.println("user.getUsername():" + user.getUsername() + ";user.getPassword():" + user.getPassword());
        if (userService.login(user.getUsername(), user.getPassword())) {
            request.getSession().setAttribute("user", user);
            model.addAttribute("user", user);
            return "redirect:/my";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "admin/login";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/my/dologin")
    public String doLogin(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("user") == null) {
            return "admin/login";
        }
        return "redirect:/my";
    }

    @RequestMapping("/my/write")
    public String write(Model model) {
        List<Category> categories = articleService.getCategories();
        categories.remove(0);
        model.addAttribute("categories", categories);
        return "admin/write";
    }

    @RequestMapping(value = "/my/write", method = RequestMethod.POST)
    public String write(Article article) {
        if (article.getId() == 0l) {
            articleService.writeBlog(article);
        } else {
            articleService.updateBlog(article);
        }
        return "redirect:/my";
    }

    @RequestMapping("/my/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        articleService.deleteArticleById(id);
        return "redirect:/my";
    }

    @RequestMapping("/my/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        Article article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "admin/write";
    }

    @RequestMapping("/uploaded")
    public @ResponseBody Map<String,Object> demo(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, HttpServletRequest request) {
        System.out.println("上传");
        Map<String,Object> resultMap = new HashMap<String,Object>();

        //String realPath = request.getServletContext().getRealPath("/assets/upload/");
        String realPath = request.getServletContext().getRealPath("/WEB-INF/static/upload/");
        //WEB-INF/static/editormd/plugins/emoji-dialog/upload
        //WEB-INF/static/upload
        System.out.println("realPath:"+realPath);

        //request.getRealPath("/")+"assets/upload/";
        // 获取上传文件路径
        //String path = request.getServletContext().getRealPath("/upload");

        // 获取原始的文件名
        String filename = file.getOriginalFilename();

        // 获取扩展文件名
        String extName = filename.substring(filename.lastIndexOf(".")+1);

        // 创建新的文件名
        Calendar calendar = Calendar.getInstance();
        String newFileName = "thenewimg"+calendar.getTimeInMillis()+"."+extName;

        File targetFile = new File(realPath, newFileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try { file.transferTo(targetFile);
            resultMap.put("success", 1);
            resultMap.put("message", "上传成功！");
           // resultMap.put("url",request.getContextPath()+"/assets/upload/"+newFileName);
            resultMap.put("url",request.getContextPath()+"/static/upload/"+newFileName);
            System.out.println("u:"+request.getContextPath()+"/static/upload/"+newFileName);
        } catch (Exception e) {
            resultMap.put("success", 0);
            resultMap.put("message", "上传失败！");
            e.printStackTrace();
        }
        return resultMap;
    }
}
