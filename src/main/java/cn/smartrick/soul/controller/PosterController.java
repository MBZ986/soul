package cn.smartrick.soul.controller;

import cn.smartrick.soul.dto.Msg;
import cn.smartrick.soul.entity.Poster;
import cn.smartrick.soul.entity.Video;
import cn.smartrick.soul.service.PosterService;
import cn.smartrick.soul.service.VideoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/poster")
public class PosterController {
    @Autowired
    private PosterService posterService;
    private final String word = "帖子";

    @GetMapping("/release/{uid}")
    public Msg queryReleaseByUser(@PathVariable("uid") Integer uid,@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(posterService.queryReleaseByUser(uid,page),"查询发布",word);
    }

//    @GetMapping("/sort/{sortId}")
//    public Msg queryAllPoster(@PathVariable("sortId") Integer sortId){
//        return Msg.res(posterService.queryVideoBySortId(sortId),"查询分类",word);
//    }

    @GetMapping("/all")
    public Msg queryAllPoster(@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(posterService.queryAllPoster(page),"查询全部",word);
    }

    @PostMapping
    public Msg addPoster(@RequestBody Poster poster){
        return Msg.res(posterService.addPoster(poster),"添加",word);
    }

    @PutMapping
    public Msg modifyPoster(@RequestBody Poster poster){
        return Msg.res(posterService.modifyPoster(poster),"修改",word);
    }

    @DeleteMapping
    public Msg removePoster(@RequestParam("id") Integer id){
        return Msg.res(posterService.removePoster(id),"删除",word);
    }
}
