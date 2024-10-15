package network;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedServerMain {

    public static void main(String[] args) throws IOException {

        //Create a thread pool
        ExecutorService thrPool = Executors.newFixedThreadPool(2);

        String threadName = Thread.currentThread().getName();

        // Set the default port to 3000
        int port = 3000;
        if (args.length > 0);
        port = Integer.parseInt(args[0]);

        // Create a server port , TCP
        ServerSocket server = new ServerSocket(port);

        while (true) {
            // Wait for an incoming connection in the form of a socket
            System.out.printf("[%s]Waiting for connection on port %d\n", threadName, port);
            Socket sock = server.accept(); // Server side socket which is the most crucial line

            System.out.println("Got a new connection:");
            
            //Create a worker to handle the work
            ClientHandler handler = new ClientHandler(sock);
            // Pass the work to the worker
            thrPool.submit(handler);

        }
    }
}
