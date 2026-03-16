package ce326.hw1;

public class HBTree {
    HBNode root;
    
    //kataskevasths dendrou
    public HBTree() {
        root = null;
    }

    //ypologismos max height komvou
    int height(HBNode root) {
        int leftHeight = 0, rightHeight = 0;
        if(root == null) {
            return(0);
        }
        leftHeight = height(root.left);
        rightHeight = height(root.right);
        return (Math.max(leftHeight, rightHeight) + 1);
    }
    
    //ypologismos weight komvou
    private int weight(HBNode root) {
        if(root == null) {
            return(0);
        }
        
        int count = 1;
        count += weight(root.left);
        count += weight(root.right);
        return(count);
     
    }
    
    //synarthsh pou elegxei an mphke o neos komvos 'h oxi
    public HBNode insert(int data) {
        root = insertWork(root, data);
        if(root != null) {
            return(root);
        }
        else {
            return(null);
        }
    }
    
    //synarthsh deksias peristrofhs
    private HBNode rightRotate(HBNode root) {
        HBNode leftnode = root.left;
        HBNode rightnode = leftnode.right;
        
        leftnode.right = root;
        root.left = rightnode;
        
        root.weight = weight(root);
        root.height = height(root);
        
        leftnode.weight = weight(leftnode);
        leftnode.height = height(leftnode);
        
        return(leftnode);
    }
    
    //synarthsh aristerhs peristrofhs
    private HBNode leftRotate(HBNode root) {
        HBNode rightnode = root.right;
        HBNode leftnode = rightnode.left;
        
        rightnode.left = root;
        root.right = leftnode;
        
        root.weight = weight(root);
        root.height = height(root);
        
        rightnode.weight = weight(rightnode);
        rightnode.height = height(rightnode);
        
        return(rightnode);
    }
    
    //synarthsh pou ypologizei to balance enos komvou
    private int balanceSub(HBNode root) {
        if(root == null) {
            return(0);
        }
        return(height(root.left) - height(root.right));
    }
    
    //synarthsh pou ypologizei to log2 enos akeraiou
    private double log2(int number) {
        double result = (Math.log(number) / Math.log(2));
        return result;
    }
   
    
    //kyria synarthsh entheshs neou komvou
    private HBNode insertWork(HBNode root,int data) {
        
        //to dendro einai adeio
        if(root == null) {
            root = new HBNode(data);
            return root;
        }
        
        //o komvos yparxei hdh
        if(root.data == data) {
            return root;
        }

        
        //h timh tou komvou mikroterh apo ton komvo
        //pou deixnei tora h synarthsh, ara epilegoume
        //aristero ypodendro
        else if(data < root.data) {
            root.left = insertWork(root.left, data);
        }
        
        //h timh tou komvou megalyterh apo ton komvo
        //pou deixnei tora h synarthsh, ara epilegoume
        //deksi ypodendro
        else {
            root.right = insertWork(root.right, data);
        }
        
        //ypologismos varous, ipsous tou neou komvou
        root.height = height(root);
        root.weight = weight(root);
        
        
        //diadikasia elegxou isorropias dendrou meta thn eisagwgh
        int balance = balanceSub(root);
        int conditionBalance = (int) Math.max(1, Math.floor(log2(root.weight)));
        int unsignedBalance = Math.abs(balance);
        
       //diplh aristerh peristrofh
        if(balance > 0 && unsignedBalance > conditionBalance && data < root.left.data) {
            root = rightRotate(root);
        }
        
        //diplh deksia peristrofh
        if(balance < 0 && unsignedBalance > conditionBalance && data > root.right.data) {
           root = leftRotate(root);
        }
        
        //aristerh deksia
        if(balance > 0 && unsignedBalance > conditionBalance && data > root.left.data) {
            root.left = leftRotate(root.left);
            root = rightRotate(root);
        }
        
        //deksia aristerh
        if(balance < 0 && unsignedBalance > conditionBalance && data < root.right.data) {
            root.right = rightRotate(root.right);
            root = leftRotate(root);
        }
        
        
    return (root);
    }
    
    //synarthsh anazhthshs komvou
    public HBNode findNode(HBNode root, int data) {
        HBNode searchNode = root;
        if(root == null) {
            return (null);
        }
        if(root.data == data) {
                return (root);
        }
        else if(root.data > data) {
            searchNode = findNode(root.left, data);
        }
         else {
            searchNode = findNode(root.right, data);
        }
        
        return(searchNode);
    }
    
    //synathsh epiloghs diadoxou se periptwsh
    //pou o komvos pou diagrafetai exei duo paidia
    private HBNode succesor(HBNode node) {
            HBNode curr = node;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }
    
    //synarthsh elegxou diagrafhs komvou
    public HBNode deleteNode(HBNode root, int data) {
        HBNode findnode = root;
        
        //anazhthsh komvou pros diagrafh
        findnode = findNode(findnode, data);
        
        //o komvos den yparxei
        if(findnode == null) {
            return(null);
        }
        else {
            
            //kalesma kyrias synarthshs diagrafhs
            this.root = deleteNodeWork(root, data);
            if(this.root == null) {
                return(null);
            }
        }
        return(root);
    }
    
