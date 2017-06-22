package com.pms.entity;

/**
 * Created by theking on 2017-03-23.
 */
public class StoredFormWMS {
    private String storage_type;		    //温层
    private String stored_status;			//库存状态
    private String allo_status;			    //预约状态
    private String location_id;			    //货位
    private String item_number;			    //物料号
    private String description;			    //物料描述
    private int actual_qty;			    //库存数量
    private int allocated_qty;			//预约库存数量
    private int unuplocation_qty;		//未上架数量
    private String type;					//货位类型

    public String getStorage_type() {
        return storage_type;
    }

    public void setStorage_type(String storage_type) {
        this.storage_type = storage_type;
    }

    public String getStored_status() {
        return stored_status;
    }

    public void setStored_status(String stored_status) {
        this.stored_status = stored_status;
    }

    public String getAllo_status() {
        return allo_status;
    }

    public void setAllo_status(String allo_status) {
        this.allo_status = allo_status;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getItem_number() {
        return item_number;
    }

    public void setItem_number(String item_number) {
        this.item_number = item_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getActual_qty() {
        return actual_qty;
    }

    public void setActual_qty(int actual_qty) {
        this.actual_qty = actual_qty;
    }

    public int getAllocated_qty() {
        return allocated_qty;
    }

    public void setAllocated_qty(int allocated_qty) {
        this.allocated_qty = allocated_qty;
    }

    public int getUnuplocation_qty() {
        return unuplocation_qty;
    }

    public void setUnuplocation_qty(int unuplocation_qty) {
        this.unuplocation_qty = unuplocation_qty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public StoredFormWMS() {
    }

    @Override
    public String toString() {
        return "StoredFormWMS{" +
                "storage_type='" + storage_type + '\'' +
                ", stored_status='" + stored_status + '\'' +
                ", allo_status='" + allo_status + '\'' +
                ", location_id='" + location_id + '\'' +
                ", item_number='" + item_number + '\'' +
                ", description='" + description + '\'' +
                ", actual_qty=" + actual_qty +
                ", allocated_qty=" + allocated_qty +
                ", unuplocation_qty=" + unuplocation_qty +
                ", type='" + type + '\'' +
                '}';
    }
}
