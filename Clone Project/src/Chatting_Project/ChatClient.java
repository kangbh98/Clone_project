package Chatting_Project;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) throws Exception{
        String name = args[0];

        Socket socket = new Socket("127.0.0.1", 8888);

        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        String line = null;
        InputThread inputThread = new InputThread(in);
        inputThread.start();

        while((line = keyboard.readLine()) != null){
            out.println(name + " : "+ line );
            out.flush();
        }
    }
}

class InputThread extends Thread{
    BufferedReader in = null;
    public InputThread(BufferedReader in){
                this.in = in;
            }

    @Override
    public void run(){
                try{
                    String line = null;
                    while ((line = in.readLine())!= null){
                        System.out.println(line);
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
    }
}