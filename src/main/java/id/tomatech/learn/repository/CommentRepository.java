package id.tomatech.learn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import id.tomatech.learn.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	Page<Comment> findAllByPostId(Long postId, Pageable pageable);
	Comment findOneByPostIdAndId(Long postId, Long commentId);
}
