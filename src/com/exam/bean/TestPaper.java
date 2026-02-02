package com.exam.bean;

public class TestPaper {
    private int paperID;
    private String paperTitle;
    private String subject;
    private double totalMarks;
    private String questionIdList;
    private String status;
    public int getPaperID() { return paperID; }
    public void setPaperID(int paperID) { this.paperID = paperID; }
    public String getPaperTitle() { return paperTitle; }
    public void setPaperTitle(String paperTitle) { this.paperTitle = paperTitle; }
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    public double getTotalMarks() { return totalMarks; }
    public void setTotalMarks(double totalMarks) { this.totalMarks = totalMarks; }
    public String getQuestionIdList() { return questionIdList; }
    public void setQuestionIdList(String questionIdList) { this.questionIdList = questionIdList; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
