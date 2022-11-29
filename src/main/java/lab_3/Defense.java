package lab_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Defense {
    public static void main(String[] args) {
        BinaryTree.Node root = parseFile("input.txt");
        BinaryTree.depthFirstSearch(root, System.out::println);
    }

    private static BinaryTree.Node parseFile(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return Parser.parse(reader);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
