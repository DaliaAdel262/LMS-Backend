package org.software_assignment.lms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



@Setter
@Getter
@AllArgsConstructor
public class MediaFile {
    private String id;
    private String fileUrl;   // URL of the file (e.g., cloud storage path)
    private String fileType;  // Type of the file (e.g., video, pdf, audio)
    private LocalDateTime uploadedAt; // Timestamp of when the file was uploaded


}