/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MD5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Nguyen Hoang Thong
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException, IOException
    {
        File file = new File("caro.rar");
        FileInputStream fis = new FileInputStream(file);
        Checksum cs = new Checksum();
        String s = cs.MD5(file);
        System.err.println(s);
    }
}
