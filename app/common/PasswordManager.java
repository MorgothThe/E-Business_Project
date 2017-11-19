package common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordManager {

    private String hashAlgorithm;

    public PasswordManager(){
        hashAlgorithm = "SHA-256";
    }

    public PasswordManager(String hashAlgorithm){
        this.hashAlgorithm = hashAlgorithm;
    }

    public String generateSalt(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return new String(bytes);
    }

    private String byteToString(byte[] byteArray){
        String text = new String(byteArray);
        text = text.replace('\0', ' ');
        return text;
    }

    private byte[] stringToByte(String text){
        text = text.replace('\0', ' ');
        return text.getBytes();
    }


    public String hashPassword(String password, String salt){
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance(hashAlgorithm);
        }
        catch (NoSuchAlgorithmException e) {
            System.err.println("I'm sorry, but " + hashAlgorithm + " is not a valid message digest algorithm");
        }
        byte[] bytePassword = stringToByte(password);
        byte[] byteSalt = stringToByte(salt);

        md.update(byteSalt);
        md.update(bytePassword);
        byte[] hashedPassword = md.digest();

        return byteToString(hashedPassword);

    }

    public Boolean isPasswordCorrect(String password, String salt, String hash){
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance(hashAlgorithm);
        }
        catch (NoSuchAlgorithmException e) {
            System.err.println("I'm sorry, but " + hashAlgorithm + " is not a valid message digest algorithm");
        }

        byte[] bytePassword = stringToByte(password);
        byte[] byteSalt = stringToByte(salt);

        md.update(byteSalt);
        md.update(bytePassword);
        String hashedPassword = byteToString(md.digest());

        if(hashedPassword.contentEquals(hash)){
            return true;
        }
        else{
            return false;
        }

    }

}
