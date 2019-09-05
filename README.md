# Sample-Quiz
This is Sample-Quiz Android Application.

This has 3 major screens (1 Activity 3 Fragments)
MainActivity holds a FrameLayout to show the Multiple fragments one by one based on the user action. 
We have 3 Different fragments namely QuizFragment.java, QuizQuestionsFragment.java & QuizSuccessFragment.java.

QuizFragment.java, it has only one button to start Quiz.

QuizQuestionsFragment.java, This plays a major role in this project.  Actually, it will read the Question.json file from the assert folder & convert json string into a Jave-Data-Model and this will show Questions with the options (options shows with RadioButton). It has one Recyclerview & it's adapter. 

Logic: on every selection of the options it will store the answers in a list(if already answered question answers changed it will replace with the respective position). Once you have look-in to the codebase you will understand I used interface to done this process.

QuizSuccessFragment.java, This is the Last page for this app. Once you gave submit of Quiz questions page then, this page will be visible. In this page we can see how many answers you answered correctly. And this page we can see the Answers with the questions. 


For Overall,

1. MVC architecture
2. Read File From Assert Folder.
3. Used Retrofit Gson Parser to parse Json string to a Data Model 
4. Used Single Activity with multiple fragment.
5. override the back-press method
6. RecyclerView inside RecyclerView
7. For all XML-designs uesed LinearLayout only.  



