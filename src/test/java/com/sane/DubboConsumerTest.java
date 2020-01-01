package com.sane;

import com.sane.dh.dubbo.service.DubboService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DubboConsumerTest {
    public static void main(String[] args) throws URISyntaxException, IOException {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"/configs/remote-consumer.xml"});
//        context.start();
//        DubboService dubboService=(DubboService) context.getBean("dubboService");
//        for(int i=0;i<100;i++){
//            dubboService.doSomeThing();
//        }

        String url="http://localhost:8080/WebDesign/jsonContnet.do";
        ClientHttpRequest clientHttpRequest=new SimpleClientHttpRequestFactory().createRequest(new URI(url), HttpMethod.GET);
        List<MediaType> mediaTypeList=new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_XML);
        clientHttpRequest.getHeaders().setAccept(mediaTypeList);
        clientHttpRequest.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        clientHttpRequest.getHeaders().set("Accept-Charset","UTF-8");
        ClientHttpResponse response=clientHttpRequest.execute();
        if(response.getStatusCode()== HttpStatus.OK){
            byte[] buff=new byte[500];
            int len=0;
            response.getBody().read(buff);

            String a=new String(buff,"UTF-8");
            System.out.println(a);
        }else{
            System.out.println(response.getStatusCode()+":"+response.getStatusText());
        }



    }
}
