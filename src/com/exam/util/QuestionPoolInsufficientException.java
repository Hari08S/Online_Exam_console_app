package com.exam.util;
public class QuestionPoolInsufficientException extends Exception {
    public String toString() {
        return "Not enough ACTIVE questions available";
    }
}
