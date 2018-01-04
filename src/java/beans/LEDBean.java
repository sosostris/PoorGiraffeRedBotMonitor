/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author XUZH0001
 */
public class LEDBean {

    private static String ip = "192.168.1.83";

    public static void turnOnLED() {
        URLConnection connection = null;
        try {
            connection = new URL("http://" + ip + "/H").openConnection();
        } catch (Exception ex) {
            Logger.getLogger(LEDBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print response
            System.out.println(response.toString());
        } catch (IOException ex) {
            Logger.getLogger(LEDBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void turnOffLED() {
        URLConnection connection = null;
        try {
            connection = new URL("http://" + ip + "/L").openConnection();
        } catch (Exception ex) {
            Logger.getLogger(LEDBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // print response
            System.out.println(response.toString());
        } catch (IOException ex) {
            Logger.getLogger(LEDBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
