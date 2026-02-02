package com.exam.bean;

public class Question {
    private String questionID;
    private String subject;
    private String topic;
    private String difficulty;
    private double marks;
    private String status;
    public String getQuestionID() { return questionID; }
    public void setQuestionID(String questionID) { this.questionID = questionID; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
