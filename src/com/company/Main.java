package com.company;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class Main {

    public static void main(String[] args) {
        String token = "jwtToken";
        String[] parts = token.split("\\.");
        //46 is . 35 is #

        byte[] part1 = parts[0].getBytes(StandardCharsets.US_ASCII);
        byte[] part2 = parts[1].getBytes(StandardCharsets.US_ASCII);
        byte[] part3 = parts[2].getBytes(StandardCharsets.US_ASCII);

        byte[] outputData = new byte[part1.length + part2.length + 2];

        System.arraycopy(part1, 0, outputData, 0, part1.length);

        outputData[part1.length] = 46;

        int j = 0;
        for (int i = part1.length + 1; i < outputData.length - 1; i++) {
            outputData[i] = part2[j];
            j++;
        }

        outputData[outputData.length - 1] = 35;


        String signature = HexBin.encode(Base64.getUrlDecoder().decode(part3));



        String output = new String(outputData, StandardCharsets.US_ASCII);
        System.out.println(output + signature);

    }
}
