package com.www.security.config;

/**
 * @Author: WangHongYan
 * @Since: 2021/01/23 21:44:34
 */

public class HsmSigner {

    private HsmClient hsmClient;

    HsmSigner(String key, String value) throws Exception {

        try {
            hsmClient = this.getHsmClient(key, value);
        } catch (Exception e) {
            throw new Exception("dd");
        }

    }

    public HsmClient getHsmClient(String key, String value){
        return new HsmClient(key, value);
    }

}
