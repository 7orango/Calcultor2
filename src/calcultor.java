


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

    public class calcultor {

        //被操作的表达式
        private static LinkedList<String> operators=new LinkedList<>();
        //输出值
        private static LinkedList<String> output=new LinkedList<>();
        //输出的数据
        private static StringBuilder PosFixEx=new StringBuilder();

        public static void main(String[] args) {
            LinkedList<String> list=new LinkedList<>();
            Scanner scanner=new Scanner(System.in);
            String s;

            System.out.println("输入一个中缀表达式，并最后加上&表示输入完成");
            while (!(s=scanner.next()).equals("&")) {//&为结尾标志
                list.add(s);
            }
            convert(list);
            scanner.close();
            System.out.println( output);


            evaluate(list);
        }
        /**
         *Evaluate the postfix expression
         * @param list
         * @param list
         */
        private static void evaluate(LinkedList<String> list){
            LinkedList<String> newList=new LinkedList<>();
            String[] postStr=PosFixEx.toString().split(" ");


            for (String s:postStr) {
                if (isOperator(s)){
                    if (!newList.isEmpty()){
                        int num1=Integer.valueOf(newList.pop());
                        int num2=Integer.valueOf(newList.pop());


                        if (s.equals("/")&&num1==0){
                            System.out.println("除数为0错误 ");
                            return;
                        }
                        int newNum=caluate(num2,num1,s);
                        newList.push(String.valueOf(newNum));
                    }
                }
                else {

                    newList.push(s);
                }
            }
            if (!newList.isEmpty()){
                System.out.println("result: "+newList.pop());
            }


        }


        /**
         * Implement the caluation
         * @param num2
         * @param num1
         * @param s
         * @return the result
         */
        private static int caluate(int num2, int num1, String s) {

            switch(s) {
                case"+":return num1+num2;


                case"-":return num1-num2;

                case"*":return num1*num2;

                case"/":return num1/num2;
                default:return 0;
            }

        }
        /**
         *  change a infix expression to a Postfix expression
         * @param list a infix expression
         */
        private static void convert(LinkedList<String> list){
            Iterator<String> x=list.iterator();
            while (x.hasNext()) {
                String b = x.next();//将输入的表达式分开


                if (isOperator(b)) {
                    if (operators.isEmpty()) {
                        operators.push(b);
                    }


                    else {
                        if (advence(operators.peek())<=advence(b)&&!b.equals(")")) {
                            operators.push(b);
                        }
                        else if(!b.equals(")")&&advence(operators.peek())>advence(b)){
                            while (operators.size()!=0
                                    &&advence(operators.peek())>=advence(b)
                                    &&!operators.peek().equals("(")) {
                                if (!operators.peek().equals("(")) {
                                    String operator=operators.pop();
                                    PosFixEx.append(operator).append(" ");
                                    output.push(operator);
                                }
                            }
                            operators.push(b);
                        }
                        else if (b.equals(")")) {
                            while (!operators.peek().equals("(")) {
                                String operator=operators.pop();
                                PosFixEx.append(operator).append(" ");
                                output.push(operator);
                            }
                            operators.pop();
                        }
                    }
                }
                else {
                    PosFixEx.append(b).append(" ");
                    output.push(b);
                }
            }
            if (!operators.isEmpty()) {
                Iterator<String> iterator=operators.iterator();


                while (iterator.hasNext()) {
                    String operator=iterator.next();
                    PosFixEx.append(operator).append(" ");
                    output.push(operator);
                    iterator.remove();
                }
            }


        }

        public ArrayList<String> stal(){

            String postfix=PosFixEx.toString();
            ArrayList<String> p=new ArrayList<>();
            for(int i=0;i<postfix.length();i++) {
                if(postfix.charAt(i)!=' ')
                    p.add(String.valueOf(postfix.charAt(i)));
            }
            return p;

        }

        /**
         * 判断操作符
         * @param value
         * @return
         */
        private static boolean isOperator(String value){
            if (value.equals("+")||value.equals("-")
                    ||value.equals("/")||value.equals("*")
                    ||value.equals("(")||value.equals(")")) {
                return true;
            }
            return false;
        }


        /**
         * 设定操作符的优先级
         * @param s
         * @return
         */
        private static int advence(String s){
            if(s== "+")
                return 1;

            if(s== "-")
                return 1;
            if(s== "*")
                return 2;
            if(s== "/")
                return 2;
            if(s== "(")
                return 3;
            if(s== ")")
                return 3;
            else
                return 0;

        }

    }









