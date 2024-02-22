package com.crudCommunity.CRUDCommunity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainModel {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommunityRepository communityRepository;
    
    @Autowired
    public MainModel(PostRepository postRepository, CommentRepository commentRepository, CommunityRepository communityRepository)
    {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.communityRepository = communityRepository;
    }
    public Long GetPostCount()
    {
        return postRepository.countBy();
    }
    public void AddPost(Post post)
    {
        Community community = communityRepository.findById(0L).get();
        post.number = community.postAmount++;
        post.uploadDate = new java.sql.Timestamp(System.currentTimeMillis()).toString();
        communityRepository.save(community);
        postRepository.save(post);
    }
    public void AddComment(Comment comment)
    {
        Community community = communityRepository.findById(0L).get();
        comment.number = community.commentAmount++;
        comment.uploadDate = new java.sql.Timestamp(System.currentTimeMillis()).toString();
        communityRepository.save(community);
        commentRepository.save(comment);
    } 
    public List<Post> GetPostList(int pageNumber)
    {
        Page<Post> result = postRepository.findAllByOrderByNumberDesc(PageRequest.of(pageNumber,20));
        List<Post> ret = result.getContent();
        for(int i=0;i<ret.size();++i)ret.get(i).SetUploadDate();
        return ret;
    }
    public String GetPassword(Long postId)
    {
        return postRepository.findById(postId).get().password;
    }
    public void DeletePost(Long postNumber)
    {
        postRepository.deleteById(postNumber);
    }
    public List<Comment> GetCommentList(Long postNumber)
    {
        List<Comment> result = commentRepository.findAllByPostNumber(postNumber);
        for(int i=0;i<result.size();++i)result.get(i).SetUploadDate();
        return result;
    }
    public Post GetPost(Long postId)
    {
        Post post = postRepository.findById(postId).get();
        post.SetUploadDate();
        return post;
    }
    public void EditPost(Post post)
    {
        Post temp = postRepository.findById(post.number).get();
        temp.title = post.title;
        temp.text = post.text;
        postRepository.save(temp);
    }

}
