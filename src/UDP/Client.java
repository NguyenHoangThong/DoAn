/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.io.File;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author Nguyen Hoang Thong
 */
public class Client {
    static UDP udp = new UDP();
    public static void main(String[] args)
    {
        System.out.print("1");
        try
        {  File f = new File("caro"); 
             udp.receiveUDP(f);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage().toString());
        }
         
    }
}
