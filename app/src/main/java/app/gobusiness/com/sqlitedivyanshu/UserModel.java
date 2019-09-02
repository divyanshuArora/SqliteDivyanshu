package app.gobusiness.com.sqlitedivyanshu;

public class UserModel {
    String name;
    String email;
    String number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    String user_image;

//    public void UserModelDash(String id,String name, String email, String number, String password,String user_image) {
//        this.id=id;
//        this.name = name;
//        this.email = email;
//        this.number = number;
//        this.password = password;
//        this.user_image=user_image;
//    }


    public  UserModel()
    {

    }





    public UserModel(String name, String email, String number, String password,String user_image) {

        this.name = name;
        this.email = email;
        this.number = number;
        this.password = password;
        this.user_image=user_image;


    }



   /* public UserModel(String number, String password) {
        this.number = number;
        this.password = password;
    }
*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;
}
