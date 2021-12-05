package com.project.kupuvalnik.service.impl;

import com.cloudinary.Cloudinary;
import com.project.kupuvalnik.service.CloudinaryService;
import com.project.kupuvalnik.service.impl.CloudinaryImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String TEMP_FILE = "temp-file";
    private static final String URL = "url";
    private static final String PUBLIC_ID = "public_id";

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    @Override
    public CloudinaryImage upload(MultipartFile file) throws IOException {

        File tempFile = File.createTempFile(TEMP_FILE, file.getOriginalFilename());
        file.transferTo(tempFile);

        try {


            @SuppressWarnings("unchecked")
            Map<String, String> uploadResult = cloudinary.uploader().upload(tempFile, Map.of());

            //TODO: add default value if cloudinary image is not found
            String url = uploadResult.getOrDefault(URL, "");
            String publicId = uploadResult.getOrDefault(PUBLIC_ID, "");

            CloudinaryImage cloudinaryImage = new CloudinaryImage();
            cloudinaryImage.setPublicId(publicId);
            cloudinaryImage.setUrl(url);

            return cloudinaryImage;


        } finally {
            tempFile.delete();
        }
    }

    public boolean delete(String publicId) {
        try {
            this.cloudinary.uploader().destroy(publicId, Map.of());
        } catch (IOException e) {
            return false;
        }
        return true;
    }


}
