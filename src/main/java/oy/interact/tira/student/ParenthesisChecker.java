package oy.interact.tira.student;

import oy.interact.tira.student.ParenthesesException;

import java.util.HashMap;

import oy.interact.tira.util.StackInterface;

public class ParenthesisChecker {

   private ParenthesisChecker() {
   }

   /**
    * TODO: Implement this method which checks if the given string has matching opening and closing
    * parentheses. It should check for matching parentheses:

    *   Lorem ipsum ( dolor sit {  amet, [ consectetur adipiscing ] elit, sed } do eiusmod tempor ) incididunt ut...,
    * 
    * that can be found for example in Java source code and JSON structures.
    * 
    * If the string has issues with parentheses, you should throw a {@code ParenthesisException} with a
    * descriptive message and error code. Error codes are already defined for you in the ParenthesesException
    * class to be used.
    *
    * Note that the exception thrown must include correct line and column numbers of the
    * place where it became obvious that there are incorrect parenthesis in the input text.
    *
    * What is to be tested about the incoming string:
    *
    * - When an opening parenthesis is found in the string, it is successfully pushed to the stack (push may fail).
    * - When a closing parenthesis is found in the string, chech that a matching opening parenthesis is popped from the stack.
    * - If the stack was empty, this indicates an error, too many opening parentheses (or too few closing ones).
    *   Note that you can check if the stack is empty before calling pop() and throw an exception.
    * - When the string has been handled, and if the stack still has parentheses, there are too few closing parentheses.
    * 
    * @param stack The stack object used in checking the parentheses from the string.
    * @param fromString A string containing parentheses to check if it is valid.
    * @return Returns the number of parentheses found from the input in total (both opening and closing).
    * @throws ParenthesesException if the parentheses did not match as intended.
    */
    public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {
      // TODO (for grade 1, see instructions for higher grades):
      // for each character in the input string
      //   if character is an opening parenthesis -- one of "([{"
      //      push it into the stack (check for failure and throw an exception if so)
      //   else if character is a closing parenthesis -- one of ")]}"
      //      pop the latest opening parenthesis from the stack
      //      if pop() throws an exception, there are too many closing parentheses 
      //      check the popped opening parenthesis against the closing parenthesis read from the string
      //      if they do not match -- opening was { but closing was ], for example.
      //         throw an exception, wrong kind of parenthesis were in the text (e.g. "asfa ( asdf } sadf")
      // if the stack is not empty after all the characters have been handled
      //   throw an exception since the string has more opening than closing parentheses.
      int count = 0;
      int line = 1;
      int columnNumber = 1;
      HashMap<Character, Character> parentheses = new HashMap<>();
      parentheses.put('(', ')');
      parentheses.put('{', '}');
      parentheses.put('[', ']');
  
      try {
          for (char oneChar : fromString.toCharArray()) {
              if (parentheses.containsKey(oneChar)) {
                  try {
                      stack.push(oneChar);
                      count++;
                  } catch (Exception e) {
                      throw new ParenthesesException("Stack failure", line, columnNumber, ParenthesesException.STACK_FAILURE);
                  }
              } else if (parentheses.containsValue(oneChar)) {
                  if (stack.isEmpty()) {
                      throw new ParenthesesException("Too many closing parentheses", line, columnNumber, ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
                  }
                  char lastOpen = stack.pop();
                  if (parentheses.get(lastOpen) != oneChar) {
                      throw new ParenthesesException("Mismatched parentheses", line, columnNumber, ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
                  }
                  count++;
              }
              if (oneChar == '\n') {
                  line++;
                  columnNumber = 1;
              }
              else{
                columnNumber++;
              }
          }
          if (!stack.isEmpty()) {
              throw new ParenthesesException("Too many opening parentheses", line, columnNumber, ParenthesesException.TOO_MANY_OPENING_PARENTHESES);
          }
      } catch (ParenthesesException e) {
          throw e;
      } catch (Exception e) {
          throw new ParenthesesException("Stack failure", line, columnNumber, ParenthesesException.STACK_FAILURE);
      }
  
      return count;
   }
}
