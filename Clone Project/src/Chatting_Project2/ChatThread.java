package Chatting_Project2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatThread extends Thread{
    private String name;
    private BufferedReader br;
    private PrintWriter pw;
    private Socket socket;
    List<ChatThread> list;


    public ChatThread(Socket socket, List<ChatThread> list)throws Exception{
        this.socket = socket;
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.br = br;
        this.pw = pw;
        this.name = br.readLine();
        this.list = list;
        this.list.add(this);


    }
    public void sendMessage(String msg){
        pw.println(msg);
        pw.flush();
    }

    @Override
    public void run() {
        // broadcast
        // chatThread는 사용자가 보낸 메시지를 읽어들여서
        // 접속된 모든 클라이언트들에게 메시지를 보낸다

        // 나를 제외한 모든 사용자에게 " 00 "님이 연결되었습니다.
        // guswo ChatThread 를 제외하고 보낸다.
        try{
        broadcast(name + "님이 연결되었습니다", false);
        String line = null;
            while ((line = br.readLine()) != null) {
                if("/quit".equals(line)){
                    break;
                }
                broadcast(name + " : "+ line ,true);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            broadcast(name + "님이 연결이 끊어졌 습니다", false);
            this.list.remove(this);
            try{
                br.close();
            }catch (Exception ex){}
            try{
                pw.close();
            }catch (Exception ex){}
            try{
                socket.close();
            }catch (Exception ex){}
        }

    }

    private  void broadcast(String msg,boolean includeMe){
        List<ChatThread> chatThreads = new ArrayList<>(this.list.size( ));
        for( int i = 0 ; i<this.list.size();i++){
            chatThreads.add(list.get(i));
        }
        try{
            for(int i = 0; i< chatThreads.size(); i ++){
               ChatThread ct = chatThreads.get(i);
               if(includeMe){ //나를 포함하지마라
                   if(ct == this){
                       continue;
                   }
               }
               ct.sendMessage(msg);
            }
        }catch (Exception ex){
            System.out.println("///");
        }
    }

















}
