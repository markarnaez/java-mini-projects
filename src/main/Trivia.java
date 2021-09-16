package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Trivia {

    private static int totalNumberOfItems = 0;
    private static int correctAnswers = 0;
    public static void main(String[] args) {

        System.out.println(
            "\n=================================" +
            "\nT R I V I A                      " +
            "\n=================================" );

        List<Question> questions = generateQuestions("./src/main/computer_history.txt", 5);
        takeTest(questions);

    }

    public static List<Question> generateQuestions(String filename, int size) {
        List<Question> questions = new ArrayList<Question>();

        try {
            FileInputStream fis = new FileInputStream(filename);
            Scanner sc = new Scanner(fis);
            int count = 1;
            String q = "";
            String a = "";
            while (sc.hasNextLine() && size > 0) {
                String line = sc.nextLine();

                if (line.startsWith("Q:")) {
                    line = count + ") " + line.split(":")[1];
                    count++;
                }

                if (line.startsWith("A:")) {
                    a = line.split(":")[1];
                    size--;
                    questions.add(new Question(q, a));
                    q = "";
                    continue;
                }
                q = q + line + "\n";
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Test file does not exist.");
            e.printStackTrace();
        }
        return questions;
    }

    public static void takeTest(List<Question> questions) {
        Scanner input = new Scanner(System.in);
        int score = 0;
        totalNumberOfItems = questions.size();
        for (int x = 0; x < totalNumberOfItems; x++) {
            System.out.println("\n" + questions.get(x).prompt);
            String userAnswer = "";
            while(true){
                System.out.print("Enter answer: ");
                userAnswer = input.nextLine();
                if(Arrays.asList("A","B","C","D").contains(userAnswer.toUpperCase())){
                    break;
                }
                System.out.println("Invalid input.");
            }
            if (questions.get(x).answer.toLowerCase().equals(userAnswer.toLowerCase())) {
                score++;
            }
        }
        input.close();
        correctAnswers = score;
        System.out.println("\nYou got " + score + " out of " + questions.size());
        System.out.println("Score: " + calculateScore());
    }

    public static int calculateScore(){
        return (correctAnswers * 100)/totalNumberOfItems;
    }
}

class Question {
    String prompt;
    String answer;

    public Question(String prompt, String answer) {
        this.prompt = prompt;
        this.answer = answer;
    }
}
