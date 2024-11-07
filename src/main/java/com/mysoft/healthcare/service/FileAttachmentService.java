package com.mysoft.healthcare.service;

import com.mysoft.healthcare.attachment.FileAttachment;
import com.mysoft.healthcare.repository.FileAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class FileAttachmentService {
    private final FileAttachmentRepository fileAttachmentRepository;

    @Autowired
    public FileAttachmentService(FileAttachmentRepository fileAttachmentRepository) {
        this.fileAttachmentRepository = fileAttachmentRepository;
    }

    public FileAttachment saveFile(MultipartFile file) throws IOException {
        FileAttachment fileAttachment = new FileAttachment();
        fileAttachment.setFileName(file.getOriginalFilename());
        fileAttachment.setFileType(file.getContentType());
        fileAttachment.setFileSize(file.getSize());
        fileAttachment.setFileData(file.getBytes());
        fileAttachment.setUploadedDate(new Date());
        return fileAttachmentRepository.save(fileAttachment);
    }


}
