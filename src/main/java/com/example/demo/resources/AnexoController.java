package com.example.demo.resources;

import com.example.demo.entity.Anexo;
import com.example.demo.services.impl.AnexoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/anexo")
public class AnexoController {

    private static final Logger logger = LoggerFactory.getLogger(AnexoController.class);

    @Autowired
    private AnexoServiceImpl anexoServiceImpl;

    @RequestMapping(headers=("content-type=multipart/*"), method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Anexo> uploadFile(@RequestParam("anexo") MultipartFile anexo) {
        String nomeAnexo = anexoServiceImpl.storeAnexo(anexo);
        Anexo anexoReq = anexoServiceImpl.setAnexo(anexo);

        String anexoDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/anexo/download/")
                .path(nomeAnexo)
                .toUriString();

        anexoReq.setPath(anexoDownloadUri);

        if(anexoServiceImpl.save(anexoReq) != null) {
            return new ResponseEntity<>(anexoReq, CREATED);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("download/{nomeAnexo}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String nomeAnexo, HttpServletRequest request) {
        Resource resource = anexoServiceImpl.loadAnexoAsResource(nomeAnexo);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Não foi possível determinar o tipo.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
