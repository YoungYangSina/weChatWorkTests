package com.test.weChatWork.AddressBook;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;





import static org.apache.commons.io.FileUtils.readFileToString;

public class UserData {


    public static JSONArray getUserData(){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        ArrayList key = new ArrayList();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\ProjectFile\\weChatWorkTests\\src\\main\\resources\\data\\testFiles.csv"));//换成你的文件名
            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            Integer whichLine = 1;
            while((line=reader.readLine())!=null){

                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                if(whichLine == 1){
                    for(int a=0;a<item.length;a++){
                        key.add(item[a]);
                        whichLine++;
                    }
                }else {
                    ArrayList content = new ArrayList();
                    for(int i=0;i<item.length;i++){
                        content.add(item[i]);

                    }
//                    System.out.println(key);
//                    System.out.println(content);
                    for(int j=0;j<key.size();j++){
                        String keytitle = key.get(j).toString();
                        String value = content.get(j).toString();
//                        System.out.println(keytitle);
//                        System.out.println(value);
                        jsonObject.put(keytitle, value);
                    }
                    jsonArray.add(jsonObject);
//                    System.out.println(jsonObject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(jsonArray);
//        System.out.println(jsonArray.getJSONObject(0));
//        System.out.println(jsonArray.getJSONObject(1));
        return jsonArray;

    }

//    public static JSONObject getJsonUserData(){
//
//        File file=new File("D:\\ProjectFile\\weChatWorkTests\\src\\main\\resources\\data\\userData.json");
//        String content= null;
//        try {
//            content = readFileToString(file,"UTF-8");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        JSONObject jsonObject=new JSONObject(content);
////            System.out.println(jsonObject);
//        return jsonObject;
////            System.out.println("姓名是："+jsonObject.getString("name"));
////            System.out.println("年龄："+jsonObject.getDouble("age"));
////            System.out.println("学到的技能："+jsonObject.getJSONArray("major"));
////            System.out.println("国家："+jsonObject.getJSONObject("Nativeplace").getString("country"));
//    }



}
