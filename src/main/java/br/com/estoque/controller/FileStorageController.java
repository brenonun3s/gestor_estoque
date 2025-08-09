package br.com.estoque.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.estoque.config.FileStorageProperties;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("api/files")
public class FileStorageController {

  private final Path fileStorageLocation;

  public FileStorageController(FileStorageProperties fileStorageProperties) throws IOException {
    this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

    if (!Files.exists(this.fileStorageLocation)) {
      Files.createDirectories(this.fileStorageLocation);
    }
  }

  @PostMapping("/upload")
  public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    if (fileName.contains("..")) {
      return ResponseEntity.badRequest().body("Nome de arquivo inválido: " + fileName);
    }

    try {
      Path targetLocation = fileStorageLocation.resolve(fileName).normalize();
      file.transferTo(targetLocation);

      String fileUrl = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/api/files/download/")
          .path(fileName)
          .toUriString();

      // DEBUG PARA IR FAZENDO OS TESTES - TESTE 1 OK - VOU MANTER ATÉ A INTEGRAÇÃO
      // COM SECURITY E FRONT
      System.out.println("Salvando arquivo em: " + targetLocation);

      return ResponseEntity.ok("Upload feito com sucesso! Link para baixar " + fileUrl);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity
          .status(500)
          .body("Erro ao fazer upload do arquivo: " + e.getMessage());

    }
  }

  @GetMapping("/download/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request)
      throws IOException {
    Path filePath = fileStorageLocation.resolve(fileName).normalize();
    Resource resource = new UrlResource(filePath.toUri());

    if (!resource.exists() || !resource.isReadable()) {
      return ResponseEntity.notFound().build();
    }

    String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    if (contentType == null) {
      contentType = "application/octet-stream";
    }

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }

  @GetMapping("/list")
  public ResponseEntity<List<String>> listFiles() throws IOException {
    List<String> fileNames = Files.list(fileStorageLocation)
        .map(Path::getFileName)
        .map(Path::toString)
        .collect(Collectors.toList());

    return ResponseEntity.ok(fileNames);
  }
}
