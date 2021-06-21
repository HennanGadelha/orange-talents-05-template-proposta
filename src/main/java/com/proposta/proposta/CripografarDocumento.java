package com.proposta.proposta;


import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class CripografarDocumento {


    private  static String password = "hfdhdffdhddhf";
    private  static String salt = KeyGenerators.string().generateKey();

    @SuppressWarnings("deprecation")
    private static TextEncryptor encryptor =  Encryptors.queryableText(password, salt);

    public static String criptografar(String documento) {
        System.out.println(encryptor.encrypt(documento));
        return encryptor.encrypt(documento);

    }

    public static String descriptografar(String documento) {
        return encryptor.decrypt(documento);
    }

}
