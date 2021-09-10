import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Registration {

    static Profile profile = new Profile();
    public static void main(String[] args) {
        System.out.println(
            "\n=================================" +
            "\nU S E R  R E G I S T R A T I O N " +
            "\n=================================" );

        LinkedHashSet<String> inputs =  new LinkedHashSet<String>();
        inputs.add("Last Name");
        inputs.add("First Name");
        inputs.add("Birthday");
        inputs.add("Gender");
        inputs.add("Course");
        inputs.add("School");
        inputs.add("Username");
        inputs.add("Password");

        try (Scanner scanner = new Scanner(System.in)) {
            for (String i : inputs){
                int c = 0;
                String  input = null;
                while(true){
                    if (c == 5 ){
                        System.out.println("Registration failed. Try again later. Bye!");
                        System.exit(1);
                    }
                    System.out.print(String.format("Enter %s: ", i));
                    input = scanner.nextLine();
                    if(input != null && !input.trim().isEmpty()){
                        if (profile.setAttribute(i, input.trim())){
                            break;
                        }
                    } else {
                        System.out.println(i + " cannot be empty.");
                    }
                    c++;
                }
            }
            if(userLogin(profile)){
                System.out.println("\nPROFILE");
                profile.showProfile(inputs);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean userLogin(Profile profile){
        System.out.println(
            "\n=================================" +
            "\nU S E R  L O G I N                        " +
            "\n=================================" );
        try (Scanner scanner = new Scanner(System.in)){
            String input = "";
            boolean isvalid = false;
            while(!isvalid){
                System.out.print("Username: ");
                input = scanner.nextLine();
                if (!isMatched(profile, "Username", input, true)){
                    System.out.println("Username not found.");
                    continue;
                }
                System.out.print("Password: ");
                input = scanner.nextLine();
                if (!isMatched(profile, "Password", input, false)){
                    System.out.println("Password did not match.");
                    continue;
                }
                isvalid = true;
            }
            System.out.println("\nLogin successful!\n=================================\n");
            profile.greetings();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isMatched(Profile profile, String attribute, String input, boolean ignoreCase){
        if (input != null && !input.trim().isEmpty()){
            if (!ignoreCase && input.equals(profile.getProperty(attribute).toString())){
                return true;
            }
            if (ignoreCase && input.equalsIgnoreCase(profile.getProperty(attribute).toString())){
                return true;
            }
        }
        return false;
    }
}

class Profile {

    private static HashMap<String,Object> attributes = new HashMap<>();
 
    public boolean setAttribute(String attribute, Object value){
        if (attribute.equalsIgnoreCase("birthday")){
            if(setAge(value.toString()) == null){
                return false;
            }
        } else if (attribute.equalsIgnoreCase("username") || attribute.equalsIgnoreCase("password")){
            if(!isValid(attribute, value)){
                return false;
            }
        }
        attributes.put(attribute, value);
        return true;
    }

    public Object getProperty(String attribute){
        return attributes.get(attribute);
    }

    public boolean isValid(String attribute, Object value){
        if (attribute.equalsIgnoreCase("username") || attribute.equalsIgnoreCase("password")){
            if (value.toString().length() < 6){
                System.out.println(attribute + " must be at least 6 characters.");
                return false;
            }
        }
        return true;
    }
    public void showProfile(LinkedHashSet<String> inputs) {
        int maxL = getMaxLength(inputs) + 2;
        for (String i : inputs){
            int pad = maxL - i.length();
            //System.out.println(i + " : " + attributes.get(i));
            System.out.format("%s %" + pad + "s %s\n",i, ": ", attributes.get(i));
            if (i.equalsIgnoreCase("birthday")){
                setAge(attributes.get(i).toString());
                i = "Age";
                pad = maxL - i.length();
                System.out.format("%s %" + pad + "s %s\n",i, ": ", attributes.get(i));
            }
        }
    }

    public Integer getMaxLength(LinkedHashSet<String>  inputs){
        int max = 0;
        for (String i : inputs){
            if(i.length() > max){
                max = i.length();
            }
        }
        return max;
    }

    public void greetings(){
        LinkedHashSet<String> details =  new LinkedHashSet<String>();
        String message = "Welcome %s %s %s a %s years old, %s student at %s.";
        details.add("Gender");
        details.add("First Name");
        details.add("Last Name");
        details.add("Age");
        details.add("Course");
        details.add("School");

        String args = "";
        for (String i : details){
            if (i.equalsIgnoreCase("gender")){
                char c = attributes.get(i).toString().toLowerCase().charAt(0);
                if (c == 'm'){
                    args = args + "Mr.,";
                } else if (c == 'f'){
                    args = args + "Ms.,";
                } else {
                    args = args + ",";
                }
                continue;
            }
            args = args + attributes.get(i).toString() + ",";
        }
        System.out.println(String.format(message, args.split(",")));
    }

    public Long setAge(String birthday) {
        Date today = new Date();
        Long age = null;
        try {
            Date birthdate = new SimpleDateFormat("MM/dd/yyyy").parse(birthday);
            Long diff = Math.abs(today.getTime() - birthdate.getTime());
            age = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) / 365;
            attributes.put("Age", age);
            // System.out.println("Your age is " + age);
        } catch (ParseException e) {
            System.err.println("Invalid date format. Use MM/dd/yyyy");
            //e.printStackTrace();
        }  
        return age;
    }
}