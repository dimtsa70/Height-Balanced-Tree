

import java.util.Scanner;

public class HW1 {

    public static void main(String[] args) {
        String[] word;
        HBNode root = null, result1;
        HBTree tree = new HBTree();
        int data, i, result, minheight;
        word = new String[2];
        word[0] = " ";
        word[1] = " ";
        Scanner sc = new Scanner(System.in);
        while (1 != 0) {
            i = 0;
            while(sc.hasNext()) {
                word[i] = sc.next();
                if(word[0].equals("-q")) {  //se periptwsh pou entolh == "-q" termatizei to systima
                    System.exit(0);
                }
                i++;
                if(i==2 || word[0].equals("-p")) {  //se periptwsh pou gemizei o pinakas 'h entolh == "p"
                    break;                          // break gia na epilexthei to swsto case
                }
            }
            data = Integer.valueOf(word[1]);
            switch (word[0]) {
                case "-i":// enthesh neou komvou                 
                   root = tree.insert(data);
                   if(root != null) {
                       System.out.printf("\n%d I\n", data);
                   }
                   else {
                       System.out.printf("\n%d NI\n", data);
                   }
                    break; 
                case "-f"://anazhthsh komvou
                    result1 = tree.findNode(root, data); 
                    if(result1 != null) {
                        System.out.printf("\n%d F\n", data);
                    }
                    else {
                        System.out.printf("\n%d NF\n", data);
                    }
                    break;
                case "-p"://ektypwsh se pre-order diaperash tou dendrou
                    if(tree.root == null) {
                        System.out.printf("\nsize: 0, max_height: 0, min_height: 0\n");
                        
                    }
                    else {
                        minheight = tree.minHeight(tree.root);//evresh elaxistou ipsous dendrou
                        System.out.printf("\nsize: %d, max_height: %d, min_height: %d\n", root.weight, root.height, minheight);
                        tree.preOrder(root);
                        System.out.printf("\n");
                    }
                    break;
                case "-d"://diagrafh komvou 
                    result1 = tree.deleteNode(root, data);
                    root = tree.root;
                    if(tree.root != null && result1 == null) {
                        System.out.printf("\n%d ND\n", data);
                    }
                    else {
                        System.out.printf("\n%d D\n", data);
                    }
                    break;
                
                default://lathos entolh
                    System.out.println("Invalid Command!\n");
                    continue;
            }
        }
    }
}


