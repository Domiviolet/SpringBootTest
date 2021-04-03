package com.www.http.kits.cryptography.encrypt;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 数字签名
 *
 * 生成签名使用私钥对象
 *
 * 校验签名使用公钥对象       SHA256withRSA
 */
public class DigitalSignature {

    String aPrivatePath = "src/test/java/com/www/http/kits/cryptography/keypaths/aPrivate.txt";
    String aPublicPath = "src/test/java/com/www/http/kits/cryptography/keypaths/aPublic.txt";




    @Test
    public  void testDigitalSignature() throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        String content = "Content to be signed";
        byte[] bytes = Base64.decode(content);

        KeyPairGenerator rsaKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
        rsaKeyPairGenerator.initialize(1024);
        KeyPair keyPair = rsaKeyPairGenerator.generateKeyPair();
        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();

        System.out.println(Base64.encode(aPrivate.getEncoded()).length());
        System.out.println(Base64.encode(aPublic.getEncoded()).length());

        //签名
//        Signature signature = Signature.getInstance("SHA256withRSA");
//        signature.initSign(aPrivate);
////        signature.update(bytes);
//        byte[] sign = signature.sign();
//        System.out.println(Base64.encode(sign));

    }








    @Test
    public void test1() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        //调用获取私钥对象的方法,获取私钥对象
        PrivateKey privateKey = readPrivateKeyObject(aPrivatePath, "RSA");
        //调用获取公钥对象的方法,获取公钥对象
        PublicKey publicKey = readPublicKeyObject(aPublicPath, "RSA");
        //调用获取数字签名的方法,获取数字签名(密文)  //注意这里的算法SHA256,必须跟 with+上面密钥加密的算法
        String signatureData = getSignature("123", "SHA256withRSA", privateKey);
        System.out.println(signatureData);

        //进行校验
        boolean b = verifySignature("123", "SHA256withRSA",publicKey, signatureData);
        System.out.println(b);
    }

    /**
     * 读取私钥对象的方法 (注意是对象)    (读取/加密一般都要用到 Base64.Base64 进行处理,便于人读取,防止乱码)
     * @param privateKeyPath  私钥路径
     * @param algorithm 算法(注意这里的算法是由 加密的算法+with+私钥的算法组成的)
     * @return  返回 PrivateKey 对象
     */

    private static PrivateKey readPrivateKeyObject(String privateKeyPath,String algorithm) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKeyString = FileUtils.readFileToString(new File(privateKeyPath), Charset.defaultCharset());//Charset.defaultCharset()表示怎么存的,怎么取
        //创建 key 的工厂
        KeyFactory factory = KeyFactory.getInstance(algorithm);

        //创建私钥 key 的规则
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyString));

        return factory.generatePrivate(privateKeySpec);
    }

    /** 这个方法不对
     * 读取公钥的对象的方法
     * @param publicKeyPath
     * @param algorithm
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */

    /**
     * 读取公钥对象的方法
     * @param publicKeyPath  公钥文件的路径
     * @param algorithm 算法
     * @return  公钥对象
     */
    private static PublicKey readPublicKeyObject(String publicKeyPath ,String algorithm) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        //对文件
        String publicKeyString = FileUtils.readFileToString(new File(publicKeyPath), Charset.defaultCharset());//Charset.defaultCharset()表示怎么存的,怎么取
        //创建key 的工厂
        KeyFactory factory = KeyFactory.getInstance(algorithm);

        //创建公钥规则
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKeyString));
        return factory.generatePublic(keySpec);
    }


    /**
     * 生成数字签名的方法    用到的类 Signature 类
     * @param input 明文(要加密的东西)
     * @param algorithm 加密算法 (注意这里的加密算要加上 with+签名算法,如SHA256withRSA.其中SHA256是一个算法,它的签名是用 RSA 来做的,所以这样写
     * @param privateKey  私钥
     * @return 生成签名
     */
    private static String getSignature(String input, String algorithm , PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        //获取签名对象
        Signature signature = Signature.getInstance(algorithm);

        //初始化签名
        signature.initSign(privateKey);

        //传入原文
        signature.update(input.getBytes());

        //开始签名
        byte[] sign = signature.sign();

        //使用 Base64 进行编码
        String encode = Base64.encode(sign);
        return encode ;
    }

    /**
     * 校验签名
     * @param input 原文
     * @param algorithm 算法  注意这里的加密算要加上 with+签名算法,如SHA256withRSA
     * @param publicKey 公钥 key
     * @param signatureData 签名的密文
     * @return  是否被修改
     */
    private static boolean verifySignature(String input , String algorithm ,PublicKey publicKey ,String signatureData) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        //获取签名对象
        Signature signature = Signature.getInstance(algorithm);

        //初始化校验
        signature.initVerify(publicKey);

        //传入原文
        signature.update(input.getBytes());

        //进行校验
        return signature.verify(Base64.decode(signatureData)); //传入的密文
    }

}
