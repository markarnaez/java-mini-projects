package main;
import javax.swing.JOptionPane;

public class Quiz
{
	private static boolean isValid(String a)
	{
		a = a.toLowerCase();
		if(a.equals("a") || a.equals("b") || a.equals("c"))
		{
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please pick A, B, or C");
			return false;
		}
	}

	private static String askQuestion(String[] q)
	{
		String fmtQuestion = "";
		String answer = "";

		for(int i = 0; i < q.length; i++)
			fmtQuestion += q[i] + "\n";

		do
		{
			answer = JOptionPane.showInputDialog(null, fmtQuestion);
			if(answer == null)
			{
				int quit = JOptionPane.showConfirmDialog(null, "Would you like to quit?", "Quit", JOptionPane.YES_NO_OPTION);
				if(quit == 0)
					return "ABORT";
				else
					continue;
			}
		} while (answer == null || !(isValid(answer)));

		return answer;
	}

	private static boolean isCorrect(String a, String r)
	{
		r = r.toUpperCase();
		if(a.equals(r))
		{
			JOptionPane.showMessageDialog(null, "Correct!");
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "The correct answer is: \n" + a);
			return false;
		}
	}

	public static void showGrade(int c, int i)
	{
		int numberQuestions = c + i;
		String fmtGrade = "";
		int pointsPerQuestion = 100 / numberQuestions;
		int grade = c * pointsPerQuestion;

		fmtGrade += "You answered " + c + " correctly and " + i + " incorrectly";
		fmtGrade += "\nYour grade is: " + grade + "%";

		JOptionPane.showMessageDialog(null, fmtGrade);
	}

	public static void main(String[] args)
	{
		int i = 0;
		int correct = 0;
		int incorrect = 0;
		String response = "";


		String[][] question = new String[5][4];
		String[] correctAnswer = new String[5];

		question[0][0] = "Which one of these is not a primitive data type?";
		question[0][1] = "A) integer";
		question[0][2] = "B) char";
		question[0][3] = "C) String";
		correctAnswer[0] = "C";

		question[1][0] = "Java source code is stored in files with what extension?";
		question[1][1] = "A) .class";
		question[1][2] = "B) .java";
		question[1][3] = "C) .jav";
		correctAnswer[1] = "B";

		question[2][0] = "The best environment for developing Java applications is?";
		question[2][1] = "A) A UNIX server hosted by a corporate entity";
		question[2][2] = "B) Eclipse IDE";
		question[2][3] = "C) Whatever you are most comfortable in";
		correctAnswer[2] = "C";

		question[3][0] = "Java can be used to write?";
		question[3][1] = "A) Web Applications";
		question[3][2] = "B) Desktop programs";
		question[3][3] = "C) All of the above";
		correctAnswer[3] = "C";

		question[4][0] = "Which GUI toolkit comes included with Java?";
		question[4][1] = "A) Swing";
		question[4][2] = "B) Gtk";
		question[4][3] = "C) JavaFX";
		correctAnswer[4] = "A";

		do
		{
			response = askQuestion(question[i]);
			if(response.equals("ABORT"))
				return;
			if(isCorrect(correctAnswer[i], response))
				correct += 1;
			else
				incorrect += 1;

			i++;
		} while(i < question.length);

		showGrade(correct, incorrect);
	}
}