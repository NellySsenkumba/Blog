package org.info.blog.repository;

import org.info.blog.models.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    List<FileInfo> findByName(String filename);
}
