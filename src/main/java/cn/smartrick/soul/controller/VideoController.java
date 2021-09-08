package cn.smartrick.soul.controller;

import cn.smartrick.soul.dto.Msg;
import cn.smartrick.soul.entity.Video;
import cn.smartrick.soul.service.VideoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    private final String word = "视频";

    @GetMapping("/release/{uid}")
    public Msg queryReleaseByUser(@PathVariable("uid") Integer uid,@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(videoService.queryReleaseByUser(uid,page),"查询发布",word);
    }

    @GetMapping("/sort/{sortId}")
    public Msg queryVideoBySortId(@PathVariable("sortId") Integer sortId,@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(videoService.queryVideoBySortId(sortId,page),"查询分类",word);
    }

    @GetMapping("/all")
    public Msg queryAllVideo(@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(videoService.queryAllVideo(page),"查询全部",word);
    }

    @PostMapping
    public Msg addVideo(@RequestBody Video video){
        log.info(video.toString());
        return Msg.res(videoService.addVideo(video),"添加",word);
    }

    @PutMapping
    public Msg modifyVideo(@RequestBody Video video){
        log.info(video.toString());
        return Msg.res(videoService.modifyVideo(video),"修改",word);
    }

    @DeleteMapping("/{id}")
    public Msg removeVideo(@PathVariable("id") Integer id){
        log.info(id.toString());
        return Msg.res(videoService.removeVideo(id),"删除",word);
    }



}
