package org.info.blog.controllers;

import org.info.blog.models.FileInfo;
import org.info.blog.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/fileInfo")
public class FileInfoController {


    private final FileInfoService fileInfoService;

    public FileInfoController(FileInfoService fileInfoService) {
        this.fileInfoService = fileInfoService;
    }


    @PostMapping
    public ResponseEntity<FileInfo> uploadFile(@RequestParam MultipartFile file) throws IOException {
        return fileInfoService.uploadFile(file);
    }

    @GetMapping("{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename) {
        return fileInfoService.downloadFile(filename);
    }

    @PostMapping("/store")
    public ResponseEntity<?> storeFile(@RequestParam MultipartFile file) throws IOException {
        return fileInfoService.storeFile(file);
    }
}
