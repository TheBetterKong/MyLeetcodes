package leetcode.editor.cn;

import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] info1 = in.nextLine().split(" ");
        int[] num1 = new int[info1.length];
        for (int i = 0; i < info1.length; i++) {
            num1[i] = Integer.parseInt(info1[i]);
        }

        String[] info2 = in.nextLine().split(" ");
        int[] num2 = new int[info1.length];
        for (int i = 0; i < info1.length; i++) {
            num2[i] = Integer.parseInt(info1[i]);
        }

        ArrayList list = findanswer(num1, num2);
        int s1 = 3;
        String s = "1 3 5";
        String s3
        System.out.println();
    }

    public static ArrayList findanswer(int[] nums1, int[] nums2) {

    }
}