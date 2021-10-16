import java.io.*;

import java.nio.file.*;
import java.nio.charset.StandardCharsets;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        int[] cells = new int[999];
        int pc = 0;
        int read = 0;
        
        for (int i = 0; i < 999; i++)
            cells[i] = 0;
        
        File f = new File("src.bf");
        
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(f.getName()));
            
            
            String s = new String(encoded, StandardCharsets.UTF_8);
            
            while (read < s.length()) {
                char c = s.charAt(read);
                
                if (c == '+') {
                    cells[pc]++;
                    
                    if (cells[pc] >= 256)
                        cells[pc] = 0;
                }
                else if (c == '-') {
                    cells[pc]--;
                    
                    if (cells[pc] < 0)
                        cells[pc] = 255;
                }
                else if (c == '>') {
                    pc++;
                    
                    if (pc >= 999)
                        pc = 0;
                }
                else if (c == '<') {
                    pc--;
                    
                    if (pc < 0)
                        pc = 999;
                }
                else if (c == ',') {
                    System.out.print("Requested Input: ");
                    
                    Scanner sc = new Scanner(System.in);
                    int input = sc.nextInt();
                    
                    cells[pc] = input;
                }
                else if (c == '.') {
                    System.out.print((char) cells[pc]);
                }
                else if (c == ']') {
                    if (cells[pc] != 0) {
                        int count = 0;
                        
                        for (int j = read - 1; j > 0; j--) {
                            char ch = s.charAt(j);
                            
                            if (ch == ']')
                                count++;
                            
                            else if (ch == '[') {
                                if (count != 0) {
                                    count--;
                                    
                                    continue;
                                }
                                
                                read = j;
                                
                                break;
                            }
                        }
                    }
                }
                
                read++;
            }
        } catch (Exception e) {
            System.err.println("An exception occurred and the program has been terminated.");
        }
    }
}
