package cn.smartrick.soul.service.impl;

import cn.smartrick.soul.entity.Comment;
import cn.smartrick.soul.mapper.CommentMapper;
import cn.smartrick.soul.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Page<Comment> queryCommentsByMedia(Integer mtype, Integer mid,Page<Comment> page) {
        return commentMapper.selectCommentsByMedia(mtype, mid,(page.getCurrent()-1)*page.getSize(),page.getSize());
    }

    @Override
    public Page<Comment> queryReleaseByUser(Integer uid,Page<Comment> page) {
        return commentMapper.selectPage(page,new QueryWrapper<Comment>().eq("releaser",uid));
    }

    @Override
    public Page<Comment> queryAllComment(Page<Comment> page) {
        return commentMapper.selectPage(page,new QueryWrapper<>());
    }

    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.insert(comment) > 0;
    }

    @Override
    public boolean removeComment(Integer id) {
        return commentMapper.deleteById(id) > 0;
    }

    @Override
    public boolean modifyComment(Comment comment) {
        return commentMapper.updateById(comment) > 0;
    }

    @Override
    public boolean liker(Integer id) {
        return commentMapper.liker(id);
    }

    @Override
    public boolean unliker(Integer id) {
        return commentMapper.unliker(id);
    }
}
