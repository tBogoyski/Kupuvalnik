package com.project.kupuvalnik.service;

import com.project.kupuvalnik.service.impl.CloudinaryImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    CloudinaryImage upload(MultipartFile file) throws IOException;

    boolean delete(String publicId);
}
