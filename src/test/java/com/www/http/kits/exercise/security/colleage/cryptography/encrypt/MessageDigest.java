package com.www.http.kits.exercise.security.colleage.cryptography.encrypt;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;


/**
 * 消息摘要(消息加密) (信息加密)
 * 防止被篡改
 * 常用的算法:
 * 1. MD5
 * 2. SHA-1
 * 3. SHA-256
 * 4. SHA-512
 * 注意以上的算法,在传入到相应的参数位置时,一般用""包裹
 * <p>
 * 对应的 api 是 MessageDigest类
 */
public class MessageDigest {

    //通过 md5 对 aa 进行数字摘要(加密)
    @Test
    public void test6() throws NoSuchAlgorithmException {

        //要进行消息摘要的东西
        String input = "aa";

        //创建MD5算法消息摘要对象
        //参数中要传入算法,常见的有 MD5,SHA-1,SHA256,SHA512
        java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance("MD5");


        //该摘要对象,对摘要内容进行操作
        //	digest(byte[] input)
        //input.getBytes()把String 型的内容转换为byte[]
        byte[] digest = messageDigest.digest(input.getBytes());
//        System.out.println(new String(digest)); //不转换回乱码
//        //把密文转换为 16 进制的操作
//        for (byte b : digest) {
//            String string = Integer.toHexString(b & 0xff);
//
//            //进行下面的操作的原因是,我们每次遍历的输出结果应该是 2 个字节的,而有些数比较小,只有一个字节,所以习惯上我们在高位补 0
//            //如果密文的长度是 1 个字节,需要在高位进行补0
//            if(string.length()==1){
//                string = "0" + string ;
//            }
////            System.out.println(string); //注意这里的遍历是把数字一个一个拿出来,并且换行了
//            System.out.print(string); //这里是不换行的操作.4124bca9335c27f86f24ba207a4912QSS8CpM1wn8IbyS6IHpJEg==
//        }
        //使用 Base64 进行转码 (防止乱码)
        String encode = Base64.encode(digest);
        System.out.println(encode);

    }

    /**
     * 对上面的做法进行封装,封装成更通用的方法
     * 这样我们只要传入不同的算法,则相应的加密就不同了
     *
     * @throws NoSuchAlgorithmException
     */

    @Test
    public void test7() throws NoSuchAlgorithmException {
        byte[] bytes = this.differentAlgorithm("aa", "SHA-512");
        String s = this.toHex(bytes);
        System.out.println(s);
    }

    //不同算法进行数字摘要的方法
    private static byte[] differentAlgorithm(String input, String algorithm) throws NoSuchAlgorithmException {
        java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance(algorithm);
        byte[] digest = messageDigest.digest(input.getBytes());
        return digest;
    }

    // 把加密后的文件转换为 16 进制的密文
    private static String toHex(byte[] digest) {

        StringBuffer sb = new StringBuffer();
        //对密文进行迭代
        for (byte b : digest) {
            String string = Integer.toHexString(b & 0xff);
            //我们习惯上要求每次迭代的结果都是 2 个 bit 位,所以为了避免可能只有1个比特位的情形,我们这里需要判读一下
            if (string.length() == 1) {
                string = "0" + string;
            }
            sb.append(string);
        }
        return sb.toString();
    }

}