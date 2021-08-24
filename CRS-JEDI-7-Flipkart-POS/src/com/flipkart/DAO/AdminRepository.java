package com.flipkart.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import com.flipkart.bean.Admin;

public class AdminRepository 
{
    ArrayList<Admin>  adminList = new ArrayList<>();
    HashMap<Integer, Admin> hm = new HashMap<>();
    int globalId = 50000;

    AdminRepository(){
        Admin admin1 = new Admin(101, "admin1", "Kaisar", "Tumkur", "kaisar@gmail.com", globalId);
        //adminList.add(admin1);
        hm.put(globalId, admin1);
        globalId++;

        Admin admin2 = new Admin(102, "admin2", "Atik", "Raipur", "atikaga@gmail.com", globalId);
        //adminList.add(admin2);
        hm.put(globalId, admin2);
        globalId++;
    }

    Admin getAdminById(int id)
    {
        return hm.containsKey(id) ? hm.get(id) : null;
    }

    int addAdmin(Admin admin)
    {
        admin.setAdminId(globalId);
        //adminList.add(admin);
        hm.put(globalId, admin);
        return globalId++;
    }

    Admin updateDetailsAdmin(Admin admin)
    {
        //Admin oldAdmin = hm.get(admin.getAdminId());
        hm.put(admin.getAdminId(), admin);
        return admin;
    }

    void deleteAdmin(int id)
    {
        hm.remove(id);
    }
}
