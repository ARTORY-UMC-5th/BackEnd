//package com.example.demo.global.config.web;
//
//import org.jasypt.encryption.StringEncryptor;
//import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
//import org.jasypt.salt.RandomSaltGenerator;
//
//
//public class JasyptTest {
//
//    public static void main(String[] args) {
//        String encryptedPassword = "QnyePTdjpwsx3j9eyNZbOm7EJnhEvLuw";
//        // Jasypt를 사용하여 해독
//        String decryptedPassword = decryptPassword(encryptedPassword);
//        // 해독된 비밀번호 출력
//        System.out.println("Decrypted Password: " + decryptedPassword);
//    }
//    private static String decryptPassword(String encryptedPassword) {
//        // Jasypt StringEncryptor 생성
//        StringEncryptor encryptor = getStringEncryptor();
//        // 암호화된 비밀번호를 해독하여 반환
//        return encryptor.decrypt(encryptedPassword);
//    }
//
//    private static StringEncryptor getStringEncryptor() {
//        // Jasypt PBE StringEncryptor 생성
//        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
//        // Jasypt SimpleStringPBEConfig 설정
//        encryptor.setAlgorithm("PBEWithMD5AndDES");
//        encryptor.setKeyObtentionIterations(1000);
//        encryptor.setProviderName("SunJCE");
//        encryptor.setSaltGenerator(new RandomSaltGenerator());
//        encryptor.setStringOutputType("base64");
//        encryptor.setPoolSize(1);
//        // Jasypt 암호화 키 설정
//        encryptor.setPassword("keytouseforencryption");
//        // 생성된 encryptor 반환
//        return encryptor;
//    }
//}