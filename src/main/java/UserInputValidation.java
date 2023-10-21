import personalinfovalidation.NameValidator;
import personalinfovalidation.SsnValidator;

import java.util.Optional;
import java.util.Scanner;

public class UserInputValidation {

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Hello!\nPlease enter your full name:");
            String name = scanner.nextLine();
            System.out.println("Thank you! Please enter your ssn:");
            String ssn = scanner.nextLine();

            boolean isSsnValid = SsnValidator.isValidSsn(ssn, Optional.empty());
            boolean isNameValid = NameValidator.isCorrectName(name);
            if (!isSsnValid || !isNameValid) {
                if (!isNameValid) {
                    System.out.println("I am sorry, You have entered an invalid name. Name can not be empty and should include Swedish alphabetical characters including swedish å, ä and Ö. Please try again.");
                }
                if (!isSsnValid) {
                    System.out.println("I am sorry, You have entered an incorrect personal number. ");
                }
                System.out.println("Please try again.");
            } else {
                System.out.println("Looks great! Thank you.");
                break;
            }
        }
    }
}


