package com.mmall.util;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.ftp.FtpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FTPUtil {
    private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);
    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;
//    ftp.server.ip=localhost
//    ftp.user=ftpuser
//    ftp.pass=ftpuser
//    ftp.server.http.prefix=http://image.imooc.com/
    private static String ftpIp = PropertyUtil.getProperty("ftp.server.ip");
    private static String ftpUser = PropertyUtil.getProperty("ftp.user");
    private static String ftpPwd = PropertyUtil.getProperty("ftp.pass");

    public FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    private boolean connectFtp(String ip, int port, String user, String pwd) {
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip, port);
            isSuccess = ftpClient.login(user, pwd);
        } catch (IOException e) {
            logger.error("connect ftp error", e);
        }
        return isSuccess;
    }

    public static boolean uploadFile(List<File> fileList) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(ftpIp, 21, ftpUser, ftpPwd);
        logger.info("start to connect ftp server ...");
        boolean res = ftpUtil.uploadFile("photo", fileList);
        logger.info("finish the upload func,end the server...upload res:{}.", res);
        return res;
    }

    private boolean uploadFile(String remotePath, List<File> fileList) throws IOException {
        boolean isSuccess = false;
        FileInputStream fis = null;
        if (connectFtp(this.ip, this.port, this.user, this.pwd)) {
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for (File file:fileList) {
                    fis = new FileInputStream(file);
                    ftpClient.storeFile(file.getName(), fis);
                }
                isSuccess = true;
                logger.info("已经上传到文件服务器.....");
            } catch (IOException e) {
                logger.error("upload file error..", e);
                e.printStackTrace();
            } finally {
                fis.close();
                ftpClient.disconnect();
            }
        }
        return isSuccess;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
