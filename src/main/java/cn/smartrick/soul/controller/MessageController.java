package cn.smartrick.soul.controller;

import cn.smartrick.soul.dto.Msg;
import cn.smartrick.soul.entity.Message;
import cn.smartrick.soul.entity.Music;
import cn.smartrick.soul.service.MessageService;
import cn.smartrick.soul.service.MusicService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    private final String word = "资讯";

    @GetMapping("/release/{uid}")
    public Msg queryReleaseByUser(@PathVariable("uid") Integer uid,@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(messageService.queryReleaseByUser(uid,page),"查询作者的",word);
    }

    @GetMapping("/sort/{sortId}")
    public Msg queryMessageBySortId(@PathVariable("sortId") Integer sortId,@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(messageService.queryMessageBySortId(sortId,page),"查询分类",word);
    }

    @GetMapping("/all")
    public Msg queryAllMessage(@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(messageService.queryAllMessage(page),"查询全部",word);
    }

    @PostMapping
    public Msg addMessage(@RequestParam("message") Message message){
        return Msg.res(messageService.addMessage(message),"添加",word);
    }

    @PutMapping
    public Msg modifyMessage(@RequestParam("message") Message message){
        return Msg.res(messageService.modifyMessage(message),"修改",word);
    }

    @DeleteMapping
    public Msg removeMessage(@RequestParam("id") Integer id){
        return Msg.res(messageService.removeMessage(id),"删除",word);
    }

    @GetMapping("/playing")
    public Msg playing(@RequestParam("id") Integer id,@RequestParam("uid") Integer uid){
        return Msg.res(messageService.reading(id,uid),"播放",word);
    }
}
