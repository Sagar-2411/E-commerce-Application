package com.lcwd.electronic.store.serviceI;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface fileService {

    String uploadFile(MultipartFile file,String path) throws IOException;  //image uplaad with path

    InputStream getResourse(String path,String name) throws FileNotFoundException;



            }
