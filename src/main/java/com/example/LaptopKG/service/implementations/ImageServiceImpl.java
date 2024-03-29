package com.example.LaptopKG.service.implementations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.LaptopKG.exception.FileEmptyException;
import com.example.LaptopKG.model.Laptop;
import com.example.LaptopKG.model.User;
import com.example.LaptopKG.repository.LaptopRepository;
import com.example.LaptopKG.repository.UserRepository;
import com.example.LaptopKG.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final LaptopRepository laptopRepository;
    private final UserRepository userRepository;

    public String saveImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new FileEmptyException("Файл пустой");
        }

        final String urlKey = "cloudinary://753949556892917:SCszCjA1duCgeAaMxDP-7Qq3dP8@dja0nqat2";

        File saveFile = Files.createTempFile(
                        System.currentTimeMillis() + "",
                        Objects.requireNonNull
                                        (file.getOriginalFilename(), "Файл должен иметь расширени")
                                .substring(file.getOriginalFilename().lastIndexOf("."))
                )
                .toFile();

        file.transferTo(saveFile);

        Cloudinary cloudinary = new Cloudinary((urlKey));

        Map upload = cloudinary.uploader().upload(saveFile, ObjectUtils.emptyMap());

        return (String) upload.get("url");
    }

    public ResponseEntity<String> saveForLaptop(Long laptopId, MultipartFile file) throws IOException {
        if(!laptopRepository.existsById(laptopId)) {
            return ResponseEntity.badRequest().body("Ноутбук с айди " + laptopId + " не найден");
        }

        Laptop laptop = laptopRepository.findById(laptopId).get();
        laptop.setImageUrl(saveImage(file));
        laptopRepository.save(laptop);
        return ResponseEntity.ok("Фотография сохранена");
    }

    public ResponseEntity<String> saveForUser(User user, MultipartFile file) throws IOException {
            user.setImageUrl(saveImage(file));
            userRepository.save(user);
            return ResponseEntity.ok("Фотография сохранена");
    }
}
