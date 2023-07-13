package project.server.domain.comment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.server.domain.cocktail.entity.Cocktail;
import project.server.domain.cocktail.service.CocktailReadService;
import project.server.domain.cocktail.service.CocktailService;
import project.server.domain.comment.dto.CommentDto;
import project.server.domain.comment.entity.Comment;
import project.server.domain.comment.repository.CommentRepository;
import project.server.exception.BusinessLogicException;
import project.server.exception.ExceptionCode;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CocktailReadService cocktailReadService;

    public CommentService(CommentRepository commentRepository,
                          CocktailReadService cocktailReadService) {
        this.commentRepository = commentRepository;
        this.cocktailReadService = cocktailReadService;
    }

    @Transactional
    public CommentDto.Response createComment(Long cocktailId, CommentDto.Post post) {
        Cocktail cocktail = cocktailReadService.readCocktail(cocktailId);
        Comment comment = post.postToEntity();
        Comment savedComment = commentRepository.save(comment);
        return savedComment.entityToResponse();
    }

    @Transactional(readOnly = true)
    public CommentDto.Response readComment(long commentId) {
        Comment comment = findCommentById(commentId);
        return comment.entityToResponse();
    }

    public Comment findCommentById(long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
    }

    public Comment updateComment(CommentDto.Patch patch) {
        Comment comment = findCommentById(patch.getCommentId());
        comment.setContent(patch.getContent());
        return commentRepository.save(comment);
    }

    public void deleteComment(long commentId) {
        Comment comment = findCommentById(commentId);
        commentRepository.delete(comment);
    }
}
