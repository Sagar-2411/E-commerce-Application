package com.lcwd.electronic.store.serviceI;

import com.lcwd.electronic.store.constant.AppConstant;
import com.lcwd.electronic.store.exception.BadApiRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class FileServiceImpl implements FileService {

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
            throw  new BadApiRequest(AppConstant.File_with_this+extenstion + AppConstant.Not_Allow);
        }


    }

    @Override
    public InputStream getResourse(String path, String name) throws FileNotFoundException {

        String fullPath= path+ File.separator +name;

        InputStream inputStream= new FileInputStream(fullPath);

        return inputStream;
    }
}
