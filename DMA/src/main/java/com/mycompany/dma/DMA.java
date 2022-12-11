package com.mycompany.dma;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.AbstractDocument.LeafElement;

public class DMA {

    private static final int strike = 2;
    private static File file = new File("C:/Users/phamh/Documents/NetBeansProjects/DMA/src/main/java/com/image/image.txt");

    public static void main(String[] args) throws FileNotFoundException {
        int[][] numArray = readFile();
        System.out.println("\n_________________________________________\n");
        System.out.println("Image input:");
        print2D(numArray);
        int[] matrix = convert1dArray(numArray);
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i]);
        }
         System.out.println("\n_________________________________________\n");
        System.out.println("MaxPooling use 1d:");
        int maxpool[]=new int[matrix.length/4];
        maxpool=maxpooling(matrix,2);
        int[][] maxPooling = maxPooling(numArray, strike);
        System.out.println("\n_________________________________________\n");
        System.out.println("MaxPooling:");
        print2D(maxPooling);
    }

    private static int[][] readFile() throws FileNotFoundException {
        int height = 0;
        int width = 0;
        List<Integer> list = new ArrayList<Integer>() {
        };
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            height = height + 1;
            String[] line = sc.nextLine().trim().split(" ");
            width = line.length;
            for (int j = 0; j < line.length; j++) {
                list.add(Integer.parseInt(line[j]));
            }
        }
        height = list.size() / width;
        System.out.println("height:" + height);
        System.out.println("width:" + width);
        int array2d[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array2d[i][j] = list.get((i * width) + j);
            }
        }
        return array2d;
    }

    public static int[] convert1dArray(int[][] arr) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                list.add(arr[i][j]);
            }
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }
    public static int[] maxpooling(int arr[],int strike){
        int height = (int)Math.sqrt(arr.length);
//        System.out.println("height"+height);
        int width = height;
        int maxpool1d[]=new int[height*width/4];
//        int maxPooling[][] = new int[height / 2][width / 2];
        for(int i=0;i<maxpool1d.length;i++){
            maxpool1d[i]=0;
        }
        int index=0;
        for (int m=0;m<=width-strike;m=m+2){
            for(int n=0;n<=height-strike;n=n+2){
    //            int t1=(int)(m*(width-strike)/4+n/2);
    //            index=index+1;
    //            System.out.println("index: "+index);
                for(int i=0;i<strike;i++){
                    for(int j=0;j<strike;j++){
    //                    int t1=(int)(m*(width-strike)/4+n/2);
                        int t2=(i+m)*width+(j+n);
    //                    System.out.println("t1: "+t1);
    //                    System.out.println("t2: "+t2);
                        if(maxpool1d[index]<arr[t2]){
                            maxpool1d[index]=arr[t2];
                        }
                    }
                }
                index=index+1;
            }
    }
    for(int i=0;i<maxpool1d.length;i++){
        System.out.println(maxpool1d[i]);
    }
    return maxpool1d;
    }

    public static int[][] maxPooling(int[][] arr, int strike) {
        List<Integer> list = new ArrayList<Integer>();
        int height = arr.length;
        int width = arr[0].length;
        int maxPooling[][] = new int[height / 2][width / 2];
        for (int m = 0; m < height; m = m + 2) {
            for (int n = 0; n < width; n = n + 2) {
                int max = 0;
                // System.out.println("\n-----------------------------\n");
                for (int i = 0; i < strike; i++) {
                    for (int j = 0; j < strike; j++) {
                        // System.out.print("arr[" + (i + m) + "][" + (j + n) + "]:" + arr[i + m][i + n] + "\n");
                        if (max < arr[i + m][j + n]) {
                            max = arr[i + m][j + n];
                        }
                    }
                }
                // System.out.println("\nMax:" + max);
                list.add(max);
            }
        }
        // for (int i = 0; i < list.size(); i++) {
        // System.out.print(list.get(i)+" ");
        // }
        for (int i = 0; i < height / 2; i++) {
            // System.out.println("\n-----------------------------\n");
            for (int j = 0; j < width / 2; j++) {
                // System.out.println(list.get(i * width / 2 + j)+" ");
                maxPooling[i][j] = list.get(i * width / 2 + j);
            }

        }
        return maxPooling;
    }

    public static void print2D(int mat[][]) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println("\n");

        }
    }
}
