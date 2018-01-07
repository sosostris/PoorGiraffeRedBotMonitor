/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author XUZH0001
 */
@Stateless
public class MovementBean {

    private static String ip = "192.168.1.99";

    public static void moveUp() {
        URLConnection connection = null;
        try {
            connection = new URL("http://" + ip + "/UP").openConnection();
            connection.setConnectTimeout(3000);
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
        } catch (Exception ex) {
            Logger.getLogger(LEDBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void moveDown() {
        URLConnection connection = null;
        try {
            connection = new URL("http://" + ip + "/DOWN").openConnection();
            connection.setConnectTimeout(3000);
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
        } catch (Exception ex) {
            Logger.getLogger(LEDBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void moveLeft() {
        URLConnection connection = null;
        try {
            connection = new URL("http://" + ip + "/LEFT").openConnection();
            connection.setConnectTimeout(3000);
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
        } catch (Exception ex) {
            Logger.getLogger(LEDBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void moveRight() {
        URLConnection connection = null;
        try {
            connection = new URL("http://" + ip + "/RIGHT").openConnection();
            connection.setConnectTimeout(3000);
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
        } catch (Exception ex) {
            Logger.getLogger(LEDBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void stop() {
        URLConnection connection = null;
        try {
            connection = new URL("http://" + ip + "/STOP").openConnection();
            connection.setConnectTimeout(3000);
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
        } catch (Exception ex) {
            Logger.getLogger(LEDBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void moveByUDP(String command) {
        String hostIp = "192.168.1.99";
        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket(1234);
        } catch (SocketException ex) {
            Logger.getLogger(MovementBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        InetAddress IPAddress = null;
        try {
            IPAddress = InetAddress.getByName(hostIp);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MovementBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] sendData = new byte[8];
        byte[] receiveData = new byte[8];
        sendData = command.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 2390);
        try {
            clientSocket.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(MovementBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            clientSocket.setSoTimeout(1000);
            while (true) {
                try {
                    clientSocket.receive(receivePacket);
                    byte[] buffer = receivePacket.getData();
                    System.out.println("FROM SERVER byte 1: " + buffer[0]);
                    System.out.println("FROM SERVER byte 2: " + buffer[1]);
                } catch (SocketTimeoutException e) {
                    break;  // Closing here would cause a SocketException
                } catch (IOException ex) {
                    Logger.getLogger(MovementBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(MovementBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        clientSocket.close();
    }
    
    public static boolean queryDistance() {
        int distance1 = 0;
        int distance2 = 0;
        String command = "CM";
        String hostIp = "192.168.1.99";
        DatagramSocket clientSocket = null;
        try {
            clientSocket = new DatagramSocket(1234);
        } catch (SocketException ex) {
            Logger.getLogger(MovementBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        InetAddress IPAddress = null;
        try {
            IPAddress = InetAddress.getByName(hostIp);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MovementBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] sendData = new byte[8];
        byte[] receiveData = new byte[8];
        sendData = command.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 2390);
        try {
            clientSocket.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(MovementBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            clientSocket.setSoTimeout(1000);
            while (true) {
                try {
                    clientSocket.receive(receivePacket);
                    byte[] buffer = receivePacket.getData();
                    distance1 = buffer[2];
                    distance2 = buffer[3];
                    System.out.println("FROM SERVER byte 1: " + distance1 + "cm");
                    System.out.println("FROM SERVER byte 1: " + distance2 + "cm");
                } catch (SocketTimeoutException e) {
                    break;  // Closing here would cause a SocketException
                } catch (IOException ex) {
                    Logger.getLogger(MovementBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(MovementBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        clientSocket.close();
        return (distance1 < 20 || distance2 < 20);
    }
}
