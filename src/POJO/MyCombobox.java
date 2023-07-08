/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

/**
 *
 * @author ThinhDN
 */
public class MyCombobox {
    private Object key;
    private Object value;

    public MyCombobox(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
    @Override
    public String toString(){
        return value.toString();
    }
    public String MaString(){
        return key.toString();
    }
     public String TenString(){
        return value.toString();
    }
}
