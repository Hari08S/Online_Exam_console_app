package com.exam.service;

import com.exam.bean.*;
import com.exam.dao.*;
import com.exam.util.*;
import java.util.*;
public class ExamService {
    private QuestionDAO qdao = new QuestionDAO();
    private TestPaperDAO pdao = new TestPaperDAO();
    public Question viewQuestionDetails(String id) throws Exception {
        if (id == null || id.isEmpty())
            throw new ValidationException();
        return qdao.findQuestion(id);
    }
    public List<Question> viewAllQuestions() throws Exception {
        return qdao.viewAllQuestions();
    }
    public boolean addNewQuestion(Question q) throws Exception {
        if (q.getQuestionID() == null || q.getQuestionID().isEmpty())
            throw new ValidationException();
        if (qdao.findQuestion(q.getQuestionID()) != null)
            throw new ValidationException();
        q.setStatus("ACTIVE");
        return qdao.insertQuestion(q);
    }
    public boolean removeQuestion(String qid) throws Exception {
        if (!pdao.findPublishedPapersContainingQuestion(qid).isEmpty())
            throw new QuestionInPublishedPaperException();
        return qdao.deleteQuestion(qid);
    }
    public boolean createTestPaper(
            String title, String subject, double marks, String mix)
            throws Exception {
        Map<String, Double> req = new HashMap<>();
        for (String part : mix.split(",")) {
            String[] kv = part.split("=");
            req.put(kv[0], Double.parseDouble(kv[1]));
        }
        List<String> selected = new ArrayList<>();
        double total = 0;
        for (String d : req.keySet()) {
            double need = req.get(d);
            double got = 0;
            List<Question> pool =
                qdao.findQuestionsBySubjectAndDifficulty(subject, d);
            if (pool.isEmpty())
                throw new QuestionPoolInsufficientException();
            for (Question q : pool) {
                selected.add(q.getQuestionID());
                got += q.getMarks();
                if (got >= need) break;
            }
            if (got < need)
                throw new QuestionPoolInsufficientException();
            total += got;
        }
        TestPaper p = new TestPaper();
        p.setPaperID(pdao.generatePaperID());
        p.setPaperTitle(title);
        p.setSubject(subject);
        p.setTotalMarks(total);
        p.setQuestionIdList(String.join(",", selected));
        p.setStatus("DRAFT");
        return pdao.recordTestPaper(p);
    }
    public boolean publishTestPaper(int paperID) throws Exception {
        TestPaper p = pdao.findPaperByID(paperID);
        if (p == null || !"DRAFT".equals(p.getStatus()))
            return false;
        for (String qid : p.getQuestionIdList().split(",")) {
            Question q = qdao.findQuestion(qid);
            if (q == null || !"ACTIVE".equals(q.getStatus()))
                throw new QuestionPoolInsufficientException();
        }
        return pdao.updatePaperStatus(paperID, "PUBLISHED");
    }
    public boolean archiveTestPaper(int paperID) throws Exception {
        TestPaper p = pdao.findPaperByID(paperID);
        if (p == null || !"PUBLISHED".equals(p.getStatus()))
            return false;
        return pdao.updatePaperStatus(paperID, "ARCHIVED");
    }
}
