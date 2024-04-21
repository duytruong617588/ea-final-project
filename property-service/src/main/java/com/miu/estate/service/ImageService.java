package com.miu.estate.service;

import com.miu.estate.model.Image;
import com.miu.estate.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.net.URL;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;


    public Image saveImage(MultipartFile file, String description, Long propertyId) throws FileNotFoundException {
        String fileName = generateFileName(file.getOriginalFilename());
        String property = System.getProperty("file.separator");
        String directoryPath = ImageService.getClassDirectoryPath(ImageService.class);
        String directory = directoryPath + property + "images" + property;
        if (!new File(directory).exists()) {
            new File(directory).mkdir();
        }
        String filePath = directory + fileName;
        Image newImage;
        File uploadedFile = new File(filePath);

        try (FileOutputStream fos = new FileOutputStream(uploadedFile)) {
            fos.write(file.getBytes());
            Image image = new Image();
            image.setUrl(filePath);
            image.setDescription(description);
            image.setPropertyId(propertyId);
            newImage = imageRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newImage;
    }

    private String generateFileName(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        return UUID.randomUUID().toString() + extension;
    }

    public static String getClassDirectoryPath(Class<?> clazz) {
        URL url = clazz.getProtectionDomain().getCodeSource().getLocation();
        String filePath = url.getFile();
        try {
            filePath = java.net.URLDecoder.decode(filePath, "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new File(filePath).getParent();
    }
}
