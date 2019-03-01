package com.lxpro.web.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.MultipartConfigElement;
import java.util.HashMap;
import java.util.Map;

/**
 * springmvc配置类
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/pages/");
        resolver.setSuffix(".html");
        return resolver;
    }
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("10240MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("10240MB");
        return factory.createMultipartConfig();
    }

    public static   void haha (String  m){

        String[] split = m.split(",");
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 1; i < split.length; i++) {
            //  如果不连续 直接 算 1
            if(!split[i].equals(split[i-1])){
                /*HashMap<Object, Object> objectHashMap = new HashMap<>();
                objectHashMap.put(map.get(split[i]),split[i]);

                *//*map.put(split[i],1);*/
            }else {
                //  如果连续
                Integer integer= map.get(split[i]);
                if(integer==null) {
                    integer=1;
                    map.put(split[i],integer+1);
                }else {
                    map.put(split[i],integer+1);
                }

            }


        }

        int max=0;
/*        HashMap<Integer, Object> map1 = new HashMap<>();
        for (Map.Entry<Object, Integer> o : map.entrySet()) {
            if (o.getValue()>max){
                max= o.getValue();
                map1.put(max,o.getKey());
            }
        }*/

        System.out.println(m);
    }
    public static void main(String[] args) {

        String  str = "1,1,1,1,2,2,2,2,3,3,3,3,7,7,7,7,7,4,5,6,2,1,1,2,2,8,7,7,7,5,5,7,5,5,5,5,5,5,5,5,4,4,4,5,5";
        String[] split = str.split(",");
        Integer[] arr = new Integer[split.length];
        for (int i = 0; i < split.length; i++) {
            arr[i] = Integer.valueOf(split[i]);
        }
        int shuzi = 0;
        int count = 0;
        int shuzitemp = arr[0];
        int counttemp = 1;
        for (int i = 1; i < arr.length - 2; i++) {
            if(shuzitemp == arr[i]) {
                counttemp++;
            }else {
                if(count<counttemp) {
                    count=counttemp;
                    shuzi = shuzitemp;
                }
                counttemp = 1;
                shuzitemp = arr[i];
            }
        }
        System.out.println("数字:"+shuzi);
        System.out.println("次数:"+count);

    }


    }


