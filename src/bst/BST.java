/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bst;

import org.w3c.dom.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author harlan.howe
 */
public class BST {

    private TreeNode root;
    private TreeNodeR rootR;
    private int depth;
    
    public BST()
    {
        //loads the words from the dictionary file into memory.
        ArrayList<String> dictionary = new ArrayList<String>();
        File inputFile = new File("word_list_moby_crossword.flat.txt");
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File("word_list_moby_crossword.flat.txt")));
            String word;
            while((word = reader.readLine())!=null)
            {
                dictionary.add(word);
            }

        }catch (FileNotFoundException fnfExp)
        {
            System.out.println("File not found.");
            fnfExp.printStackTrace();
        }
        catch (IOException ioExp)
        {
            ioExp.printStackTrace();
        }
        System.out.println("Dictionary Loaded. "+dictionary.size());

        
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("How many words should I add to the tree? ");
        int numWords = keyboard.nextInt();
        ArrayList<String> wordList = new ArrayList<>();
        
        for (int i=0; i<numWords; i++)
        {
            int which = (int)(Math.random()*dictionary.size());
            System.out.println(dictionary.get(which));
            add(dictionary.get(which));

            addR(dictionary.get(which)); //adds the words in reverse
            wordList.add(dictionary.get(which)); //adds the word to wordList
        }
    
        System.out.println("------------------");
        System.out.println(subString("", root));

        //prints words in reverse
        System.out.println("------------------");
        System.out.println(subStringR(rootR));

        //prints out the number of letters
        System.out.println("This list has " + characterLength(subString("", root)) + " letters.");

        //prints out the depth
        System.out.println(maxDepth(root) - 1);

        
        Scanner input = new Scanner(System.in);
        System.out.println("What word would you like to look for?");
        String word = input.next();
        System.out.println(hasWord(wordList, word));



    }
    


    public String subString(String prefix, TreeNode subroot)
    {
        if (subroot == null){
            return "";
        }
        else 
        {
            String result = "";
            result+= subString(prefix+"\t",subroot.getLeft());
            result+= prefix+subroot.getValue()+"\n";
            result+= subString(prefix+"\t",subroot.getRight());
            return result;
        }
    }
    public void add(String s)
    {
        if (root == null)
            root = new TreeNode(s);
        else
            addToSubTree(s,root);
    }
    
    public void addToSubTree(String s, TreeNode subroot)
    {
        int rel = s.compareTo(subroot.getValue());
        if (rel<0)
        {
            if (subroot.getLeft() == null) {
                subroot.setLeft(new TreeNode(s));
            }
            else {
                addToSubTree(s, subroot.getLeft());
            }
        }
        else
        {
            if (subroot.getRight() == null) {
                subroot.setRight(new TreeNode(s));
            }
            else {
                addToSubTree(s, subroot.getRight());
            }
        }
    }

//recursive method to find left and right in reverse
    public void addToSubTreeR (String s, TreeNodeR subroot)
    {
        int rel = s.compareTo(subroot.getValue());
        if (rel>0)
        {
            if (subroot.getLeft() == null)
                subroot.setLeft(new TreeNodeR(s));
            else
                addToSubTreeR(s,subroot.getLeft());
        }
        else
        {
            if (subroot.getRight() == null)
                subroot.setRight(new TreeNodeR(s));
            else
                addToSubTreeR(s,subroot.getRight());
        }
    }
//adds words to the reverse subtree
    public void addR(String s)
    {
        if (rootR == null)
            rootR = new TreeNodeR(s);
        else
            addToSubTreeR(s,rootR);
    }

//formats in reverse order
    public String subStringR(TreeNodeR subroot)
    {
        if (subroot == null)
            return "";
        else
        {
            String result = "";
            result+= subStringR(subroot.getLeft());
            result+= subroot.getValue()+"\n";
            result+= subStringR(subroot.getRight());
            return result;
        }
    }

//finds the number of letters by shortening the string letter by letter
    public static int characterLength (String s){
        String newString = s.replace("\n", "");

        if (newString.length() == 0){
            return 0;
        }

        else {
            String shorterString = newString.substring(1);
            return 1 + characterLength(shorterString);
        }
    }
//finds word by shortening wordList word by word
//sees if the first word is equal to the target
    public static boolean hasWord (ArrayList<String> wordList, String word){
        if (wordList.size() == 0){
            return false;
        }

        else {
            if (wordList.get(0).equals(word)){
                return true;
            }

            else {
                wordList.remove(0);
                return hasWord(wordList, word);
            }
        }
    }

    public static int maxDepth (TreeNode node){
        if (node == null){
            return 0;
        }

        else {
            int lDepth = maxDepth(node.getLeft());
            int rDepth = maxDepth(node.getRight());

            if (lDepth > rDepth){
                return (lDepth + 1);
            }

            else {
                return (rDepth + 1);
            }
        }


    }



}
