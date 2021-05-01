package com.www.http.kits.exercise.security.colleage.cryptography.encrypt;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SymmetricEncrypt {

    /**
     * kaiser 凯撒加密
     * 移位法
     * <p>
     * 把hello world采用凯撒加密的方式加密
     */
    @Test
    public void test1() {
        // 1. 定义原文
        String input = "hello world";
        // 2. 把原文右移3 位
        int key = 3;
        // 3.把字符串变成字节数组
        char[] chars = input.toCharArray();

        StringBuffer stringBuffer = new StringBuffer(); //这一步是在 27 行需要时,才写的
        // 4.对字节数组进行遍历
        for (char ch : chars) {
//            int a = ch ;
            //往右移动 3 位
            ch = (char) (ch + key);
            //把机密后的数据添加到 StringBuffer 对象中
            stringBuffer.append(ch); //这个是加密之后的,密文
        }
        System.out.println(stringBuffer);
    }

    /**
     * 对以上进行解密
     *
     * @return
     */
    @Test
    public void test2() {

        String input = "hello world";
        String encrypt = encrypt(input, 3);
        System.out.println(encrypt); //加密后的结果

        //解密,这里解密我们需要密文和密钥
        StringBuffer decipher = decipher(encrypt, 3);
        System.out.println(decipher);


    }

    //定义解密的方法
    private StringBuffer decipher(String encrypt, int key) {
        char[] chars = encrypt.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (char ch : chars) {
            ch = (char) (ch - key);
            stringBuffer.append(ch);
        }
        return stringBuffer;
    }


    //1.先定义加密方法
    public String encrypt(String input, int key) {
        char[] chars = input.toCharArray();

        StringBuffer stringBuffer = new StringBuffer();
        for (char ch : chars) {
            ch = (char) (ch + key);
            stringBuffer.append(ch);
        }
        return stringBuffer.toString(); //把 StringBuffer 类型的转换为 String 类型的
    }



  /*   对称加密(加密和解密用同一把密钥
     对称加密分为: 流加密(一个一个的加密) 和 块加密(分成块再加密)
     例如: 12345 如果采用流加密,则是 1 ,2 ,3..一个一个加密
                 块加密则是: 12 加密, 34 加密 等
      常见的对称加密:
        AES
        DES(密钥必须是 8 个字节,否则不对)

      特点:
      1.加密速度快,可以加密大文件
      2.密文可逆,一旦密钥文件泄漏,会导致暴露
      3.加密后编码表找不到对应字符,出现乱码
      4.一般结合 Base64 一起使用
    */


    /**
     * 用 DES 算法对"硅谷"进行加密的操作
     */

    @Test
    public void test3() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 原文(明文)
        String input = "硅谷";
        //定义 key
        //如果使用 DES 进行加密,密钥必须是 8 个字节
        String key = "12345678";
        //定义加密使用的算法,如 DES ,AES
        String algorithm = "DES";
        //定义加密类型
        String transformation = "DES";
        //创建加密对象. (需要用到 Java 提供的 Cipher 类中的 getInstance()方法,传入的参数是加密类型
        //Cipher 是用来做加密或解密的.也就是加密或解密都用它
        Cipher cipher = Cipher.getInstance(transformation);

        // 从给定的字节数组构造一个密钥SecretKey
        //new SecretKeySpec()需要两个参数,一个是 key 的字节,一个是加密的类型,类似加密算法
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);


        //进行加密初始化 int()两个参数,一个是加密模式或解密模式 .第二个参数是加密的规则
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);  //因为这个方法第二个参数需要加密规则,所以我们要在上面去创建对应的规则对象

        //调用加密方法. doFinal()方法 .注意 doFinal()方法即是加密方法,也是解密方法.
        //传入的参数是原文,也就是对什么进行加密/解密 ,就传入什么.注意这里传入的如果是 String 类型,需要 .getBytes()转换为字节型
        byte[] bytes = cipher.doFinal(input.getBytes()); //这里获得是密文

        //下面输出结果会乱码.
        //编码表中找不到对应的值会出现乱码,比如-188.编码表中没有负数
        System.out.println(new String(bytes)); //不能直接打印System.out.println(bytes).因为 bytes 是数组.