    //synarthsh pou elegxei meta thn diagrafh an
    //to dendro exei eksisorrophthei
    private HBNode checkBalance(HBNode root) {
        if(root == null) {
            return(root);
        }
        
        root.weight = weight(root);
        root.height = height(root);
        
        int balance = balanceSub(root);
        int conditionBalance = (int) Math.max(1, Math.floor(log2(root.weight)));
        int unsignedBalance = Math.abs(balance);
        
       //diplh aristerh peristrofh
        if((balance > 0 && unsignedBalance > conditionBalance) && balanceSub(root.left) >= 0 ) {
            root = rightRotate(root);
            
            //diadikasia elegxou isorropias komvwn pou symmeteixan sthn peristrofh
            balance = balanceSub(root.left);
            conditionBalance = (int) Math.max(1, Math.floor(log2(root.left.weight)));
            unsignedBalance = Math.abs(balance);
            if(unsignedBalance > conditionBalance) {
               root.left = checkBalance(root.left);
            }
            balance = balanceSub(root.right);
            conditionBalance = (int) Math.max(1, Math.floor(log2(root.right.weight)));
            unsignedBalance = Math.abs(balance);
            if(unsignedBalance > conditionBalance) {
               root.right = checkBalance(root.right);
            }
            balance = balanceSub(root);
            conditionBalance = (int) Math.max(1, Math.floor(log2(root.weight)));
            unsignedBalance = Math.abs(balance);
            if(unsignedBalance > conditionBalance) {
               root = checkBalance(root);
            }    
        }
        
        //diplh deksia peristrofh
        if((balance < 0 && unsignedBalance > conditionBalance) && balanceSub(root.right) <= 0) {
           root = leftRotate(root);
           
            balance = balanceSub(root.left);
            conditionBalance = (int) Math.max(1, Math.floor(log2(root.left.weight)));
            unsignedBalance = Math.abs(balance);
            if(unsignedBalance > conditionBalance) {
               root.left = checkBalance(root.left);
            }
            balance = balanceSub(root.right);
            conditionBalance = (int) Math.max(1, Math.floor(log2(root.right.weight)));
            unsignedBalance = Math.abs(balance);
            if(unsignedBalance > conditionBalance) {
                root.right = checkBalance(root.right);
            }
            balance = balanceSub(root);
            conditionBalance = (int) Math.max(1, Math.floor(log2(root.weight)));
            unsignedBalance = Math.abs(balance);
            if(unsignedBalance > conditionBalance) {
                root = checkBalance(root);
            }
           
        }
        
        //aristerh deksia
        if((balance > 0 && unsignedBalance > conditionBalance) && balanceSub(root.left) < 0) {
            root.left = leftRotate(root.left);
            root = rightRotate(root);
            
            balance = balanceSub(root.left);
            conditionBalance = (int) Math.max(1, Math.floor(log2(root.left.weight)));
            unsignedBalance = Math.abs(balance);
            if(unsignedBalance > conditionBalance) {
               root.left = checkBalance(root.left);
            }
            balance = balanceSub(root.right);
            conditionBalance = (int) Math.max(1, Math.floor(log2(root.right.weight)));
            unsignedBalance = Math.abs(balance);
            if(unsignedBalance > conditionBalance) {
               root.right = checkBalance(root.right);
            }
            balance = balanceSub(root);
            conditionBalance = (int) Math.max(1, Math.floor(log2(root.weight)));
            unsignedBalance = Math.abs(balance);
            if(unsignedBalance > conditionBalance) {
               root = checkBalance(root);
            }
            
        }
        
        //deksia aristerh
        if((balance < 0 && unsignedBalance > conditionBalance) && balanceSub(root.right) > 0) {
            root.right = rightRotate(root.right);
            root = leftRotate(root);
            
            balance = balanceSub(root.left);
            conditionBalance = (int) Math.max(1, Math.floor(log2(root.left.weight)));
            unsignedBalance = Math.abs(balance);
            if(unsignedBalance > conditionBalance) {
                root.left = checkBalance(root.left);
            }
            balance = balanceSub(root.right);
            conditionBalance = (int) Math.max(1, Math.floor(log2(root.right.weight)));
            unsignedBalance = Math.abs(balance);
            if(unsignedBalance > conditionBalance) {
                root.right = checkBalance(root.right);
            }
            balance = balanceSub(root);
            conditionBalance = (int) Math.max(1, Math.floor(log2(root.weight)));
            unsignedBalance = Math.abs(balance);
            if(unsignedBalance > conditionBalance) {
               root = checkBalance(root);
            }
            
        }
        return(root);
    }
    
    //kyria synarthsh diagrafhs epilexthentos komvou
    HBNode deleteNodeWork(HBNode root, int data) {
        
        //dendro adeio
        if(root == null) {
            return (null);
        }
        
        
        //o komvos vrisketai sto aristero ypodendro
        if(data < root.data) {
             root.left = deleteNodeWork(root.left, data);
        }
        
        //o komvos vrisketai sto deksi ypodendro
        else if(data > root.data) {
            root.right = deleteNodeWork(root.right, data); 
        }
        //o komvos vrethike
        else {
            
            //ean o komvos den exei paidia 'h exei mono deksi paidi
            //epistrefei to deski paidi
            if(root.left == null) {
                return (root.right);
            }
            
            //ean o komvos exei aristero paidi
            //epistrefei auto
            if(root.right == null) {
                return (root.left);
            }
            
            //o komvos exei duo paidia
            HBNode suc = succesor(root.right);
            root.data = suc.data;
            root.right = deleteNodeWork(root.right, suc.data);
            
        }
        //elegxos isorropias dendrou
        root = checkBalance(root);
        
        return(root);
    }
    
    //synarthsh ektypwshs dendrou se pre-order
    public void preOrder(HBNode root) {
        if(root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
    
    //synarthsh evreshs elaxistou ipsous dendrou
    public int minHeight(HBNode root) {
        int leftHeight = 0, rightHeight = 0;
        if(root == null) {
            return(0);
        }
        leftHeight = minHeight(root.left);
        rightHeight = minHeight(root.right);
        return (Math.min(leftHeight, rightHeight) + 1);
    }
}
















