package com.manjesh.blog.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;

public interface FileService {
	String uploadImage(String path,MultipartFile file) throws IOException;
	InputStream getResource(String path,String fileName) throws FileNotFoundException;
	
}
