/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MD5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Nguyen Hoang Thong
 */
public class Checksum {
    
    public String MD5(File file) throws NoSuchAlgorithmException, IOException
    {
        FileInputStream fis = new FileInputStream(file);
        MessageDigest md = MessageDigest.getInstance("MD5");
        //FileInputStream fis = new FileInputStream("c:\\loging.log");

        byte[] dataBytes = new byte[1024];

        int nread = 0;
        while ((nread = fis.read(dataBytes)) != -1) {
          md.update(dataBytes, 0, nread);
        };
        byte[] mdbytes = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        

        return sb.toString();
    }
    public void ghiFile(String path,String value) throws IOException{
		File f=new File(path);
		if(!f.exists()){
			f.createNewFile();
		}
		FileOutputStream out=new FileOutputStream(f,true);
		BufferedWriter br=new BufferedWriter(new OutputStreamWriter(out));
		br.write(value);
		br.close();
	}
}
