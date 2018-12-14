package com.dbc.minifb.business.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;

/**
 * @Author: Knox
 * @Date: 2018/12/5 11:46 AM
 * @Description: You Know
 * @Version 1.0
 */
@Component
@Slf4j
public class AppStartListener implements ApplicationListener<ApplicationStartedEvent> {
    @Value("${application.file.baseStaticResourcesPath}")
    private String baseStaticResourcesPath;


    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        String postImagesPath = baseStaticResourcesPath + "/images/posts";
        String headIconsPath = baseStaticResourcesPath + "/images/headicons";
        createDir(postImagesPath);
        createDir(headIconsPath);
    }

    private void createDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            boolean success = file.mkdirs();
            if (!success) {
                log.error("创建" + path + "文件夹失败！");
                try {
                    throw new IOException("文件夹" + path + "创建失败!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
