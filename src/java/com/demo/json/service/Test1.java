package com.demo.json.service;

import com.demo.json.entity.Person;
import com.demo.json.tools.JsonTools;
import net.sf.json.JSONObject;

public class Test1
{

    public static void main(String[] args)
    {
        String msg = "";

        JsonService service = new JsonService();
        Person person = service.getPerson();

        // һ��Person����
        msg = JsonTools.createJsonString("aPerson", person);
        System.out.println(msg);

        // һ��List������������Person����
        msg = JsonTools.createJsonString("Persons", service.getPersonsList());
        System.out.println(msg);

        // һ��List������������String����
        msg = JsonTools.createJsonString("StringsList",
                service.getStringsList());
        System.out.println(msg);

        // һ��List������������Map����ÿ��Map�����д洢�����ɸ���ֵ��
        msg = JsonTools.createJsonString("MapsList", service.getMapsList());
        System.out.println(msg);

        JSONObject jsonObj = new JSONObject();
        String[] likes = new String[] { "JavaScript", "Skiing", "Apple Pie" };
        jsonObj.put("likes", likes);
        System.out.println(jsonObj);

    }

}