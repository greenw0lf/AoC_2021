package Day16;

import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Str {
    String str;
    public Str(String str) {
        this.str = str;
    }
}

public class Day16Part1 {
    public static void literal(Str binary) {
        String temp = binary.str.substring(0, 5);
        binary.str = binary.str.substring(5);
        while (temp.charAt(0) == '1') {
            temp = binary.str.substring(0, 5);
            binary.str = binary.str.substring(5);
        }
    }

    public static int lengthOfBits(Str binary) {
        int res = 0;

        int length = Integer.parseInt(binary.str.substring(0, 15), 2);
        binary.str = binary.str.substring(15);

        int initLen = binary.str.length();

        while (initLen - binary.str.length() < length) {
            res += packet(binary);
        }

        return res;
    }

    public static int numberOfPackets(Str binary) {
        int res = 0;

        int noOfPackets = Integer.parseInt(binary.str.substring(0, 11), 2);
        binary.str = binary.str.substring(11);

        for (int i = 0; i < noOfPackets; i++)
            res += packet(binary);

        return res;
    }

    public static int packet(Str binary) {
        int res = 0;

        String version = binary.str.substring(0, 3);
        res += Integer.parseInt(version, 2);
        binary.str = binary.str.substring(3);
        String id = binary.str.substring(0, 3);
        binary.str = binary.str.substring(3);

        if (Integer.parseInt(id, 2) == 4)
            literal(binary);
        else if (binary.str.charAt(0) == '0') {
            binary.str = binary.str.substring(1);
            res += lengthOfBits(binary);
        }
        else {
            binary.str = binary.str.substring(1);
            res += numberOfPackets(binary);
        }

        return res;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(16);

        String hexa = sc.next();
        String binary = "";

        for (int i = 0; i < hexa.length(); i++) {
            StringBuilder digit = new StringBuilder(Integer.toBinaryString(
                    Integer.parseInt("" + hexa.charAt(i), 16)));
            int pain = digit.length();
            for (int j = 0; j < 4 - pain; j++)
                digit.insert(0, "0");
            binary += digit;
        }

        System.out.println(packet(new Str(binary)));
    }
}
