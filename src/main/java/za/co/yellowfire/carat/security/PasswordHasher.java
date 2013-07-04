package za.co.yellowfire.carat.security;

import org.apache.shiro.crypto.hash.Sha256Hash;

public class PasswordHasher {
    public static void main(String[] args) throws Exception {
        System.out.println("password hash = " + new Sha256Hash("password").toHex());
    }
}
