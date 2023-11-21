package com.lcwd.electronic.store.serviceImpl;

import com.lcwd.electronic.store.exception.BadApiRequest;
import com.lcwd.electronic.store.serviceI.fileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class FileServiceImpl implements fileService {

    private Logger logger= LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
        String originalFilename = file.getOriginalFilename();
        logger.info("file name: {}",originalFilename);
        String fileName= UUID.randomUUID().toString();
        String extenstion = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileNameWithExtension = fileName + extenstion;
        String fullPathWithFileName= path + File.separator+ fileNameWithExtension;
        if(extenstion.equalsIgnoreCase(".png")|| extenstion.equalsIgnoreCase(".jpg")|| extenstion.equalsIgnoreCase(".jpeg")){

            File folder = new File(path);

            if(!folder.exists()){
                folder.mkdirs();
            }

            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            return  fileNameWithExtension;
        }else
        {
            throw  new BadApiRequest("File with this "+extenstion +"Not allow");
        }


    }

    @Override
    public InputStream getResourse(String path, String name) throws FileNotFoundException {

        String fullPath= path+ File.separator +name;

        InputStream inputStream= new FileInputStream(fullPath);

        return inputStream;
    }
}
