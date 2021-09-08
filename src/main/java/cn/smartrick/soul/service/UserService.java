package cn.smartrick.soul.service;

import cn.smartrick.soul.entity.Cloud;
import cn.smartrick.soul.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Date;
import java.util.List;

public interface UserService {
    //用户注册
    public abstract boolean register(User user);
    //用户登录
    public abstract User login(String username, String password);
    //用户信息更改
    public abstract boolean modifyUser(User user);
    public abstract boolean deleteUser(Integer id);
    public abstract Page<User> queryAllUser(Page<User> page);
    public abstract Page<User> queryAllUserSimple(Page<User> page);

    //粉丝数据操作
    public abstract Page<User> queryFollows(Integer uid,Page<User> page);//查询某用户的全部关注
    public abstract Page<User> queryFans(Integer uid,Page<User> page);//查询某用户的全部粉丝
    public abstract boolean addFollow(Integer uid, Integer fid);//为某个用户新增关注
    public abstract boolean deleteFollow(Integer uid, Integer fid);//为某个用户删除关注

    boolean opRoot(Integer uid);
}
