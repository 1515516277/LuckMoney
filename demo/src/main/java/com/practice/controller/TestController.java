package com.practice.controller;

import com.practice.annotation.Exceptions;
import com.practice.design.PriceFactory;
import com.practice.pojo.dto.TestDto;
import com.practice.service.ServiceLocator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private PriceFactory priceFactory;
    @Autowired
    private ServiceLocator serviceLocator;

    @Exceptions(age = 1)
    @RequestMapping("test")
    public void testMap() {
//        serviceLocator.getMap().forEach((key,val)->{
//            System.out.println(key+"---"+val);
//        });
        System.out.println("xxxxxxxxxxxx");

    }

    @RequestMapping("buy")
    public void buy() throws Exception {
        System.out.println(serviceLocator.getCallPrice(1000.0).callPrice(1000));
    }

    @Test
    public void test() {
//        Integer[] integers={1,2,3,4,5,6,7};
//        List<Integer> list=new ArrayList<>(Arrays.asList(integers));
//        int sum = list.stream().mapToInt(integer -> integer).sum();
//        System.out.println(sum);
        String url="http://localhost:8444/user/personalCenter.html";
        String url2=url.replace("http://","https://");
        System.out.println(url2);
    }



    /**
     * 去掉指定字符串的开头的指定字符
     * @param stream 原始字符串
     * @param first 首字符
     * @param end 结尾字符
     * @return
     */
    public  String StringStartTrim(String stream, String first,String end) {
        if (StringUtils.isEmpty(stream) || stream.length() <= 1) {
            stream="";
        } else {
            String str=stream.substring(0,1);
            if(first.equals(str)){
                stream=stream.substring(1);
            }
            str=stream.substring(stream.length()-1);
            if(end.equals(str)){
                stream=stream.substring(0,stream.length()-1);
            }
        }
        return stream;
    }


    public List<String> obj(Object o){
        return new ArrayList<>();
    }

    private void getTest(TestDto testDto) {
        if ("".equals("")) {
            testDto.setId(1).setName("x");
        }
    }

    private TestDto getTestDto() {
        return new TestDto();
    }

    public <T> TestController() {

    }






}
