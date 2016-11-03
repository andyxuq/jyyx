package com.jyyx.service.utils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jyyx.core.constant.Constants;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 配置读取工具类
 * @author xuqing01
 *
 */
@Component
public class ConfigLoader implements ApplicationContextAware{

    /**
     * spring applicationContext
     */
    private static ApplicationContext applicationContext;
    
    /**
     * properties文件读取
     */
    private static Properties commonConfig;
    
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ConfigLoader.applicationContext = applicationContext;
        ConfigLoader.commonConfig = applicationContext.getBean("commonConfig", Properties.class);
    }
    
	public static <T> T getBean(Class<T> t) {
        return applicationContext.getBean(t);
    }
    
    /**
     * 从配置文件中获取配置信息
     * @param key
     * @return
     */
    public static String getConfig(String key) {
        return commonConfig.getProperty(key);
    }
    
    /**
     * 获取上传路径
     * @param key
     * @return
     */
    public static String getUploadPath(String key) {
    	String rootPath = getConfig(Constants.FILE_UPLOAD_PATH);
    	String uploadPath = getConfig(key);
    	
    	return rootPath + uploadPath;
    }
}
