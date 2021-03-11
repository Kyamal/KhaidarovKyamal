package tasks.first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

public class FirstTaskSolution implements FirstTask{

    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {

        ArrayDeque<Integer> vertexes = new ArrayDeque<Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        vertexes.addFirst(startIndex);
        result.add(startIndex);
        while (!vertexes.isEmpty()) {
            for (int i = 0; i < adjacencyMatrix[startIndex - 1].length; i++) {
                if (adjacencyMatrix[startIndex - 1][i]) {
                    if (check(result, i + 1)) {
                        vertexes.addLast(i + 1);
                        result.add(i + 1);
                    }
                }

            }
            vertexes.pollFirst();
            try {
                startIndex = vertexes.peekFirst();
            } catch (NullPointerException e) {
                String size = result.toString();
                return (result.toString().substring(1,size.length()-1));
            }
        }
        return null;
    }

    boolean check(ArrayList<Integer> result, Integer check) {

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).equals(check)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean validateBrackets(String s) {
        Stack<Character> brace = new Stack<>();
        char[] chArray = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (chArray[i] == '(' | chArray[i] == '[' | chArray[i] == '{') {
                brace.push(chArray[i]);
            }
            if (chArray[i] == ')' | chArray[i] == ']' | chArray[i] == '}') {
                if (brace.isEmpty()) {
                    return false;
                } else {
                    if ((brace.peek().equals('(') & chArray[i] == ')') | (brace.peek().equals('[') & chArray[i] == ']') | (brace.peek().equals('{') & chArray[i] == '}')) {
                        brace.pop();
                    }
                }
            }
        }
        if (brace.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Long polishCalculation(String s) {
        ArrayDeque<Long> longs = new ArrayDeque<>();
        String[] strings = s.split(" ");
        Long counter = Long.valueOf(0);
        for (int i = 0; i < strings.length; i++) {
            if (!(strings[i].equals("+") | strings[i].equals("-") | strings[i].equals("*") | strings[i].equals("/"))) {
                longs.addLast(Long.parseLong(strings[i]));
            } else {
                counter = longs.peekLast();
                longs.pollLast();
                if (strings[i].equals("+")) {
                    try {
                        counter = longs.peekLast() + counter;
                    } catch (NullPointerException e) {
                        throw new IllegalArgumentException();

                    }
                }
                if (strings[i].equals("-")) {
                    try {
                        counter = longs.peekLast() - counter;
                    } catch (NullPointerException e) {
                        throw new IllegalArgumentException();

                    }

                }
                if (strings[i].equals("*")) {
                    try {
                        counter = longs.peekLast() * counter;
                    } catch (NullPointerException e) {
                        throw new IllegalArgumentException();
                    }
                }
                if (strings[i].equals("/")) {
                    try {
                        counter = longs.peekLast() / counter;
                    } catch (NullPointerException e) {
                        throw new IllegalArgumentException();
                    }

                }


                longs.pollLast();
                longs.addLast(counter);


            }
        }
        Long result = counter;
        longs.pollLast();
        if (longs.isEmpty()) {
            return result;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
