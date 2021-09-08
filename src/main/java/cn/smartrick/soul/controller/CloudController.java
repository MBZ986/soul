package cn.smartrick.soul.controller;

import cn.smartrick.soul.dto.Msg;
import cn.smartrick.soul.entity.Cloud;
import cn.smartrick.soul.entity.Music;
import cn.smartrick.soul.service.CloudService;
import cn.smartrick.soul.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloud")
public class CloudController {
    @Autowired
    private CloudService cloudService;

    private final String word = "缓存";

    @DeleteMapping("/user/{uid}")
    public Msg queryCloudByUserId(@PathVariable("uid") Integer uid) {
        return Msg.res(cloudService.queryCloudByUserId(uid), "查询用户", word);
    }

    @PostMapping
    public Msg addCloud(@RequestParam("cloud") Cloud cloud) {
        return Msg.res(cloudService.addCloud(cloud), "添加", word);
    }

    @PutMapping
    public Msg modifyCloud(@RequestParam("cloud") Cloud cloud) {
        return Msg.res(cloudService.modifyCloud(cloud), "修改", word);
    }

    @DeleteMapping
    public Msg removeCloud(@RequestParam("id") Integer id) {
        return Msg.res(cloudService.removeCloud(id), "删除", word);
    }

}
