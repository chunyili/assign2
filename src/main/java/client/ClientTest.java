package client;

import lwm2m.server.BootstrapServer.BootstrapConfig;

/**
 * Created by jilongsun on 6/26/15.
 */
public class ClientTest {
    public static void main(String args[]){

        BootstrapConfig config3 = BootstrapRequest.getBootstrapInfo();

        if(config3 == null){
            System.out.println("Bootstrap has been denied, cannot register");
        }else {
            String returnString = RegisterRequest.registerInfo(config3);
            System.out.print(returnString);
        }




    }

}
