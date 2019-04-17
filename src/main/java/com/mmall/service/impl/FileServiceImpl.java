package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String upload(String path, MultipartFile file) {
        String originName = file.getOriginalFilename();
        String extendFileProp = originName.substring(originName.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + extendFileProp;
        logger.info("开始上传文件到文件服务器,上传文件名:{},路径:{},新文件名:{}", originName, path, fileName);
        File pathFile = new File(path);
        if (!pathFile.exists()) {
            pathFile.setWritable(true);
            pathFile.mkdirs();
        }
        File targetFile = new File(path, fileName);
        try {
            file.transferTo(targetFile);
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传到文件服务器失败", e);
            return null;
        }
        return targetFile.getName();
    }
}
