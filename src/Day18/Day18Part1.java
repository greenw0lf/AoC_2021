package Day18;

import static java.lang.Math.ceil;
import Read.ReadingOfFiles;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Snailfish {
    int data;
    Snailfish parent;
    Snailfish left;
    Snailfish right;
    public Snailfish(int data) {
        this.data = data;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public Snailfish parseString(String str) {
        if (str.charAt(0) != '[') {
            this.data = Character.getNumericValue(str.charAt(0));
            return this;
        }
        int i = 2;
        int count = str.charAt(1) == '[' ? 1 : 0;
        while (count > 0) {
            if (str.charAt(i) == '[')
                count++;
            if (str.charAt(i) == ']')
                count--;
            i++;
        }
        this.left = new Snailfish(-1);
        this.left.parseString(str.substring(1,i));
        this.left.parent = this;
        this.right = new Snailfish(-1);
        this.right.parseString(str.substring(i+1,str.length()-1));
        this.right.parent = this;
        return this;
    }

    public String toString() {
        if (this.data != -1)
            return String.valueOf(this.data);
        String str = "[";
        str += this.left.toString();
        str += ",";
        str += this.right.toString();
        str += "]";
        return str;
    }

    public int height() {
        if (this.left == null)
            return 0;
        int left = this.left.height() + 1;
        int right = this.right.height() + 1;
        return Math.max(left, right);
    }

    public void explode() {
        if (this.left.height() >= this.right.height() && this.left.height() > 0)
            this.left.explode();
        else if (this.right.height() > this.left.height() && this.right.height() > 0)
            this.right.explode();
        else {
            this.explodeLeft(this.parent, this, this.left.data);
            this.explodeRight(this.parent, this, this.right.data);
            this.left = null;
            this.right = null;
            this.data = 0;
        }
    }

    public void explodeLeft(Snailfish parent, Snailfish child, int data) {
        if (parent != null)
            if (parent.left.equals(child))
                explodeLeft(parent.parent, parent, data);
            else if (parent.left.data == -1 && child != null)
                explodeRight(parent.left, null, data);
            else if (parent.left.data == -1)
                explodeLeft(parent.left, null, data);
            else
                parent.left.data += data;
    }

    public void explodeRight(Snailfish parent, Snailfish child, int data) {
        if (parent != null)
            if (parent.right.equals(child))
                explodeRight(parent.parent, parent, data);
            else if (parent.right.data == -1 && child != null)
                explodeLeft(parent.right, null, data);
            else if (parent.right.data == -1)
                explodeRight(parent.right, null, data);
            else
                parent.right.data += data;
    }

    public int split() {
        if (this.data > 9) {
            this.left = new Snailfish(this.data/2);
            this.right = new Snailfish((int) ceil(this.data / 2.0));
            this.data = -1;
            this.left.parent = this;
            this.right.parent = this;
            return -1;
        }
        else if (this.data != -1)
            return 0;
        int l = 0;
        int r = 0;
        if (this.left != null)
            l = this.left.split();
        if (this.right != null && l != -1)
            r = this.right.split();
        return Math.min(l, r);
    }

    public int magnitude() {
        if (this.data != -1)
            return this.data;
        else return 3*this.left.magnitude() + 2*this.right.magnitude();
    }
}

public class Day18Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = ReadingOfFiles.readFile(18);
        String str = sc.next();

        Snailfish root = new Snailfish(-1);
        Snailfish l = new Snailfish(-1);
        l.parseString(str);
        while (sc.hasNext()) {
            String str2 = sc.next();
            Snailfish r = new Snailfish(-1);
            root = new Snailfish(-1);
            r.parseString(str2);
            root.left = l;
            root.right = r;
            root.left.parent = root;
            root.right.parent = root;

            int spl = -1;
            while (spl == -1 || root.height() > 4) {
                spl = -1;
                if (root.height() > 4) {
                    root.explode();
                } else {
                    spl = root.split();
                }
            }

            l = new Snailfish(-1);
            l.parseString(root.toString());
        }

        System.out.println(root.magnitude());
    }
}
