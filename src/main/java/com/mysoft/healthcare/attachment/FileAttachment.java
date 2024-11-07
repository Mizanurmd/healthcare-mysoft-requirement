package com.mysoft.healthcare.attachment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file_attachment")
public class FileAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;    // Original file name
    private String fileType;    // MIME type
    private long fileSize;      // Size in bytes
    private byte[] fileData;    // Store file as binary data

    private Date uploadedDate;


}
