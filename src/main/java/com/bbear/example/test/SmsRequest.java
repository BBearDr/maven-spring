package com.bbear.example.test;

/**
 * @author junxiongchen
 * @date 2018/10/09
 */
public class SmsRequest {
    private String id;
    private String name;
    private String address;

    public SmsRequest(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmsRequest that = (SmsRequest) o;

//        if (!id.equals(that.id)) return false;
//        if (!name.equals(that.name)) return false;
        return address.equals(that.address);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
       /* result = 31 * result + name.hashCode();
        result = 31 * result + address.hashCode();*/
        return result;
    }
}
