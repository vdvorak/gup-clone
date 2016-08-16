package ua.com.itproekt.gup.service.socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * It connects to the server and passes the messages
 */

@Configuration
//@PropertySource("classpath:tcp.properties")
public class TcpNotificationClient {

    private TcpNotificationClient instance;

    private TcpNotificationClient(){
    }

    public static TcpNotificationClient getInstance(){
        return new TcpNotificationClient();
    }

//    @Value("${tcp.server.host}")
    private String DEFAULT_HOST = "localhost";

//    @Value("${tcp.server.port}")
    private int DEFAULT_PORT = 3071;

    public void transport(String notification) throws IOException {
        /* Create a socket to receive a pair of host:port */
        Socket fromServer = null;
        try {
            fromServer = new Socket(DEFAULT_HOST, DEFAULT_PORT);
        } catch (UnknownHostException e) {
            throw new UnknownHostException("Unknown host: " + DEFAULT_HOST);
        } catch (IOException e) {
            throw new IOException("I/O Error creating socket " + DEFAULT_HOST + ":" + DEFAULT_PORT);
        }
        
      /* Get the output stream, which will be transmitted through the messages to the server */
        OutputStream out = null;
        try {
            out = fromServer.getOutputStream();
        } catch (IOException e) {
            throw new IOException("Failed to get the output.");
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        try {
            while (notification != null) {
                writer.write(notification);
                writer.flush();
            }
        } catch (IOException e) {
            throw new IOException("Error while writing the message.");
        }

        writer.close();
        fromServer.close();
    }

}