//        for (byte aByte : bytes) {  //使用快捷键 bytes.for tab 生成 .注意 bytes 是要遍历的数组名. 集合也同样适用
//            System.out.println(aByte);
//        }
        //如果我们希望没有乱码 Base64 进行转码,注意这个 Base64 是 apache 的包,不要导错了
        String encode = Base64.encode(bytes);
        System.out.println(encode);

    }

    /**
     * 对 DES 加密后的文件("硅谷")进行解密
     */

    @Test
    public void test4() throws IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        //调用解密方法,完成解密
        String decrypt = this.decryptDES("qANksk5lvqM=", "12345678", "DES", "DES");
        System.out.println(decrypt);

        //调用加密方法,完成加密 (方法中调方法,this 可以省略,直接写方法名)
        String encrypt = this.encryptDES("硅谷", "12345678", "DES", "DES");
        System.out.println(encrypt);

    }

    /**
     * 对 DES 加密后的文件的机密方法
     *
     * @param decryptContents     密文
     * @param key            密钥
     * @param transformation 加密类型
     * @param algorithm      加密算法
     * @return
     */
    //注意方法里面的形参,实际上就相当于我们在方法里声明了传参对象
    private static String decryptDES(String decryptContents, String key, String transformation, String algorithm) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //创建解密对象
        Cipher cipher = Cipher.getInstance(transformation);

        //从给定的字节数组构造一个密钥SecretKey
        //SecretKeySpec(byte[] key, String algorithm)
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        //初始化解密对象
        //Cipher.DECRYPT_MODE 表示解密的模式,这里代码会有提示,不需要记住
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        //调用解密的方法 doFinal()进行解密
        byte[] bytes = cipher.doFinal(Base64.decode(decryptContents));

//        //为防止乱码,用 Base64 进行解码
//        byte[] decode = Base64.decode(new String(bytes));

        return new String(bytes); //注意这里要把 bytes 数组转换为 String 对象,这里的 return,可以理解我们的 System.out.println()方法
    }

    /**
     * 加密操作
     *
     * @param encryptContents 加密内容
     * @param key             密钥
     * @param transformation  加密类型
     * @param algorithm       加密算法 (有 DES,AES)
     * @return 字母串型的加密密文
     */
    private static String encryptDES(String encryptContents, String key, String transformation, String algorithm) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //创建加密算法的加密对象
        Cipher cipher = Cipher.getInstance(transformation);

        //从给定的字节数组构造一个密钥SecretKey
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);

        //初始化加密对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        //调用加密方法 doFinal() 传入的参数表示对什么进行操作
        byte[] bytes = cipher.doFinal(encryptContents.getBytes());
        String encode = Base64.encode(bytes);
        return encode;
    }

    /*
     * Base64 (apache的包) 是算法
     *   它不是加密算法,是可读性算法,它不是用来保护数据,是为了可读性.
     *   命名由来: 它是由 64 个字符组成,大写,小写的字母总共 26+26=52 ,和数字 0-9 ,+ 和 /
     *   字节分组中如果不够的话,就用"="补齐
     *
     * Base58 一般用在比特币里面的一种编码方式
     *   base58 里面没有数字 0 ,和字母 o 没有大写字母 I 和小写字母 i ,没有+ /
     *
     * */


    // AES 加密, AES 是在 DES 的基础上优化后的加密
    // AES和 DES 的代码都是一样的,只要注意 key 的字节数就可以了
    // AES 和 DES 的算法都是一样的,只是 AES 的密钥 key 是 16 个字节.而 DES 的密钥是 8 个字节
    // 常见的 transformation 的类型有:
