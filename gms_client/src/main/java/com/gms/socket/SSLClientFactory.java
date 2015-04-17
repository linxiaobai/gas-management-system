package com.gms.socket;

/**
 * Created by Kevin on 2015/4/17.
 */
public class SSLClientFactory {

    private static SSLClient sslClient = null;
    public static SSLClient fetchSSLClient() {
        if (sslClient == null) {
            sslClient = new SSLClient();
            return sslClient;
        }
        return sslClient;
    }
}
