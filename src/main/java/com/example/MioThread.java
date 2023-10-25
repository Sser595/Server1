package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MioThread extends Thread{
    Socket s;
    public MioThread(Socket s){
        this.s= s;
    }
    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            int numeroDaIndovinare = (int) (Math.random() * 100);
            System.out.println(numeroDaIndovinare);

            int numeroRicevuto;

            do {
                String temp = in.readLine();
                System.out.println(temp);
                numeroRicevuto = Integer.parseInt(temp);
                if (numeroRicevuto < numeroDaIndovinare) {
                    out.writeBytes("1\n");
                } else if (numeroRicevuto > numeroDaIndovinare) {
                    out.writeBytes("2\n");
                }
            } while (numeroRicevuto != numeroDaIndovinare);

            out.writeBytes("3\n");

            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("errore durante l'istanza del server");
            System.exit(1);
        }
    }
}
