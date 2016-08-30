package ua.com.itproekt.gup.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * It connects to the server and passes the messages
 */

@Configuration
@PropertySource("classpath:properties/tcp.properties")
public class TcpNotificationClient {

    @Autowired
    Environment env;

//    public static void main(String[] args) {
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TcpNotificationClient.class);
//        TcpNotificationClient tcpNotificationClient = applicationContext.getBean(TcpNotificationClient.class);
//        try {
//            tcpNotificationClient.transport("{\"from\":\""+InetAddress.getLocalHost().getHostAddress()+"\",\"message\":\"This is a TcpNotificationClient\"}");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void transport(String notification) throws IOException {
        String host = env.getProperty("tcp.server.host");
        int port = Integer.valueOf(env.getProperty("tcp.server.port"));

        /* Create a socket to receive a pair of host:port */
        Socket fromServer = null;
        try {
            fromServer = new Socket(host, port);
        } catch (UnknownHostException e) {
            throw new UnknownHostException("Unknown host: " + host);
        } catch (IOException e) {
            throw new IOException("I/O Error creating socket " + host + ":" + port);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(notification.getBytes())));

      /* Get the output stream, which will be transmitted through the messages to the server */
        OutputStream out = null;
        try {
            out = fromServer.getOutputStream();
        } catch (IOException e) {
            throw new IOException("Failed to get the output.");
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        String ln = null;
        try {
            while ((ln = reader.readLine()) != null) {
                writer.write(ln + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            throw new IOException("Error while writing the message.");
        }

        writer.close();
        fromServer.close();
    }

}
