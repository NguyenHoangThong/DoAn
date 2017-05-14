/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encrypt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Nguyen Hoang Thong
 */
public class CryptoUtilsTest {
    public static void main(String[] args) throws FileNotFoundException {
        String key = "Mary has one cat";
        
        File inputFile = new File("2.jpg");
        //FileInputStream f =new FileInputStream(inputFile);
        File encryptedFile = new File("document.encrypted");
        File decryptedFile = new File("document.decrypted");
         
        try {
            CryptoUtils.encrypt(key, inputFile, encryptedFile);
            CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
//            try {
    // retrieve image
//                BufferedImage bi = ImageIO.read(decryptedFile);
//                File outputfile = new File("saved.png");
//                ImageIO.write(bi, "png", outputfile);
////writte any file
//                   FileInputStream fin = new FileInputStream(decryptedFile);
//                   byte b[] = new byte[(int)decryptedFile.length()];
//                   fin.read(b);
//
//                   File nf = new File("nf.jpg");
//                   FileOutputStream fw = new FileOutputStream(nf);
//                   fw.write(b);
//                   fw.flush();
//                   fw.close();
//            }catch (IOException e) {
//
//            }
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
       }
    }
}
