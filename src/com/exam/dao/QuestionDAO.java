package com.exam.dao;

import com.exam.bean.Question;
import com.exam.util.DBUtil;
import java.sql.*;
import java.util.*;
public class QuestionDAO {
    public Question findQuestion(String id) throws Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps =
            con.prepareStatement("SELECT * FROM QUESTION_TBL WHERE QUESTION_ID=?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) return null;
        Question q = new Question();
        q.setQuestionID(rs.getString(1));
        q.setSubject(rs.getString(2));
        q.setTopic(rs.getString(3));
        q.setDifficulty(rs.getString(4));
        q.setMarks(rs.getDouble(5));
        q.setStatus(rs.getString(6));
        return q;
    }
    public List<Question> viewAllQuestions() throws Exception {
        List<Question> list = new ArrayList<>();
        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM QUESTION_TBL");
        while (rs.next()) {
            Question q = new Question();
            q.setQuestionID(rs.getString(1));
            q.setSubject(rs.getString(2));
            q.setTopic(rs.getString(3));
            q.setDifficulty(rs.getString(4));
            q.setMarks(rs.getDouble(5));
            q.setStatus(rs.getString(6));
            list.add(q);
        }
        return list;
    }
    public boolean insertQuestion(Question q) throws Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps =
            con.prepareStatement("INSERT INTO QUESTION_TBL VALUES (?,?,?,?,?,?)");
        ps.setString(1, q.getQuestionID());
        ps.setString(2, q.getSubject());
        ps.setString(3, q.getTopic());
        ps.setString(4, q.getDifficulty());
        ps.setDouble(5, q.getMarks());
        ps.setString(6, q.getStatus());
        int r = ps.executeUpdate();
        con.commit();
        return r == 1;
    }
    public boolean updateQuestionStatus(String id, String status) throws Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps =
            con.prepareStatement("UPDATE QUESTION_TBL SET STATUS=? WHERE QUESTION_ID=?");
        ps.setString(1, status);
        ps.setString(2, id);
        int r = ps.executeUpdate();
        con.commit();
        return r == 1;
    }
    public boolean deleteQuestion(String id) throws Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps =
            con.prepareStatement("DELETE FROM QUESTION_TBL WHERE QUESTION_ID=?");
        ps.setString(1, id);
        int r = ps.executeUpdate();
        con.commit();
        return r == 1;
    }
    public List<Question> findQuestionsBySubjectAndDifficulty(
            String subject, String difficulty) throws Exception {
        List<Question> list = new ArrayList<>();
        Connection con = DBUtil.getConnection();
        PreparedStatement ps =
            con.prepareStatement(
                "SELECT * FROM QUESTION_TBL WHERE SUBJECT=? AND DIFFICULTY=? AND STATUS='ACTIVE'");
        ps.setString(1, subject);
        ps.setString(2, difficulty);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Question q = new Question();
            q.setQuestionID(rs.getString(1));
            q.setMarks(rs.getDouble(5));
            list.add(q);
        }
        return list;
    }
}
