package com.dbc.minifb.service;

import com.dbc.minifb.business.response.BaseResponse;
import com.dbc.minifb.business.response.ResponseFactory;
import com.dbc.minifb.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Knox
 * @Date: 2018/12/3 8:32 PM
 * @Description: You Know
 * @Version 1.0
 */
@Service
@Slf4j
public class FileOptionService {

    @Value("${application.file.baseStaticResourcesPath}")
    private String basePath;
    private static final String POST_IMAGES_SUFFIX = "/images/posts";
    private static final String HEAD_ICONS_SUFFIX = "/images/headicons";
    private static final String PATH_SEPARATE = "/";

    public List<String> uploadPostImages(MultipartFile[] images, User poster) {
        List<String> imagesPaths = new ArrayList<>();
        try {
            String encode = encodeToBytes(poster.getUsername());
            String userImageDir = basePath + POST_IMAGES_SUFFIX + PATH_SEPARATE + encode;
            File dir = new File(userImageDir);
            if (!dir.exists()) {
                boolean success = dir.mkdirs();
                if (!success) {
                    log.error("创建文件夹" + userImageDir + "失败！");
                    throw new FileNotFoundException("创建文件夹失败！");
                }
            }
            String imageDir = "/static" + POST_IMAGES_SUFFIX + PATH_SEPARATE + encode + PATH_SEPARATE;
            for (MultipartFile file : images) {
                String fileName = file.getOriginalFilename();
                assert fileName != null;
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                InputStream inputStream = file.getInputStream();
                String imageName = System.currentTimeMillis() + suffix;
                FileOutputStream os = new FileOutputStream(userImageDir + PATH_SEPARATE + imageName);
                IOUtils.copy(inputStream, os);
                imagesPaths.add(imageDir + imageName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagesPaths;

    }

    public String uploadHeadIcon(MultipartFile headIcon, User user) {
        String imagePath = "";
        try {
            String encode = encodeToBytes(user.getUsername());
            String headImageDir = basePath + HEAD_ICONS_SUFFIX + PATH_SEPARATE + encode;
            File file = new File(headImageDir);
            if (!file.exists()) {
                boolean mkdirs = file.mkdirs();
                if (!mkdirs) {
                    log.error("创建文件夹" + headImageDir + "失败");
                    throw new FileNotFoundException();
                }
            }
            String imageUrlPath = "/static" + HEAD_ICONS_SUFFIX + PATH_SEPARATE + encode + PATH_SEPARATE;
            String fileName = headIcon.getOriginalFilename();
            assert fileName != null;
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String imageName = System.currentTimeMillis() + suffix;
            InputStream in = headIcon.getInputStream();
            FileOutputStream os = new FileOutputStream(headImageDir + PATH_SEPARATE + imageName);
            IOUtils.copy(in, os);
            imagePath = imageUrlPath + imageName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagePath;
    }


    private String encodeToBytes(String str) {
        StringBuilder sb = new StringBuilder();
        for (byte a : str.getBytes()) {
            sb.append(a >> 4 & 0x0f);
            sb.append(a & 0x0f);
        }
        return sb.toString();
    }
}
