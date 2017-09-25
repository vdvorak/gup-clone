//package ua.com.itproekt.gup.socket;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.ServerSocket;
//import java.net.Socket;
//
///**
//* Слушает указанный в первом параметре порт и выводит все приходящие на него сообщения на консоль.
//*/
//public class TcpNotificationClientTest {
//
//    public static void main(String[] args) {
//        int port = DEFAULT_PORT;
//
//        ServerSocket serverSocket = null;
//        try {
//            serverSocket = new ServerSocket(port);
//        } catch (IOException e) {
//            System.out.println("занят: " + port);
//            System.exit(-1);
//        }
//
//        Socket clientSocket = null;
//        try {
//            clientSocket = serverSocket.accept();
//        } catch (IOException e) {
//            System.out.println("Ошибка при подключении к: " + port);
//            System.exit(-1);
//        }
//
//        InputStream in = null;
//        try {
//            in = clientSocket.getInputStream();
//        } catch (IOException e) {
//            System.out.println("Не удалось получить поток.");
//            System.exit(-1);
//        }
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        String ln = null;
//        try {
//            while ((ln = reader.readLine()) != null) {
//                System.out.println(ln);
//                System.out.flush();
//            }
//        } catch (IOException e) {
//            System.out.println("Ошибка при чтении сообщения.");
//            System.exit(-1);
//        }
//    }
//
//    private static final int DEFAULT_PORT = 3071;
//}
