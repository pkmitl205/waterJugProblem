import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pakon
 */
public class usingBFS {

    public static LinkedList<State> visited = new LinkedList<State>();
    public static Dictionary<String, String> backLink = new Hashtable<String, String>();
    public static Queue<State> q = new LinkedList<State>();
    public static Stack<String> s = new Stack<String>();
    public static int jug_one, jug_two, fill;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("======================================\n");
        System.out.print("Water Jug Problem Solution By BFS\n");
        System.out.print("======================================\n");
        
        System.out.print("Enter size of jug one : ");
        jug_one = Integer.parseInt(br.readLine());
        System.out.print("Enter size of jug two : ");
        jug_two = Integer.parseInt(br.readLine());
        System.out.print("Enter volume of fill : ");
        fill = Integer.parseInt(br.readLine());
        
        System.out.print("\nStart State (x,y)\n");
        System.out.print("======================================\n");
        int x, y;
        System.out.print("Enter value of x : ");
        x = Integer.parseInt(br.readLine());
        System.out.print("Enter value of y : ");
        y = Integer.parseInt(br.readLine());
        State initial = new State(x, y);
        q.add(initial);
        
        System.out.println("\nInitial State ("+x+","+y+")");
        System.out.print("======================================");
        
//        State initial = new State(0, 0);
//        q.add(initial);

        try {
            generateEnque();
        }
        catch (Exception e) {
            System.out.print("\n");
            System.out.print("\nSolution\n");
            System.out.print("======================================\n");
            System.out.println("No Solution exists");
        }
    }
    public static void generateEnque() {
        State p = q.poll();
        if(!isVisited(p)){
            visited.add(p);
            generateState(p);
        }
        generateEnque();
    }
    public static void generateState(State p)
    {
        //fill
        System.out.print("\nChildren of Node ("+p.x+","+p.y+") are : ");
        if(p.x < jug_one)
        {
            State pNew = new State(jug_one, p.y);
            q.add(pNew);
            System.out.print("("+pNew.x+","+pNew.y+")");
            
            if(backLink.get("("+pNew.x+","+pNew.y+")") == null)
            {
               backLink.put("("+pNew.x+","+pNew.y+")","("+p.x+","+p.y+")");
               checkIfSolution(pNew);
            }
        }
        if(p.y < jug_two)
        {
            State pNew = new State(p.x, jug_two);
            q.add(pNew);
            System.out.print("("+pNew.x+","+pNew.y+")");
            
            if(backLink.get("("+pNew.x+","+pNew.y+")") == null)
            {
               backLink.put("("+pNew.x+","+pNew.y+")","("+p.x+","+p.y+")");
               checkIfSolution(pNew);
            }
        }
        
        //fill over
        if(p.x > 0){
            State pNew = new State(0, p.y);
            q.add(pNew);
            System.out.print("("+pNew.x+","+pNew.y+")");
            
            if(backLink.get("("+pNew.x+","+pNew.y+")") == null)
            {
               backLink.put("("+pNew.x+","+pNew.y+")", "("+p.x+","+p.y+")");
               checkIfSolution(pNew);
            }
        }
        if(p.y > 0){
            State pNew = new State(p.x, 0);
            q.add(pNew);
            System.out.print("("+pNew.x+","+pNew.y+")");
            
            if(backLink.get("("+pNew.x+","+pNew.y+")") == null)
            {
               backLink.put("("+pNew.x+","+pNew.y+")", "("+p.x+","+p.y+")");
               checkIfSolution(pNew);
            }
        }
        
	//pour
	if(p.x < jug_one && p.y != 0)
        {
            if(p.x+p.y > jug_one)
            {
                int One = jug_one;
                int Two = p.x+p.y - jug_one;
                State pNew = new State(One,Two);
                q.add(pNew);
                System.out.print("("+pNew.x+","+pNew.y+")");
                
                if(backLink.get("("+pNew.x+","+pNew.y+")") == null)
                {
                   backLink.put("("+pNew.x+","+pNew.y+")", "("+p.x+","+p.y+")");
                   checkIfSolution(pNew);
                }
            }
            else if(p.x+p.y < jug_one)
            {
                State pNew = new State(p.x+p.y,0);
                q.add(pNew);
                System.out.print("("+pNew.x+","+pNew.y+")");
                
                if(backLink.get("("+pNew.x+","+pNew.y+")") == null)
                {
                   backLink.put("("+pNew.x+","+pNew.y+")", "("+p.x+","+p.y+")");
                   checkIfSolution(pNew);
                }
            }
	}
	if(p.y < jug_two && p.x != 0)
        {
            if(p.y+p.x > jug_two)
            {
                int One = p.y+p.x - jug_two;
                int Two = jug_two;
                State pNew = new State(One,Two);
                System.out.print("("+pNew.x+","+pNew.y+")");
                
                if(backLink.get("("+pNew.x+","+pNew.y+")") == null)
                {
                   backLink.put("("+pNew.x+","+pNew.y+")", "("+p.x+","+p.y+")");
                   checkIfSolution(pNew);
                }
            }
            else if(p.x+p.y < jug_two)
            {
                State pNew = new State(0,p.x+p.y);
                q.add(pNew);
                System.out.print("("+pNew.x+","+pNew.y+")");
                
                if(backLink.get("("+pNew.x+","+pNew.y+")") == null)
                {
                   backLink.put("("+pNew.x+","+pNew.y+")", "("+p.x+","+p.y+")");
                   checkIfSolution(pNew);
                }
            }
            //pour over
            System.out.print("");
        }
    }
        public static boolean checkIfSolution(State p)
        {
            if(p.x == fill && p.y == 0)
            {
                System.out.print("\n");
                System.out.print("\nSolution\n");
                System.out.print("======================================\n");
                
                String parent = "("+p.x+","+p.y+")";
                
                while(!parent.equals("(0,0)"))
                {
                    s.push(parent);
                    parent = backLink.get(parent);
                }
                
                s.push("(0,0)");
                
                for(int i = s.size()-1; i >= 0; i--)
                {
                    System.out.println(s.get(i));
                }
                
                System.exit(0);
            }
            return false;
    }
        public static boolean isVisited(State p)
        {
            for(State check : visited)
            {
                if(p.x == check.x && p.y == check.y)
                {
                    return true;
                }
            }
            return false;
        }
}