//              AES/CBC/NoPadding
//               AES/CBC/PKCS5Padding
//               AES/ECB/NoPadding
//               AES/ECB/PKCS5Padding
//               DES/CBC/NoPadding
//               DES/CBC/PKCS5Padding
//               DES/ECB/NoPadding
//               DES/ECB/PKCS5Padding
//               DESede/CBC/NoPadding
//               DESede/CBC/PKCS5Padding
//               DESede/ECB/NoPadding
//               DESede/ECB/PKCS5Padding
//               RSA/ECB/PKCS1Padding
//               RSA/ECB/OAEPWithSHA-1AndMGF1Padding
//               RSA/ECB/OAEPWithSHA-256AndMGF1Padding
    @Test
    public void tes5() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        //下面参数transformation = "AES" ,其实是 "AES/ECB/PKCS5Padding"的缩写
        String encryptAES = this.encryptAES("王红岩", "1234567891111111", "AES/ECB/PKCS5Padding", "AES");
        System.out.println(encryptAES); //B0256Ja8bNiZeXaba5NuSg==
    }

    private static String encryptAES(String encryptContents, String key, String transformation, String algorithm) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        //Cipher类提供加密和解密的加密密码的功能
        //创建加密算法的加密对象
        Cipher cipher = Cipher.getInstance(transformation);

        //从给定的字节数组构造一个密钥SecretKey
        //SecretKeySpec(byte[] key, String algorithm)
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);

        //初始化加密对象
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
//        cipher.init(Cipher.ENCRYPT_MODE,);
        //调用加密方法 doFinal() 传入的参数表示对什么进行操作
        byte[] bytes = cipher.doFinal(encryptContents.getBytes());
        String encode = Base64.encode(bytes);
        return encode;

    }
    /**
     *  加密模式(
     *
     *  1.ECB: (Electronic codeBook) 电子密码本 (把一段文本进行分拆加密,使用同一个 key,分别进行加密,然后组合)
     *      需要加密的消息按照块密码的块大小被分为多个块,并对每个块进行单独加密
     *      优点: 可以并行处理数据
     *      缺点: 同样的原文生成同样的密文,不能很好的保护数据
     *      同时加密,原文是一样的,加密出来的密文也是一样的
     *  2.CBC: (Cipher-block chaining) 密码块链接
     *      每个明文块先与前一个密文块进行异或处理,再进行加密.在这种情形下,每个密文块都依赖于它前面的所有明文块
     *      优点: 同样的原文生成的密文不一样
     *      缺点: 串行处理数据,速度快
     *
     *  填充模式
     *      当需要按块处理的数据长度不符合块处理需要时,会按照填充模式进行处理
     *   1.NoPadding(不填充)
     *       在 DES 算法下,要求 key是 8 个字节,AES 是 16 个
     *   2.PKCS5Padding
     *      数据块的大小为 8 位,不够就补位
     *
     *  注意:
     *      1.默认情况下,加密模式和填充模式(transformation)为: ECB/PKCS5Padding
     *      2.如果使用 CBC 模式,在初始化 Cipher 对象的时候,还需要增加参数.
     *          初始化向量 IV : IvParameterSpec iv = new IvParameterSpec(key.getBytes())
     *
     */


    /**
     *  消息摘要(消息加密) (信息加密)
     *  防止被篡改
     *      常用的算法:
     *          1. MD5
     *          2. SHA-1
     *          3. SHA-256
     *          4. SHA-512
     *         注意以上的算法,在传入到相应的参数位置时,一般用""包裹
     *
     *  对应的 api 是 MessageDigest类
     */
    //通过 md5 对 aa 进行数字摘要(加密)
    @Test
    public void test6() throws NoSuchAlgorithmException {

        //要进行消息摘要的东西
        String input = "aa";

        //创建MD5算法消息摘要对象
        //参数中要传入算法,常见的有 MD5,SHA-1,SHA256,SHA512
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");


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
     *  这样我们只要传入不同的算法,则相应的加密就不同了
     * @throws NoSuchAlgorithmException
     */

    @Test
    public void test7() throws NoSuchAlgorithmException {
        byte[] bytes = this.differentAlgorithm("aa", "SHA-512");
        String s = this.toHex(bytes);
        System.out.println(s);
    }
    //不同算法进行数字摘要的方法
    private static byte[] differentAlgorithm(String input ,String algorithm) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        byte[] digest = messageDigest.digest(input.getBytes());
        return digest ;
    }

    // 把加密后的文件转换为 16 进制的密文
    private static String toHex(byte[] digest){

        StringBuffer sb = new StringBuffer();
        //对密文进行迭代
        for (byte b : digest) {
            String string = Integer.toHexString(b & 0xff);
            //我们习惯上要求每次迭代的结果都是 2 个 bit 位,所以为了避免可能只有1个比特位的情形,我们这里需要判读一下
            if (string.length()==1){
                string = "0" +string ;
            }
            sb.append(string);
        }
        return sb.toString() ;
    }
}
