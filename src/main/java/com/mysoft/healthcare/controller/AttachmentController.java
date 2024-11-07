package com.mysoft.healthcare.controller;

import com.mysoft.healthcare.attachment.FileAttachment;
import com.mysoft.healthcare.service.FileAttachmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
@RequestMapping("/api/v1/files")
public class AttachmentController {
    private final FileAttachmentService fileAttachmentService;

    public AttachmentController(FileAttachmentService fileAttachmentService) {
       this.fileAttachmentService = fileAttachmentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileAttachment> uploadFile(@RequestParam("fileData") MultipartFile fileData) {
        try {
            FileAttachment savedFile = fileAttachmentService.saveFile(fileData);
            return new ResponseEntity<>(savedFile, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
