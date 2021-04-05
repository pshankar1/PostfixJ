import java.io.IOException;

/************
The output iterates 
Postfix notation   !

********************/

public class Main {
  public static void main(String[] args) throws IOException{
      String val="((5+2)*(8-3))/4";
      String p;
      
      Main theTrans=new Main(val);
      p=theTrans.move();
      System.out.println("Postfix verison: "+p+'\n');

    }
    class Stack{
      private int hLength;
      private char[] stackArray;
      private int tLayer;

      public Stack(int max){
        hLength=max;
        stackArray=new char[hLength];
        tLayer=-1;
      }
    /***************
	 * Push()
	 *  needs to get value from user
	 *  basically gets value from user hence valN
	 *  refers to StackElement method and puts value at the top of the stack
	 *  increases the length hence len++ 
	 * Type E
	 ***************/
      public void push(char i){
        stackArray[++tLayer]=i;

      }
      /********
	 * Pop()
	 * Retrieve and remove stack element from top
	 **********/
      public char pop(){
        return stackArray[tLayer--];
      }
  /********
	 * Peek()
	 *gets first element of stacks
	 *and will show it at the top
	 **********/
      public char peek(){
        return stackArray[tLayer--];
      }
/*******
 * checks if element isEmpty()
 */
      public boolean isEmpty(){
        return(tLayer==-1);
      }
    }
    private Stack s;
    private String val;
    private String p=" ";

    public Main(String ss){
      val=ss;
      int size=val.length();
      s=new Stack(size);

    }

/***************
This moves around the values in the notation!
this serves as a switch case and establishes the ()
as characters 
+ - ()
*************/
    public String move(){
      for(int i=0;i<val.length();i++){
        char vv=val.charAt(i);
        switch(vv){
          case'+':
          case'-':
          gotOper(vv,1);
          break;
          case '(':
            s.push(vv);
            break;
          case ')':
            par(vv);
            break;
          default:
            p=p+vv;
            break;
        }
      }
      while(!s.isEmpty()){
        p=p+s.pop();
      }
      System.out.println(p);
      return p;
    }

    public void gotOper(char opThis,int o1){
      while(!s.isEmpty()){
        char opTop=s.pop();
        if(opTop =='('){
          s.push(opTop);
          break;
        }else{
          int o2;
          if(opTop=='+' || opTop=='-')
          o2=1;
          else
          o2=2;
          if(o2<o1){
            s.push(opTop);
            break;
          }
          else p=p+opTop;
        }
      }
      s.push(opThis);
    }

    public void par(char ch){
      while(!s.isEmpty()){
        char l=s.pop();
        if(l=='(')
        break;
        else p=p+l;
      }

    }

    
      
    
}