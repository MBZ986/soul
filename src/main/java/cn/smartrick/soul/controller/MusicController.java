package cn.smartrick.soul.controller;

import cn.smartrick.soul.dto.Msg;
import cn.smartrick.soul.entity.Music;
import cn.smartrick.soul.entity.Video;
import cn.smartrick.soul.service.MusicService;
import cn.smartrick.soul.service.VideoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/music")
public class MusicController {
    @Autowired
    private MusicService musicService;

    private final String word = "音乐";

    @GetMapping("/author")
    public Msg queryMusicByAuthor(@RequestParam("author") String author,@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(musicService.queryMusicByAuthor(author,page),"查询作者的",word);
    }

    @GetMapping("/sort/{sortId}")
    public Msg queryMusicSortId(@PathVariable("sortId") Integer sortId,@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(musicService.queryMusicBySortId(sortId,page),"查询分类",word);
    }

    @GetMapping("/all")
    public Msg queryAllMusic(@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(musicService.queryAllMusic(page),"查询全部",word);
    }

    @PostMapping
    public Msg addMusic(@RequestBody Music music){
        return Msg.res(musicService.addMusic(music.setReleaseDate(new Date())),"添加",word);
    }

    @PutMapping
    public Msg modifyMusic(@RequestBody Music music){
        return Msg.res(musicService.modifyMusic(music),"修改",word);
    }

    @DeleteMapping("/{id}")
    public Msg removeMusic(@PathVariable("id") Integer id){
        return Msg.res(musicService.removeMusic(id),"删除",word);
    }

    @GetMapping("/playing")
    public Msg playing(@RequestParam("id") Integer id,@RequestParam("uid") Integer uid){
        return Msg.res(musicService.playing(id,uid),"播放",word);
    }


}
