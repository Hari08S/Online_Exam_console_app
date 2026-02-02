package com.exam.util;
public class QuestionInPublishedPaperException extends Exception {
    public String toString() {
        return "Question exists in a PUBLISHED test paper";
    }
}
