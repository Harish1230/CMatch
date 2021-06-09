package com.cgi.ImageUpload.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin("*")
public class ImageUploadController {
	
	private static final Logger log = LoggerFactory.getLogger(ImageUploadController.class);


	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		log.info("Image Method Started");
		File convertFile = new File(
				"D:/JOB TRANING/cmatch-app/Front End/CMatch/src/assets/" + file.getOriginalFilename());
		file.transferTo(convertFile);
		// System.out.println(convertFile.getAbsolutePath());
//    	
//    	convertFile.createNewFile();
//    	
//    	try(FileOutputStream fout = new FileOutputStream(convertFile)) 
//    	{
//    		fout.write(file.getBytes());
//    	}
//    	catch(Exception exe)
//    	{
//    		exe.printStackTrace();
//    	}
		return new ResponseEntity<String>("successfull", HttpStatus.OK);

		// C:/Users/admin/CMatchApp/cmatch-app/Front End/CMatch/src/assets/
	}

}
