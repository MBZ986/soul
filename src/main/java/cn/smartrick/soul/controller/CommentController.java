package cn.smartrick.soul.controller;

import cn.smartrick.soul.dto.Msg;
import cn.smartrick.soul.entity.Comment;
import cn.smartrick.soul.entity.Video;
import cn.smartrick.soul.service.CommentService;
import cn.smartrick.soul.service.PosterService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    private final String word = "评论";

    @GetMapping("/release/{uid}")
    public Msg queryReleaseByUser(@PathVariable("uid") Integer uid,@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);

        return Msg.res(commentService.queryReleaseByUser(uid,page),"查询全部",word);
    }

    @GetMapping("/media")
    public Msg queryReleaseByUser(@RequestParam("mtype") Integer mtype,@RequestParam("mid") Integer mid,@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(commentService.queryCommentsByMedia(mtype,mid,page),"查询媒体",word);
    }


    @GetMapping("/all")
    public Msg queryAllComment(@RequestParam("current") Integer current,@RequestParam("size") Integer size){
        Page page = new Page(current,size);
        return Msg.res(commentService.queryAllComment(page),"查询全部",word);
    }

    @PostMapping
    public Msg addComment(@RequestParam("comment") Comment comment){
        return Msg.res(commentService.addComment(comment),"添加",word);
    }

    @PutMapping
    public Msg modifyComment(@RequestParam("comment") Comment comment){
        return Msg.res(commentService.modifyComment(comment),"修改",word);
    }

    @DeleteMapping
    public Msg removeComment(@RequestParam("id") Integer id){
        return Msg.res(commentService.removeComment(id),"删除",word);
    }
}
