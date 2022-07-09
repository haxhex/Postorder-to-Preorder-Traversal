/*
 Helia Ghorbani
 9824353
 Post-Order -> Pre-Order
 */
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;
public class PostorderToPreorder {
    static int postToPre(int f, int minValue, int maxValue, int preIndex, int[] postorder, int[] preorder) {
        if (f < 0 || postorder[f] < minValue || postorder[f] > maxValue) {
            return 0;
        }
        int right = postToPre(f - 1, postorder[f], maxValue, preIndex, postorder, preorder); // Right Subtree
        int left = postToPre(f - 1 - right, minValue, postorder[f], preIndex - right, postorder, preorder); // Left Subtree
        preorder[preIndex - left - right] =  postorder[f];
        return left + right + 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n; // Size Of BST
        ArrayList<String> tempNodes = new ArrayList<>();
        System.out.println("Enter Post-Order Traversal elements or (end) after entering last node : ");
        while (true){
            String temp = scanner.next();
            if (temp.equalsIgnoreCase("end")){
                break;
            }
            tempNodes.add(temp);
        }
        n = tempNodes.size();
        int[] postOrder = new int[n];
        for (int i = 0; i < n; i++) {
            postOrder[i] = Integer.parseInt(tempNodes.get(i));
        }

        int min = postOrder[0]; // Min value in  BST
        int max = postOrder[0]; // Max value in BST
        for (int i = 0; i < n; i++) {
            min = min(min, postOrder[i]);
            max = max(max, postOrder[i]);
        }

        int[] preOrder = new int[n]; // Preorder
        postToPre(n - 1, min, max, n - 1, postOrder, preOrder);
        System.out.println("Pre-Order Traversal: ");
        for (int j : preOrder) {
            System.out.print(j + " ");
        }
    }
    }