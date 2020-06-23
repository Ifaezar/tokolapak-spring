package com.ifaezar.tokolapak.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifaezar.tokolapak.dao.ProductWeekendTaskRepo;
import com.ifaezar.tokolapak.entity.ProductWeekendTask;

@RestController
@RequestMapping("/productsWeekendTask")
@CrossOrigin
public class ProductWeekendTaskController {

	private String uploadPath = System.getProperty("user.dir") + "/src/main/resources/static/images/";
	
	@Autowired
	private ProductWeekendTaskRepo productWeekendTaskRepo;
	
	@GetMapping
	public Iterable<ProductWeekendTask> getProducts(){
		return productWeekendTaskRepo.findAll();
	}
	
//	@PostMapping
//	public ProductWeekendTask addProduct(@RequestBody ProductWeekendTask productWeekendTask) {
//		productWeekendTask.setId(0);
//		return productWeekendTaskRepo.save(productWeekendTask);
//	}
	
	@DeleteMapping("/{id}")
	public void DeleteProduct(@PathVariable int id) {
		Optional <ProductWeekendTask> findProduct = productWeekendTaskRepo.findById(id);
		productWeekendTaskRepo.deleteById(id);
	}
	
	@PutMapping("/edit")
	public ProductWeekendTask editProduct(@RequestBody ProductWeekendTask productWeekendTask) {
		return productWeekendTaskRepo.save(productWeekendTask);
	}
	
	@PostMapping
	public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("productData") String userString) throws JsonMappingException, JsonProcessingException {
		Date date = new Date();
		
		ProductWeekendTask productWeekendTask = new ObjectMapper().readValue(userString, ProductWeekendTask.class);
		String fileExtension = file.getContentType().split("/")[1];
		
		String newFileName = "PROD-" + date.getTime() + "." + fileExtension;
		
		String fileName = StringUtils.cleanPath(newFileName);
		Path path = Paths.get(StringUtils.cleanPath(uploadPath) + fileName);
		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/documents/download/").path(fileName).toUriString();
		
		productWeekendTask.setImage(fileDownloadUrl);;
		
		productWeekendTaskRepo.save(productWeekendTask);
		
		return fileDownloadUrl;
//		return fileName + "has been upload";
	}
	
	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity<Object> downloadFile(@PathVariable String fileName){
		Path path = Paths.get(uploadPath + fileName);
		Resource resource = null;
		
		try {
			resource = new UrlResource(path.toUri());
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}
		System.out.println("DOWNLAOD");
		
		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment: filename=\"" + resource.getFilename()+ "\"").body(resource);
	}
	
}
