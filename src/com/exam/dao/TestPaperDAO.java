package com.exam.dao;

import com.exam.bean.TestPaper;
import com.exam.util.DBUtil;
import java.sql.*;
import java.util.*;
public class TestPaperDAO {
    public int generatePaperID() throws Exception {
        Connection con = DBUtil.getConnection();
        Statement st = con.createStatement();
        ResultSet rs =
            st.executeQuery("SELECT NVL(MAX(PAPER_ID),810000)+1 FROM TEST_PAPER_TBL");
        rs.next();
        return rs.getInt(1);
    }
    public boolean recordTestPaper(TestPaper p) throws Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps =
            con.prepareStatement("INSERT INTO TEST_PAPER_TBL VALUES (?,?,?,?,?,?)");
        ps.setInt(1, p.getPaperID());
        ps.setString(2, p.getPaperTitle());
        ps.setString(3, p.getSubject());
        ps.setDouble(4, p.getTotalMarks());
        ps.setString(5, p.getQuestionIdList());
        ps.setString(6, p.getStatus());
        int r = ps.executeUpdate();
        con.commit();
        return r == 1;
    }
    public boolean updatePaperStatus(int paperID, String status) throws Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps =
            con.prepareStatement("UPDATE TEST_PAPER_TBL SET STATUS=? WHERE PAPER_ID=?");
        ps.setString(1, status);
        ps.setInt(2, paperID);
        int r = ps.executeUpdate();
        con.commit();
        return r == 1;
    }
    public TestPaper findPaperByID(int paperID) throws Exception {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps =
            con.prepareStatement("SELECT * FROM TEST_PAPER_TBL WHERE PAPER_ID=?");
        ps.setInt(1, paperID);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) return null;
        TestPaper p = new TestPaper();
        p.setPaperID(rs.getInt(1));
        p.setPaperTitle(rs.getString(2));
        p.setSubject(rs.getString(3));
        p.setTotalMarks(rs.getDouble(4));
        p.setQuestionIdList(rs.getString(5));
        p.setStatus(rs.getString(6));
        return p;
    }
    public List<TestPaper> findPublishedPapersContainingQuestion(String qid)
            throws Exception {
        List<TestPaper> list = new ArrayList<>();
        Connection con = DBUtil.getConnection();
        PreparedStatement ps =
            con.prepareStatement(
                "SELECT * FROM TEST_PAPER_TBL WHERE STATUS='PUBLISHED' AND QUESTION_ID_LIST LIKE ?");
        ps.setString(1, "%" + qid + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) list.add(new TestPaper());
        return list;
    }
}
