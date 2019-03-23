package com.flamezz.creditmanagementapp;

public class Profile {

    private String name;
    private String  email;
    private String phone;
    private String  currentcredit;

Profile()
{

}
    Profile(String name,String email,String phone,String currentcredit)
    {
        this.name=name;
        this.email=email;
        this.currentcredit=currentcredit;
        this.phone=phone;
    }




    public void setName(String name)
    {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCurrentcredit() {
        return currentcredit;
    }


}
