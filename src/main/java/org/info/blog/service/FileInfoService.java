package org.info.blog.service;

import org.info.blog.models.FileInfo;
import org.info.blog.repository.FileInfoRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileInfoService {
    public FileInfoService(FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
    }

    private final FileInfoRepository fileInfoRepository;


    public ResponseEntity<FileInfo> uploadFile(MultipartFile file) throws IOException {
        FileInfo fileInfo = FileInfo.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .data(file.getBytes())
                .build();

        return new ResponseEntity<>(fileInfoRepository.save(fileInfo), HttpStatus.CREATED);

    }

    public ResponseEntity<?> downloadFile(String filename) {
        List<FileInfo> fileInfo = fileInfoRepository.findByName(filename);
        byte[] data = fileInfo.get(0).getData();

//        fileInfo.get(0).getType();
//        System.out.println(fileInfo.get(0).getType());
//        System.out.println(MediaType.IMAGE_JPEG);
//
////        MultipartFile file;
////        return new ResponseEntity<>(data, HttpStatus.OK);
//        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fileInfo.get(0).getType())).body(filename);

        String contentType = fileInfo.get(0).getType();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));
        headers.setContentDispositionFormData("attachment", filename);

        return ResponseEntity.ok()
                .headers(headers)
                .body(data);

    }
}
