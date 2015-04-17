package com.gms.socket;

/**
 * Created by Kevin on 2015/4/17.
 */
public class DealClientRequestFactory {
    private static DealClientRequest dealClientRequest = null;

    public static DealClientRequest fetchDealClientRequest() {
        if (dealClientRequest == null) {
            dealClientRequest = new DealClientRequest();
            return dealClientRequest;
        }
        return dealClientRequest;
    }

}
