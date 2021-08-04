package com.sswu.crople.controller;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sswu.crople.dto.UploadresultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

// 파일 업로드를 담당하는 컨트롤러
@RestController
@RequiredArgsConstructor
@Log4j2
public class UploadController {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<String>> uploadFile(MultipartFile[] uploadFiles){

        List<String> URLList = new ArrayList<>();

        for(MultipartFile uploadFile: uploadFiles){

            if(uploadFile.getContentType().startsWith("image") == false){
                log.warn("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            File convertFile = new File(uploadFile.getOriginalFilename());
            try {
                if(convertFile.createNewFile()) {
                    try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                        fos.write(uploadFile.getBytes());
                    }catch (IOException e){
                        System.out.println("파일 write 오류");
                    }
                }
            }catch (IOException e){
                System.out.println("파일 create 오류");
            }

            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("/") + 1);
            log.info("fileName: " + fileName);
            String dirPath = "review/" + makeDirPath();
            String uuid = UUID.randomUUID().toString();
            String saveName =  File.separator + dirPath + File.separator + uuid + "_" + fileName;

            amazonS3Client.putObject(new PutObjectRequest(bucket, saveName, convertFile).withCannedAcl(CannedAccessControlList.PublicRead));
            System.out.println(saveName);
//            String url = amazonS3Client.getResourceUrl(bucket, saveName).toString();
            String uri = amazonS3Client.getObject(bucket, saveName).getObjectContent().getHttpRequest().getURI().toString();
            System.out.println("-----------------------------------------------url: " + uri);

            convertFile.delete();

            URLList.add(uri);
        }

        return new ResponseEntity<>(URLList, HttpStatus.OK);
    }

    private String makeDirPath(){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        return folderPath;
    }

//    @GetMapping("/display")
//    public ResponseEntity<byte[]> getFile(String fileName){
//
//        ResponseEntity<byte[]> result = null;
//
//        try{
//            String srcFileName = URLDecoder.decode(fileName, "UTF-8");
//
//            log.info("fileName: " + srcFileName);
//
//            File file = new File(uploadPath + File.separator+srcFileName);
//
//            log.info("file: " + file);
//
//            HttpHeaders header = new HttpHeaders();
//
//            header.add("Content-Type", Files.probeContentType(file.toPath()));
//
//            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
//        } catch (Exception e){
//            log.error(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return result;
//    }
//
//    @PostMapping("/removeFile")
//    public ResponseEntity<Boolean> removeFile(String fileName){
//
//        String srcFileName = null;
//
//        try{
//            srcFileName = URLDecoder.decode(fileName, "UTF-8");
//            File file = new File(uploadPath + File.separator+srcFileName);
//            boolean result = file.delete();
//
//            File thumbnail = new File(file.getParent(), "s_" + file.getName());
//
//            result = thumbnail.delete();
//
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        }catch (UnsupportedEncodingException e){
//            e.printStackTrace();
//            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
