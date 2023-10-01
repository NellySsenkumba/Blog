package org.info.blog.repository;

import org.info.blog.models.PostLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<PostLikes, Long> {
}
