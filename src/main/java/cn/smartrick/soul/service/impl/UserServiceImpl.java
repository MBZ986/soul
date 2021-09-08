package cn.smartrick.soul.service.impl;

import cn.smartrick.soul.entity.Cloud;
import cn.smartrick.soul.entity.User;
import cn.smartrick.soul.mapper.CloudMapper;
import cn.smartrick.soul.mapper.UserMapper;
import cn.smartrick.soul.service.UserService;
import cn.smartrick.soul.util.StringUtil;
import cn.smartrick.soul.util.UploadFileQiniu;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CloudMapper cloudMapper;
    @Autowired
    private UploadFileQiniu fileQiniu;


    @Override
    public boolean register(User user) {
        if (userMapper.insert(user) > 0) {
            log.info("注册后的用户ID：" + user.getId());
            Cloud cloud = new Cloud();
            cloud.setUserId(user.getId());
            return cloudMapper.insert(cloud) > 0;
        }

        return false;
    }

    @Override
    public User login(String username, String password) {
        return parse(userMapper.selectOne(new QueryWrapper<User>().eq("username", username).eq("password", password)));
    }

    @Override
    public boolean modifyUser(User user) {
        return userMapper.updateById(user) > 0;
    }

    @Transactional
    @Override
    public boolean deleteUser(Integer id) {
        if (userMapper.deleteById(id) > 0) {
            return cloudMapper.delete(new QueryWrapper<Cloud>().eq("user_id", id)) > 0;
        }
        return false;
    }

    @Override
    public Page<User> queryAllUser(Page<User> page) {
        Page<User> userPage = userMapper.selectPage(page, new QueryWrapper<>());
        return parse(userPage);
    }

    @Override
    public Page<User> queryAllUserSimple(Page<User> page) {
        return parse(page.setRecords(userMapper.selectAllUserSimple(
                (page.getCurrent() - 1) * page.getSize(), page.getSize())
        ));
    }

    @Override
    public Page<User> queryFollows(Integer uid, Page<User> page) {
        return parse(page.setRecords(userMapper.selectFollows(
                uid, (page.getCurrent() - 1) * page.getSize(), page.getSize())
        ));
    }

    @Override
    public Page<User> queryFans(Integer uid, Page<User> page) {
        return parse(page.setRecords(userMapper.selectFans(
                uid, (page.getCurrent() - 1) * page.getSize(), page.getSize())
        ));
    }

    @Override
    public boolean addFollow(Integer uid, Integer fid) {
        return userMapper.insertFollow(uid, fid);
    }

    @Override
    public boolean deleteFollow(Integer uid, Integer fid) {
        return userMapper.deleteFollow(uid, fid);
    }

    @Override
    public boolean opRoot(Integer uid) {
        User user = userMapper.selectById(uid);
        if (user != null) {
            return userMapper.updateById(
                    user.getRoot() == 0 ? user.setRoot(1) : user.setRoot(0)
            ) != 0;
        }
        return false;
    }

    private Page<User> parse(Page page) {
        ((List<User>) page.getRecords()).forEach(user -> {
            if(StringUtil.isStorage(user.getHeadImgUrl()))
                user.setHeadImgUrl(fileQiniu.getDownloadPrivatePath(user.getHeadImgUrl()));

            if(StringUtil.isStorage(user.getTopImgUrl()))
                user.setTopImgUrl(fileQiniu.getDownloadPrivatePath(user.getTopImgUrl()));
        });
        return page;
    }

    private User parse(User user) {
        if(StringUtil.isStorage(user.getHeadImgUrl()))
        user.setHeadImgUrl(fileQiniu.getDownloadPrivatePath(user.getHeadImgUrl()));
        if(StringUtil.isStorage(user.getTopImgUrl()))
        user.setTopImgUrl(fileQiniu.getDownloadPrivatePath(user.getTopImgUrl()));
        return user;
    }
}
