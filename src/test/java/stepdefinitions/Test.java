package stepdefinitions;

import java.util.regex.Pattern;

public class Test {
    public static void main(String... a){
        String pattern = "@[a-z[^0-9]]"; //[a-z0-9[^A-Z]]
        String email = "@a";//saravananitindia3
        if(Pattern.compile(pattern).matcher(email).find()){
            System.out.println("OKK");
        }else {
            System.out.println("NOO");
        }
    }
}
