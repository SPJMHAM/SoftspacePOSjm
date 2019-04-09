package com.example.softspaceposjm.Model;
// EditText edtUserName,edtICno,edtAddress,edtPassword,edtConfirmPassword,edtEmail;
public class User {
    private String UserName;
    private String ICno;
    private String Address;
    private String Password;

    private String Email;
    public User(){

    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    /*
    *   public User(String name, String password, String email,String image) {
            this.name = name;
            Password = password;
            Email = email;
        }
    *
    * */
public User( String UserName,String ICno, String Address,String Password, String Email){
    this.UserName = UserName;
    this.ICno = ICno;
    this.Address = Address;
    this.Password = Password;

    this.Email = Email;
}




    public String getICno() {
        return ICno;
    }

    public void setICno(String ICno) {
        this.ICno = ICno;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }



    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
