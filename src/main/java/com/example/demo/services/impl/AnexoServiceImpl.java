package com.example.demo.services.impl;

import com.example.demo.entity.Anexo;
import com.example.demo.exception.AnexoNotFoundException;
import com.example.demo.exception.AnexoStorageException;
import com.example.demo.exception.RegradeNegocioException;
import com.example.demo.property.AnexoStorageProperties;
import com.example.demo.repository.AnexoRepository;
import com.example.demo.services.AnexoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class AnexoServiceImpl implements AnexoService {

    private final Path fileStorageLocation;

    @Autowired
    private AnexoRepository repository;

    @Autowired
    public AnexoServiceImpl(AnexoStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new AnexoStorageException("Não foi possível criar o diretório onde os anexos seriam guardados.", ex);
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

    public Anexo save(Anexo entity) {
        return repository.save(entity);
    }

    public Anexo setAnexo(MultipartFile arquivo, String nomeAnexo) {
        Anexo anexo = new Anexo();
        anexo.setNome(arquivo.getName());
        anexo.setExtensao(arquivo.getContentType());
        anexo.setTamanho(arquivo.getSize());

        String anexoDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/anexo/download/")
                .path(nomeAnexo)
                .toUriString();

        anexo.setPath(anexoDownloadUri);

        return anexo;
    }

    public Anexo findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new RegradeNegocioException("Anexo não encontrado"));
    }

    public Anexo validaAnexo(MultipartFile anexo) {
        String nomeAnexo = this.storeAnexo(anexo);

        if(!Objects.requireNonNull(anexo.getContentType()).contains("jpeg") &&
                !anexo.getContentType().contains("gif") &&
                !anexo.getContentType().contains("png")) {
            throw new RegradeNegocioException("A extensão do anexo é inválida. Extensões permitidas: jpeg, jpg, gif, png");
        }

        if(anexo.getSize() > 35000000) {
            throw new RegradeNegocioException("O tamanho máximo de anexo permitido é de 35Mb");
        }

        return this.setAnexo(anexo, nomeAnexo);
    }
}
