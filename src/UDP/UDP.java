/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import Encrypt.CryptoUtils;
import MD5.Checksum;
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
    static String KEY = "nguyenhoangthong";
    static Checksum cs = new Checksum();
    public static void sendUDP(File f,String host)
    {
        
        try
        {
            //tao md5 hash
            String checksum =  cs.MD5(f);
            cs.ghiFile(host+".txt", f.getName().toString()+": "+checksum+"\r\n");
            //------------------------------------------//
            //ma hoa file
            File encryptedFile = new File("document.encrypted");
            CryptoUtils.encrypt(KEY, f, encryptedFile);
            DatagramSocket ds = new DatagramSocket();
            try{
            //init
                
                DatagramPacket receiveDP,sendDP;            
                InetAddress add = InetAddress.getByName(host);
                FileInputStream fis = new FileInputStream(encryptedFile);
                byte send[]= new byte[1024];
                byte receive[]= new byte[1024];
                ds.setSoTimeout(1000*60);
                //-----------------------------------//
                //send message
                String s = " day la demo test";            
                send = checksum.getBytes();
                System.out.println("bat dau gui");
                sendDP = new DatagramPacket(send, send.length, add, PORT);
                ds.send(sendDP);
                System.out.println("Da gui");

                //-----------------------------------//
                //Nhan phan hoi tu server
                receiveDP = new DatagramPacket(receive , receive.length);
                System.out.println("Đang đợi phản hồi...");
                ds.receive(receiveDP);
                String input = new String(receiveDP.getData());
                System.out.println(input);
                //-----------------------------------------//
                //gui file den dia chi           
                //read file
                int len = fis.available();
                byte b1[] = new byte[len];
                fis.read(b1);
                fis.close();
                //send file           
                DatagramPacket dp = new DatagramPacket(b1,len,add, PORT);
                ds.send(dp);
                //log
                //------------------------------------------------------------------//
                //ket thuc
                ds.close();
                System.out.println("Send "+f.getPath().toString()+" "+add+" "+PORT+" "+len);
                ///---------------------------------------------------------------------------//
            }
            catch(SocketTimeoutException ste)
            {
                ds.close();
                System.out.println("da co loi xay ra");
            }
            //            int len2 = send.length;
//            DatagramSocket ds2 = new DatagramSocket();
//            DatagramPacket dp2 = new DatagramPacket(send, len2, add, PORT);
//            ds2.send(dp2);
            //read file
            //File f = new File(path);
        } 
        catch(Exception e)
        {
            System.out.println(e.getMessage().toString());
        }
    }
    public static String receiveUDP(File file)
    {
        String out = "";
        
        try
        {
            DatagramSocket receiveSK = new DatagramSocket(PORT);
            try{
                FileOutputStream f = new FileOutputStream(file);
                
                DatagramPacket receiveDP,sendDP,fileDP;
                byte send1[] = new byte[1024];
                byte receive[]= new byte[1024];
                byte filebyte[]= new byte[1024*1024*100];
                System.err.println("Bat dau nhan tin");
                receiveSK.setSoTimeout(1000*60);
                //---------------------------------------------------------//
                //nhan tu client
                receiveDP = new DatagramPacket(receive, receive.length);
                receiveSK.receive(receiveDP);
                out = new String(receiveDP.getData());
                int port = receiveDP.getPort();
                InetAddress address = receiveDP.getAddress();
                System.out.println(out);
                //---------------------------------------------------------//
                //gui tra client
                String ss = "day la string tra ve";
                send1 = ss.getBytes();
                sendDP = new DatagramPacket(send1, send1.length, address, port);
                receiveSK.send(sendDP);
                System.out.println("da gui tra ve");
                //-------------------------------------------------------//
                //nhan file
                fileDP = new DatagramPacket(filebyte, filebyte.length);
                receiveSK.receive(fileDP);
                System.out.println("da nhan file");
                byte b2[] = fileDP.getData();
                f.write(b2,0,fileDP.getLength());
                f.close();
                System.out.println("Da luu file thanh cong");
                //-------------------------------------------------------//
                //dong ket noi
                receiveSK.close();
            }
            catch(SocketTimeoutException ske)
            {
                receiveSK.close();
                System.err.println("da loi xay ra");
                
            }            
        }
        catch(Exception e)
        {
            
            System.err.println(e.getMessage().toString());
        }
        return out;
    }

    
}
