package cn.smartrick.soul.controller;

import cn.smartrick.soul.dto.Msg;
import cn.smartrick.soul.entity.User;
import cn.smartrick.soul.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    private UserService userService;

    private final String word = "用户";

    @PostMapping("/register")
    public Msg register(@RequestBody User user){
        log.info(user.toString());
        return Msg.res(userService.register(user),"注册",word);
    }

    @PostMapping("/login")
    public Msg login(@RequestBody User user){
        log.info(user.toString());
        User login = userService.login(user.getUsername(), user.getPassword());
        return Msg.res(login,"用户登录成功");
    }

    @PutMapping
    public Msg modifyUser(@RequestBody User user){
        log.info(user.toString());
        return Msg.res(userService.modifyUser(user),"修改",word);
    }

    @DeleteMapping("/{id}")
    public Msg deleteUser(@PathVariable("id") Integer id){
        log.info(id+"");
        return Msg.res(userService.deleteUser(id),"删除",word);
    }

    @GetMapping("/all")
    public Msg queryAllUser(@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(userService.queryAllUser(page),"查询",word);
    }

    @GetMapping("/all_s")
    public Msg queryAllUserSimple(@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(userService.queryAllUserSimple(page),"查询",word);
    }

    @GetMapping("/follows")
    public Msg queryFollows(@RequestParam("uid") Integer uid,@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(userService.queryFollows(uid,page),"查询用户关注");
    }

    @GetMapping("/fans")
    public Msg queryFans(Integer uid,@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(userService.queryFans(uid,page),"查询用户粉丝");
    }

    @PostMapping("/follow")
    public Msg addFollow(Integer uid, Integer fid){
        return Msg.res(userService.addFollow(uid,fid),"添加","关注");
    }

    @DeleteMapping("/follow")
    public Msg deleteFollow(Integer uid, Integer fid){
        return Msg.res(userService.deleteFollow(uid,fid),"取消","关注");
    }

    @PutMapping("/root/{uid}")
    public Msg deleteFollow(@PathVariable("uid") Integer uid){
        return Msg.res(userService.opRoot(uid),"修改","Root权限");
    }

}
