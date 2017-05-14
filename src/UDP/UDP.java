/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 *
 * @author Nguyen Hoang Thong
 */
public class UDP {
    static int PORT = 2020;
    public static void sendUDP(File f,String host)
    {
        
        try
        {
            //read file
            //File f = new File(path);
            FileInputStream fis = new FileInputStream(f);
            int len = fis.available();
            byte b1[] = new byte[len];
            fis.read(b1);
            fis.close();
            //-----------------------------------//
            //send file
            DatagramSocket ds = new DatagramSocket();
            InetAddress add = InetAddress.getByName(host);
            DatagramPacket dp = new DatagramPacket(b1,len,add, PORT);
            ds.send(dp);
            //log
            System.out.println("Send "+f.getPath().toString()+" "+add+" "+PORT+" "+len);
            
        } 
        catch(Exception e)
        {
            System.out.println(e.getMessage().toString());
        }
    }
    public static void receiveUDP(File file)
    {
        System.out.print("1");
        try
        {
            System.out.print("2");
            DatagramSocket ds = new DatagramSocket(2020);
           // ds.setSoTimeout(10000);
            byte b1[] = new byte[1024*1024*100];
            System.out.print("3");
	    DatagramPacket goinhan = new DatagramPacket(b1, 60000);
            System.out.print("4");
	// Nhan goi tin
           // try{
            ds.receive(goinhan);
            System.out.print("5");
//            }
//            catch(SocketTimeoutException ste)
//            {
//                System.exit(1);
//            }
                        
            System.out.println("Da nhan"+goinhan.getAddress()+" "+goinhan.getPort());
	// Luu ket qua
            System.out.print("6");
            
	    FileOutputStream f = new FileOutputStream(file);
	    byte b2[] = goinhan.getData();
            f.write(b2,0,goinhan.getLength());
            f.close();
            System.out.println("Da luu file thanh cong");
		
			// Dong UDP Socket
            ds.close();
        }
        catch(Exception e)
        {
            
            System.err.println(e.getMessage().toString());
        }
    }

    
}
