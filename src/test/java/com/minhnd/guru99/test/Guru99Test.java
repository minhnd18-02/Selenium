/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.minhnd.guru99.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author ADMIN
 */
public class Guru99Test {

    private static WebDriver myBrowser; // khai báo biến trỏ vào trình duyệt

    @BeforeAll // hàm này sẽ chạy trc tất cả các @Test
    // thường dùng để khởi động 1 điều gì đó
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        myBrowser = new ChromeDriver(); //tạo object trình duyệt trong RAM
    }

    @AfterAll // hàm này sẽ chạy sau khi tất cả các @Test đã chạy xong
    // thường dùng để dọn dẹp
    public static void tearDownClass() throws InterruptedException {
        Thread.sleep(2000);// app nghỉ 2s
        myBrowser.close();//dọn dẹp trình duyệt, tắt trình duyệt
                            // don rác trong ram luôn (webdriver.exe)
    }

    @Test // bài PE môn JavaWeb, thầy yêu cầu sau khi Login thành công thì chào
    //fullname, với bài BankGuru99, login thành công thì chào
    // managerID:<username login vào>
    public void checkGuru99Login() throws InterruptedException {
        myBrowser.get("https://demo.guru99.com/V4/");
    
            
        //đi bắt 2 ô nhập user/pass
        //bắt đc 1 tag là 1 object thuộc WebElement
            
        WebElement user = 
                myBrowser.findElement(By.xpath("//input[@name='uid']"));
        user.sendKeys("mngr510525");
        
        WebElement pass = 
                myBrowser.findElement(By.name("password"));
        pass.sendKeys("gEqyrYj");
        
        WebElement btnLogin = 
                myBrowser.findElement(By.name("btnLogin"));
        btnLogin.click();
        // login mà thành công thì chuyển trang và say hello
        // trong bài này thì login thành công thì trang welcome trả về 
        // với câu chào Manger Id : <username>
        // chuyển trang nếu mạng lag chưa kịp trả trang về trình duyệt 
        // mà câu lệnh dưới đã đi tìm tag welcome ở trong myBrowser
        // sẽ bị báo lỗi ko thấy TAG, do JVM cứ chạy lệnh thôi, ko chờ
        //trình duyệt đổi trang xong!!!
        //CHỗ nào có chuyển trang, Bắt SLENIUM, JVM CHỜ 1 PHÚT
        //GỌI LÀ WAIT - CHỜ, ĐỂ ĐỒNG BỘ!!!
        Thread.sleep(3000);
        
        WebElement welcomeMsg = 
                myBrowser.findElement(By.cssSelector("tr[class='heading3'] td"));
        System.out.println("In thu Welcome: " + welcomeMsg.getText());
        
        //chạy ngon thì in ra đc test lấy từ trang web
        //ta viết code dùng Selenium để lấy data trong trang web
        // code này gọi là con BOT để cào đata CRAWL (V), CRAWLER (N)
        
        assertEquals("Manger Id : mngr510525", welcomeMsg.getText());
    }
    
    // DDT
    public static Object[][] initData(){
        Object[][] account = {
                                {"mngr510525", "gEqyrYj"},
                                {"mngr510534", "ErYrEvy"}
                             };
        return account;
    }
    @ParameterizedTest
    @MethodSource(value = "initData")
    public void checkGuru99LoginDDT(String username, String password) throws InterruptedException {
        myBrowser.get("https://demo.guru99.com/V4/");
        //đi bắt 2 ô nhập user/pass
        //bắt đc 1 tag là 1 object thuộc WebElement
            
        WebElement user = 
                myBrowser.findElement(By.xpath("//input[@name='uid']"));
        user.sendKeys(username);
        
        WebElement pass = 
                myBrowser.findElement(By.name("password"));
        pass.sendKeys(password);
        
        WebElement btnLogin = 
                myBrowser.findElement(By.name("btnLogin"));
        btnLogin.click();
        // login mà thành công thì chuyển trang và say hello
        // trong bài này thì login thành công thì trang welcome trả về 
        // với câu chào Manger Id : <username>
        // chuyển trang nếu mạng lag chưa kịp trả trang về trình duyệt 
        // mà câu lệnh dưới đã đi tìm tag welcome ở trong myBrowser
        // sẽ bị báo lỗi ko thấy TAG, do JVM cứ chạy lệnh thôi, ko chờ
        //trình duyệt đổi trang xong!!!
        //CHỗ nào có chuyển trang, Bắt SLENIUM, JVM CHỜ 1 PHÚT
        //GỌI LÀ WAIT - CHỜ, ĐỂ ĐỒNG BỘ!!!
        Thread.sleep(3000);
        
        WebElement welcomeMsg = 
                myBrowser.findElement(By.cssSelector("tr[class='heading3'] td"));
        //chạy ngon thì in ra đc test lấy từ trang web
        //ta viết code dùng Selenium để lấy data trong trang web
        // code này gọi là con BOT để cào đata CRAWL (V), CRAWLER (N)        
        assertEquals("Manger Id : " + username, welcomeMsg.getText());
    }
    
}
// Framework và thư viện đều là file .jar,.dll, cung cấp các class cho dân dev import
//using vào và gọi xài

// nhưng thư viện thì xài hàm tự do, đặt việc gọi hàm của thư viện
// vào bất cứ chỗ nào, class nào khác phù hợp
// JDBC xài ở DBUtil hay ở đâu cũng đc
//FW: phải tuân thủ quy tắc viết, cấu trúc code, class đc đưa ra sẵn
// thường là theo các @ đã quy ước, @Test, @BeforeAll...
//EF Entity FW .NET nó sing sẵn cho mình cài DBContext...
// generate database (code first)  phải khai báo nhưingx thông số
// trong các Entity class theo quy ước

// 3 điểm bài thi PE
// Yêu cầu bạn viết ra test case để test chức năng login của GURU99 bank

//Test case #1: Check Guru99 login with valid account
//Step/Procedures:
// 1. Open a certain broser, e.g. Chrome
// 2. Type the following URL: https://demo.guru99.com/V4/
// 3. Hit enter to see the login page
// 4. Input a valid account to login, e.g. mngr510525/gEqyrYj
// 5. Hit enter to trigger the login process

// Expected result: 
// 3. The login page is shown with usename/pass/login button include
// 5. The Dashboard or Welcome screen is shown with welcome message
//      is under the format of "Manger Id : <username>"