package cn.web.user.service;

import cn.web.user.dao.UserDao;
import cn.web.user.domain.User;

/**
 * Created by Taeyeon-Serenity on 2017/3/7.
 */
/*User的业务逻辑层*/
public class UserService {
    private UserDao userDao=new UserDao();

    /*注册功能*/
    public void regist(User user) throws UserException{
        /*1.使用用户名去查询，如果返回null，完成添加
        *  2.如果返回的不是null，抛出异常*/
        User _user=userDao.findByUsername(user.getUsername());
        if(_user !=null ) throw new UserException("用户名"+user.getUsername()+"已经注册过了!");
        userDao.add(user );
    }
    /*登陆功能*/
    public User login(User form)throws UserException{
        /*1.使用form的username进行查询，得到User*/
        User user=userDao.findByUsername(form.getUsername());
        /*2.如果返回为null，说明用户名不存在，抛出异常*/
        if(user == null) throw new UserException("用户名不存在!");
        /*3.比较user的password和form的password，如果不同，抛出异常，异常信息为“密码错误”*/
        if(!form.getPassword().equals(user.getPassword())){
            throw new UserException("密码错误!");
        }
        /*返回数据库中查询出来的user，而不是form，因为form中只有用户名和密码，而user是所有用户信息!*/

        return null;
    }/**/
}
