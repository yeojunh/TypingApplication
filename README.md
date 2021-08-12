# CPSC 210 FINAL PROJECT - TYPING MASTER

## What will the application do?
For my CPSC 210 project, I will be creating a **typing practice application** that measures the user's *typing speed and accuracy*. 
The user will be able to track their previous typing test records for typing speed and accuracy.
The typing speed will be calculated in words per minute (wpm), and accuracy will be based on the percentage of words typed correctly. 

## Who will use it?
Anyone who wants to improve their typing speed and accuracy is welcome and encouraged to use this application! 
The target audience is users who type frequently, including but not limited to:
- Students (especially in computer science)
- Typists
- Writers

## Why is this project of interest to you?
As a student in computer science, typing is an essential aspect of my major. A higher typing speed and greater accuracy is an asset, and my go-to typing practice website is [monkeytype](https://monkeytype.com/) for its simple graphics. So, I would like to create a simple typing practice application with a clean design for an optimal typing practice. 

## User Stories
- As a user, I want to be able to view my typing speed and accuracy 
- As a user, I want to be able to select which area I want to focus on 
            (regular, short phrases, punctuation-focused, number-focused)
- As a user, I want to be able to add my current typing run to my history of typing results, and any upcoming runs 
- As a user, I want to be able to view a list of previous typing results 
            with their respective typing speed and accuracy, and the overall typing speed and accuracy
- As a user, I want to be able to save my typing history to file
- As a user, I want to be able to load my last typing history automatically when I open the application
- As a user, I want to be able to clear my typing history
- As a user, I want to be able to reload my last deleted typing history, just in case I accidentally clear it

## Phase 4: Task 2
- Test and design a class in your model package that is robust.  You must have at least one method that throws a checked exception.  You must have one test for the case where the exception is expected and another where the exception is not expected.

The robust class
- **TypingPractice** class in model package

The methods with a robust design: 
- **finishedTyping()**
  - Throws IllegalFinishException if the program's isTyping field is false, and therefore has not started the timer for typing speed calculation. 
  - By throwing IllegalFinishException, we can ensure that the elapsed time calculates properly.
  - We catch this exception in TypingScreen's keyListenerSetup method and print an error message.
- **setupWordsAndArrayLists(String userTyped)**
  - Throws EmptyStringException if the user input is empty. 
  - We cannot split the user input (String) into an arraylist of words if there are no words in the String. 
  - We catch this exception in determineNumWordsTypedIncorrectly and utilize this indication to set the number of words typed incorrectly = the length of the phrase the user is prompted to type (everything is incorrect).
- **choosePhraseToType(String focus)**
  - Throws IllegalFocusException if the focus that the caller entered is invalid (must be one of "regular", "short", "punctuation" , "number". 
  - We catch this exception in load___Typing() (where ___ is Regular/Short/Punctuation/Number), and send an error message that the focus is invalid.