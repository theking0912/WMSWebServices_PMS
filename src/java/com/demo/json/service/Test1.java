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

        // 一个Person对象
        msg = JsonTools.createJsonString("aPerson", person);
        System.out.println(msg);

        // 一个List，里面有若干Person对象
        msg = JsonTools.createJsonString("Persons", service.getPersonsList());
        System.out.println(msg);

        // 一个List，里面有若干String对象
        msg = JsonTools.createJsonString("StringsList",
                service.getStringsList());
        System.out.println(msg);

        // 一个List，里面有若干Map对象，每个Map对象中存储了若干个键值对
        msg = JsonTools.createJsonString("MapsList", service.getMapsList());
        System.out.println(msg);

        JSONObject jsonObj = new JSONObject();
        String[] likes = new String[] { "JavaScript", "Skiing", "Apple Pie" };
        jsonObj.put("likes", likes);
        System.out.println(jsonObj);

    }

}