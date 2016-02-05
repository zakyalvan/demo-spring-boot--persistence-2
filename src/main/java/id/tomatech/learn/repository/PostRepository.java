package id.tomatech.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.tomatech.learn.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
}
