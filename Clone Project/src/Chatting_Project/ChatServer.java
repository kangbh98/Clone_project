package Chatting_Project;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatServer {
    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(9999);
        List<PrintWriter> outList = Collections.synchronizedList(new ArrayList<>());

        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("접속 :"+socket);

            ChatThread chatThread = new ChatThread(socket, outList);
            chatThread.start();
        }
    }
}

class ChatThread extends Thread{

    private Socket socket;
    private List<PrintWriter> outList;
    private PrintWriter out;
    private BufferedReader in;

    public ChatThread(Socket socket,List<PrintWriter> outList){
        this.socket = socket;
        this.outList = outList;

        try {
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outList.add(out);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        //1 Socket 으로 부터 읽어 들일 수 있는 객체 얻는다
        //2 socket 쓰기위한 객체를 얻는다 (현재 열결된 클라이언트에게 쓰는 객체)
    }
    public void run(){

        //3 클라이언트가 보낸 메시지를 읽는다
        //4 접속한 모든 클라이언트에게 메시지를 보낸다.( 현재 접속된 모든 클라이언트에게 쓸 수 있는 객체가 필요하다.
        String line = null;
        try{
            while ((line = in.readLine()) != null){
                for(int i =0; i< outList.size();i++){ //접속한 모든 클라이언트에게 메시지를 전송한다.
                    PrintWriter o =outList.get(i);
                    o.println(line);
                    o.flush();
                }

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally { // 접속이 끊어질때
            try{
                outList.remove(out);
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            for(int i =0; i< outList.size();i++){ //접속한 모든 클라이언트에게 메시지를 전송한다.
                PrintWriter o =outList.get(i);
                o.println("Some client quit");
                o.flush();
            }
            try {
                socket.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

}