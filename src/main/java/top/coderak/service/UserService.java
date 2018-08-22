package top.coderak.service;

import top.coderak.dao.UserDao;
import top.coderak.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public boolean login(String username, String password) {
        User user = userDao.getUser(username, password);
        if (user == null) {
            return false;
        }else{
            return true;
        }
    }
}
