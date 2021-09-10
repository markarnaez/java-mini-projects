import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Trivia {
    public static void main(String[] args) {

        System.out.println(System.getProperty("user.dir"));
        List<Question> questions = generateQuestions("file/trivia1.txt", 5);
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
        for (int x = 0; x < questions.size(); x++) {
            System.out.println(questions.get(x).prompt);
            String userAnswer = input.nextLine();
            if (questions.get(x).answer.toLowerCase().equals(userAnswer.toLowerCase())) {
                score++;
            }
        }
        input.close();
        System.out.println("You got " + score + " out of " + questions.size());
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
