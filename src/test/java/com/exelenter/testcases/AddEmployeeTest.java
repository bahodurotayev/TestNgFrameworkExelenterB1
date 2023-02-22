package com.exelenter.testcases;

import com.exelenter.base.BaseClass;
import org.testng.annotations.Test;


public class AddEmployeeTest extends BaseClass {
    @Test(groups = "smoke")
    public void addEmployeeTest() {
        loginPage.loginToWebsite("username", "password");
        pimPage.navigateToAddEmployee();
        System.out.println("New Employee ID: " + addEmployeePage.employeeId.getAttribute("value"));
        addEmployeePage.addEmployee("empFirstname", "empLastname", "filePath"); // This method will add a new employee
    }

}
