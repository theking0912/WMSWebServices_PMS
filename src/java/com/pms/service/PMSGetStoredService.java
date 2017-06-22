package com.pms.service;

import com.tools.DBHelper;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;

/**
 * Created by theking on 2017-03-23.
 */
@WebService
public class PMSGetStoredService {

    public String getStoredItem(String ClientCode, String ItemNumber) {

        String json = null;
        String Item_Number = ClientCode + '-' + ItemNumber + '%';
        ArrayList sqlValues = new ArrayList();
        sqlValues.add(ClientCode);
        sqlValues.add(Item_Number);
        //声明sql
        String sql = "select tim.storage_type\n" +
                "\t   ,case when \n" +
                "\t\t\ttsi.status = 'A' \n" +
                "\t\tthen '库存可用'\n" +
                "\t\tend as stored_status\n" +
                "\t   ,isnull(ta.status,'未预约') allo_status\n" +
                "\t   ,tsi.location_id\n" +
                "\t   ,tsi.item_number\n" +
                "\t   ,tim.description\n" +
                "\t   ,case when\n" +
                "\t    tsi.status = 'A' \n" +
                "\t\tthen\n" +
                "\t\t\tactual_qty\n" +
                "\t\telse\n" +
                "\t\t\t0\n" +
                "\t\tend as actual_qty\n" +
                "\t   ,isnull(ta.allocated_qty,0) as allocated_qty\n" +
                "\t   ,case when\n" +
                "\t    tl.type = 'A' \n" +
                "\t\tthen\n" +
                "\t\t\tactual_qty\n" +
                "\t\telse\n" +
                "\t\t\t0\n" +
                "\t\tend as unuplocation\n" +
                "\t   ,tl.type \n" +
                " from t_stored_item tsi\n" +
                "  inner join t_item_master tim\n" +
                "     on tsi.item_number = tim.item_number\n" +
                "   left join tbl_allocation ta\n" +
                "     on tsi.item_number = ta.item_number\n" +
                "\tand tsi.location_id = ta.location_id\n" +
                "\tand isnull(ta.status,'U') = 'U'\n" +
                "   left join t_location tl\n" +
                "     on tl.location_id = tsi.location_id\n" +
                "  where tl.type not in ('F','T','X','W','D')\n" +
                "\tand tsi.status = 'A'\n" +
                "\tand tim.client_code = ?\n" +
                "\tand tsi.item_number like ?\n";
        //声明数据库操作对象
        DBHelper dbHelper = new DBHelper();
        dbHelper.setSql(sql);
        dbHelper.setSqlValues(sqlValues);
        json = dbHelper.executeQuery();
        return json;
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9001/Service/PMSGetStoredService", new com.pms.service.PMSGetStoredService());
        System.out.println("Services Success!");
    }

}


