package pt.tecnico.sockets;

import java.io.*;
import java.net.Socket;

public class SocketClient {

  public static void main(String[] args) throws IOException {
    // Check arguments
    if (args.length < 3) {
      System.err.println("Argument(s) missing!");
      System.err.printf("Usage: java %s host port text%n", SocketClient.class.getName());
      return;
    }

    // First argument is the server host name
    final String host = args[0];
    // Second argument is the server port
    // Convert port from String to int
    final int port = Integer.parseInt(args[1]);

    // Concatenate following arguments using a string builder
    StringBuilder sb = new StringBuilder();
    for (int i = 2; i < args.length; i++) {
      sb.append(args[i]);
      if (i < args.length - 1) {
        sb.append(" ");
      }
    }
    final String text = sb.toString();

    // Create client socket
    Socket socket = new Socket(host, port);
    System.out.printf("Connected to server %s on port %d %n", host, port);

    // Create stream to send data to server
    DataOutputStream out = new DataOutputStream(socket.getOutputStream());

    // Send text to server as bytes
    out.writeBytes(((Integer) text.length()).toString() + '\n');
    out.writeBytes(text + '\n');
    System.out.println("Sent text: " + text);

    DataInputStream in = new DataInputStream(socket.getInputStream());
    System.out.printf("The Server Response is %d\n", in.readInt());

    // Close client socket
    socket.close();
    System.out.println("Connection closed");
  }
}
