package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.*;
import java.util.Date;

public class ServerMain {

    public static void main(String[] args) throws IOException {

        String threadName = Thread.currentThread().getName();

        // Set the default port to 3000
        int port = 3000;
        if (args.length > 0);
        port = Integer.parseInt(args[0]);

        // Creates a server port , TCP to accept connection
        ServerSocket server = new ServerSocket(port);

        while (true) {
            // Wait for an incoming connection in the form of a socket
            System.out.printf("[%s]Waiting for connection on port %d\n", threadName, port);
            Socket sock = server.accept();  // Server side socket which is the most crucial line

            System.out.println("Got a new connection:");

            //Get the input stream
            InputStream is = sock.getInputStream();
            Reader reader = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(reader);

            //Get the output stream 
            OutputStream os = sock.getOutputStream(); // Input output stream deal with bytes
            Writer writer = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(writer);

            //Read from the client
            String fromClient = br.readLine();

            System.out.printf(">>>> CLIENT: %s\n", fromClient);

            //Process the data
            fromClient = (new Date()).toString() + " " + fromClient.toUpperCase();

            // Write results back to client
            bw.write(fromClient);
            bw.newLine();
            bw.flush();
            os.flush();

            os.close();
            is.close();
            sock.close();

        }
    }
}
