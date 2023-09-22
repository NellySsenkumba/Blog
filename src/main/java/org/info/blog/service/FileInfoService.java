package org.info.blog.service;

import org.info.blog.models.FileInfo;
import org.info.blog.repository.FileInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class FileInfoService {

    private String uploadDirectoryPath="src/main/resources/uploads/";



    @Autowired
    private ResourceLoader resourceLoader;

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

        String contentType = fileInfo.get(0).getType();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));
        headers.setContentDispositionFormData("attachment", filename);

        return ResponseEntity.ok()
                .headers(headers)
                .body(data);

    }


    public ResponseEntity<?> storeFile(MultipartFile file) throws IOException {

        Path rootLocation = Paths.get(uploadDirectoryPath);

        Path destinationFile = rootLocation
                .resolve(Paths.get(file.getOriginalFilename()))
                .normalize()
                .toAbsolutePath();
        InputStream inputStream = file.getInputStream();

        Files.copy(inputStream, destinationFile,
                StandardCopyOption.REPLACE_EXISTING);


        return new ResponseEntity<>("File uploaded",HttpStatus.CREATED);
    }
}
