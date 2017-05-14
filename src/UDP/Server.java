/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.io.File;

/**
 *
 * @author Nguyen Hoang Thong
 */
public class Server {
    static UDP udp =new UDP();
    public static void main(String[] args)
    {
        try
        {
            File f = new File("caro.rar");
            udp.sendUDP(f,"localhost");
        }
        catch(Exception e)
        {
            
        }
    }
}
