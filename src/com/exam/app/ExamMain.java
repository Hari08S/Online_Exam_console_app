package com.exam.app;

import java.util.Scanner;
import com.exam.bean.Question;
import com.exam.service.ExamService;
import com.exam.util.*;
public class ExamMain {
    private static ExamService examService;
    public static void main(String[] args) {
        examService = new ExamService();
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Online Exam Question Bank Console ---");
        // TEST 1: Add a new ACTIVE question
        try {
            Question q = new Question();
            q.setQuestionID("QST2002");   // use new ID
            q.setSubject("DBMS");
            q.setTopic("Transactions");
            q.setDifficulty("MEDIUM");
            q.setMarks(5.0);
            boolean r = examService.addNewQuestion(q);
            System.out.println(r ? "QUESTION ADDED" : "QUESTION ADD FAILED");
        } catch (ValidationException e) {
            System.out.println("Validation Error: " + e.toString());
        } catch (Exception e) {
            System.out.println("System Error: " + e.getMessage());
        }
        // TEST 2: Create a new DRAFT test paper
        try {
            boolean r = examService.createTestPaper(
                    "DBMS Practice Test",
                    "DBMS",
                    10.0,
                    "EASY=2,MEDIUM=8,HARD=0");

            System.out.println(r ? "TEST PAPER CREATED"
                                 : "TEST PAPER CREATION FAILED");

        } catch (QuestionPoolInsufficientException e) {
            System.out.println("Question Pool Error: " + e.toString());
        } catch (ValidationException e) {
            System.out.println("Validation Error: " + e.toString());
        } catch (Exception e) {
            System.out.println("System Error: " + e.getMessage());
        }
        // TEST 3: Attempt to remove a question from published paper
        try {
            boolean r = examService.removeQuestion("QST1002");
            System.out.println(r ? "QUESTION REMOVED"
                                 : "QUESTION REMOVAL FAILED");

        } catch (QuestionInPublishedPaperException e) {
            System.out.println("Removal Blocked: " + e.toString());
        } catch (ValidationException e) {
            System.out.println("Validation Error: " + e.toString());
        } catch (Exception e) {
            System.out.println("System Error: " + e.getMessage());
        }
        sc.close();
    }
}
