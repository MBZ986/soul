package cn.smartrick.soul.service;

import cn.smartrick.soul.entity.Comment;
import cn.smartrick.soul.entity.Music;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface CommentService {
    public abstract Page<Comment> queryCommentsByMedia(Integer mtype, Integer mid,Page<Comment> page);//查询指定媒体的某个作品的全部评论
    public abstract Page<Comment> queryReleaseByUser(Integer uid,Page<Comment> page);//查询某个用户全部评论
    public abstract Page<Comment> queryAllComment(Page<Comment> page);//查询全部评论
    public abstract boolean addComment(Comment comment);//用户发表评论
    public abstract boolean removeComment(Integer id);//用户发表评论
    public abstract boolean modifyComment(Comment comment);//用户发表评论
    public abstract boolean liker(Integer id);//某条评论被点赞
    public abstract boolean unliker(Integer id);//某条评论取消点赞
}
