package com.example.demo.services;

import com.example.demo.entity.Anexo;
import com.example.demo.exception.AnexoNotFoundException;
import com.example.demo.exception.AnexoStorageException;
import com.example.demo.property.AnexoStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class AnexoStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public AnexoStorageService(AnexoStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new AnexoStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeAnexo(MultipartFile anexo) {
        // Normalize file name
        String nomeAnexo = StringUtils.cleanPath(Objects.requireNonNull(anexo.getOriginalFilename()));

        try {
            // Check if the file's name contains invalid characters
            if(nomeAnexo.contains("..")) {
                throw new AnexoStorageException("Sorry! Filename contains invalid path sequence " + nomeAnexo);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(nomeAnexo);
            Files.copy(anexo.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return nomeAnexo;
        } catch (IOException ex) {
            throw new AnexoStorageException("Could not store file " + nomeAnexo + ". Please try again!", ex);
        }
    }

    public Resource loadAnexoAsResource(String nomeAnexo) {
        try {
            Path filePath = this.fileStorageLocation.resolve(nomeAnexo).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new AnexoNotFoundException("File not found " + nomeAnexo);
            }
        } catch (MalformedURLException ex) {
            throw new AnexoNotFoundException("File not found " + nomeAnexo, ex);
        }
    }
}
