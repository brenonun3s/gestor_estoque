package br.com.estoque.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
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

 public FileStorageController(FileStorageProperties fileStorageProperties) {
  this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
 }

 @PostMapping("/upload")
 public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
  String fileName = StringUtils.cleanPath(file.getOriginalFilename());

  try {
   Path targetLocation = fileStorageLocation.resolve(fileName);
   file.transferTo(targetLocation);

   String fileUrl = ServletUriComponentsBuilder
     .fromCurrentContextPath()
     .path("api/files/download/")
     .path(fileName)
     .toUriString();

   return ResponseEntity.ok("Upload feito com sucesso! Link para baixar " + fileUrl);
  } catch (IOException e) {
   return ResponseEntity
     .status(500)
     .body("Erro ao fazer upload do arquivo: " + e.getMessage());

  }

  //TODO:METODO FALHANDO -> CORRIGIR
  @GetMapping("/download/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws IOException {

    Path filePath = fileStorageLocation.resolve(fileName).normalize();
    Resource resource = new UrlResource(filePath.toUri());

    try {
     String contextType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    
     if (contextType == null) {
      contextType = "application/octet-stream";
     }

     return ResponseEntity.ok().contentType(MediaType.parseMediaType(contextType))
      .header(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);


    } catch (MalformedURLException e) {
    return ResponseEntity.status(500).body("Erro ao baixar o arquivo: " + e.getMessage());
   }
  }

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
