
package com.mycompany.encryptioncipher;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class EncryptionCipher {

    private static final int ROTATION_AMOUNT = 3;

 
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString().toLowerCase();
    }

   
    private static byte rotateRight(byte b, int N) {
        int unsignedByte = b & 0xFF;
        N = N % 8;
        int rotated = (unsignedByte >>> N) | (unsignedByte << (8 - N));
        return (byte) rotated;
    }

   
    public static String computeHash(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hash);
    }

  
    public static byte[] encrypt(byte[] plaintext, byte[] key) {
        byte[] cipher = new byte[plaintext.length];

        for (int i = 0; i < plaintext.length; i++) {
            byte p = plaintext[i];
            byte k = key[i % key.length];

            int repeats;
            if (i % 2 == 0) {  
                repeats = (k % 3) + 2;  
            } else {
                repeats = 1; 
            }

            byte temp = p;

            for (int r = 0; r < repeats; r++) {
                byte rotated = rotateRight(temp, ROTATION_AMOUNT);
                temp = (byte) (rotated ^ k);
            }

            cipher[i] = temp;
        }

        return cipher;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=".repeat(80));
        System.out.println("         Executing Encryption + Hashing System (Custom Hybrid)");
        System.out.println("=".repeat(80));

        System.out.println("\n1. Enter Plaintext (Your Name):");
        String plaintextStr = input.nextLine();

        System.out.println("2. Enter Key:");
        String keyStr = input.nextLine();

        byte[] plaintext = plaintextStr.getBytes(StandardCharsets.UTF_8);
        byte[] key = keyStr.getBytes(StandardCharsets.UTF_8);

        try {
          
            String originalHash = computeHash(plaintextStr);

            byte[] cipherBytes = encrypt(plaintext, key);
            String cipherHex = bytesToHex(cipherBytes);

            System.out.println("\n" + "-".repeat(80));
            System.out.println("[RESULTS]");
            System.out.println("Plaintext       : " + plaintextStr);
            System.out.println("Key             : " + keyStr);
            System.out.println("SHA-256 (Plain) : \n" + originalHash);
            System.out.println("Ciphertext Hex  : \n" + cipherHex);

            System.out.println("\n" + "=".repeat(80));
            System.out.println("✅ Encryption and Hashing Completed Successfully");
            System.out.println("=".repeat(80));

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            input.close();
        }
    }
}
