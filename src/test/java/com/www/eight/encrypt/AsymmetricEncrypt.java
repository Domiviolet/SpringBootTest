package com.www.eight.encrypt;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 非对称加密
 * 常见的非对称加密算法
 * 	1.	RSA
 * 	2.	ECC
 */
public class AsymmetricEncrypt {


    //非对称加密,采用私钥对"硅谷"加密,公钥进行解密的操作
    @Test
    public void test1() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String input = "硅谷";

        //创建密钥对 通过类 KeyPairGenerator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        //1.生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //2.生成公钥和私钥
        PrivateKey privateKey = keyPair.getPrivate(); //生成私钥
        PublicKey publicKey = keyPair.getPublic(); //生成公钥

        //下面两步是为了输出私钥和密钥
        //1. 获取密钥的字节数组
        byte[] privateKeyEncoded= privateKey.getEncoded(); //获取私钥的字节数组
        byte[] publicKeyEncoded = publicKey.getEncoded(); //获取公钥的字节数组

        //2. 使用 Base64进行编码 (Apache 的包)
        String privateKeyEncodeBase64 = Base64.encode(privateKeyEncoded); //通过 Base64 对私钥转码
        String publicKeyEncodeBase64 = Base64.encode(publicKeyEncoded); //通过 Base64 对公钥进行转码

//        System.out.println(privateKeyEncodeBase64);
//        System.out.println("==============");
//        System.out.println(publicKeyEncodeBase64);
        //创建加密对象
        Cipher cipher = Cipher.getInstance("RSA");  //传入参数是: 非对象加密算法(RSA)

        //对加密对象进行初始化
        cipher.init(Cipher.ENCRYPT_MODE, privateKey); //这里我们打算用私钥进行加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        String encodeBase64 = Base64.encode(bytes);  //加密的时候我们需要用到 Base64 便于我们查看加密后的结果.解密不需要用它.
        System.out.println(encodeBase64);

        //下面是解密的操作
        cipher.init(Cipher.DECRYPT_MODE,publicKey);
        byte[] decryptByte = cipher.doFinal(bytes); //注意这里的 doFinal()方法,加密加密解密都是它.但是在加密的时候传的是明文,在解密的时候传的是密文.
        System.out.println(new String(decryptByte)); //注意这里是 传的是 new String()
    }


    //产生公钥和私钥
    @Test
    public void test2() throws IOException, NoSuchAlgorithmException {
        //下面的文件名是我们自己取的,可以任意,反正都在项目这个路径下
        generatorKeyToFile("RSA","a.pri","a.pub");
    }

    /**
     * 把生成的公钥和私钥放在指定的路径下的方法
     * 如果对应路径的参数只写文件名,则默认是在当前项目路径下.
     * @param algorithm
     * @param privateKeyPath
     * @param publicKeyPath
     */

    private static void generatorKeyToFile(String algorithm ,String privateKeyPath ,String publicKeyPath) throws NoSuchAlgorithmException, IOException {

        //创建密钥对 通过类 KeyPairGenerator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        //1.生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        //2.生成公钥和私钥
        PrivateKey privateKey = keyPair.getPrivate(); //生成私钥
        PublicKey publicKey = keyPair.getPublic(); //生成公钥

        //下面两步是为了输出私钥和密钥
        //1. 获取密钥的字节数组
        byte[] privateKeyEncoded= privateKey.getEncoded(); //获取私钥的字节数组
        byte[] publicKeyEncoded = publicKey.getEncoded(); //获取公钥的字节数组

        //2. 使用 Base64进行编码 (Apache 的包)
        String privateKeyEncodeBase64 = Base64.encode(privateKeyEncoded); //通过 Base64 对私钥转码
        String publicKeyEncodeBase64 = Base64.encode(publicKeyEncoded); //通过 Base64 对公钥进行转码

        //把公钥和私钥保存指定的文件下
        //注意啊这里的 FileUtils 是 Apache.commons.io 包下的工具类,是针对文件进行操作的.这里进行了导包.

        //前一个参数是我们方法的公钥路径参数,中间的参数是具体的公钥,最后一个参数是编码格式
       FileUtils.writeStringToFile(new File(privateKeyPath),privateKeyEncodeBase64, Charset.forName("UTF-8"));
        //后一个参数是我们方法的私钥路径参数,中间的参数是具体的私钥,最后一个参数是编码格式
        FileUtils.writeStringToFile(new File(publicKeyPath),publicKeyEncodeBase64,Charset.forName("UTF-8"));
    }

    /**
     * 读取私钥字符串的方法 .(注意是字符串内容,不是对象)
     * 注意用 FilesUtils.FileUtils.readFileToString()方法
     * @param privateKeyPath 私钥的路径
     * @param algorithm  对应的算法那
     * @return 私钥的内容
     * @throws IOException
     */

    private static String readPrivateKeyString(String privateKeyPath ,String algorithm) throws IOException {
        String privateKeyString = FileUtils.readFileToString(new File(privateKeyPath), Charset.defaultCharset());//Charset.defaultCharset()表示怎么存的,怎么取
        return privateKeyString ;
    }
    private static String readePublicKeyString(String publicKeyPath,String algorithm) throws IOException {
        String publicKeyString = FileUtils.readFileToString(new File(publicKeyPath), Charset.defaultCharset());
        return publicKeyString ;
    }

    /**
     * 读取私钥对象的方法 (注意是对象)    (读取/加密一般都要用到 Base64.Base64 进行处理,便于人读取,防止乱码)
     * @param privateKeyPath  私钥路径
     * @param algorithm  对应的算法
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



    /**
     * 读取公钥对象的方法
     * @param publicKeyPath  公钥文件的路径
     * @param algorithm 算法
     * @return  公钥对象
     */
    private static PublicKey getPublicKey(String publicKeyPath , String algorithm) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKeyString = FileUtils.readFileToString(new File(publicKeyPath), Charset.defaultCharset());
        //创建 key 工厂
        KeyFactory publicFactory = KeyFactory.getInstance(algorithm);
        //创建公钥规则
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKeyString));
        return  publicFactory.generatePublic(keySpec);
    }


    @Test
    public void test3() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
//        String readPrivateKey = readPrivateKeyString("a.pri", "RSA");
//        System.out.println(readPrivateKey);
        PrivateKey privateKeyObject = readPrivateKeyObject("a.pri", "RSA");
        System.out.println(privateKeyObject);
    }

    @Test
    public void test4() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        PublicKey rsa = this.getPublicKey("a.pub", "RSA");
        System.out.println(rsa);
    }




}
