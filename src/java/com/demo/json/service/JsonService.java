package com.demo.json.service;

import com.demo.json.entity.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonService
{
    public JsonService()
    {
    }

    public Person getPerson()
    {
        Person person = new Person(001, "ZhangSan", "Beijing");

        return person;

    }

    public List<Person> getPersonsList()
    {
        List<Person> personsList = new ArrayList<Person>();
        Person person1 = new Person(001, "ZhangSan", "Beijing");
        Person person2 = new Person(002, "LiSi", "Tianjin");
        Person person3 = new Person(003, "WangWu", "Shanghai");

        personsList.add(person1);
        personsList.add(person2);
        personsList.add(person3);

        return personsList;
    }

    public List<String> getStringsList()
    {
        List<String> stringsList = new ArrayList<String>();

        stringsList.add("南京");
        stringsList.add("杭州");
        stringsList.add("承德");
        stringsList.add("哈尔滨");
        stringsList.add("重庆");

        return stringsList;
    }

    public List<Map<String, Object>> getMapsList()
    {
        List<Map<String, Object>> mapsList = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < 3; ++i)
        {
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("id", i);
            map.put("name", "Name_" + i);
            map.put("address", "Address_" + i);

            mapsList.add(map);
        }

        return mapsList;

    }

}