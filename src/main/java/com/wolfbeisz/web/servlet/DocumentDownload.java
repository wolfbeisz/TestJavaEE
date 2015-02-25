package com.wolfbeisz.web.servlet;

import org.apache.commons.io.IOUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Philipp on 25.02.2015.
 */
@WebServlet("/downloadDocument")
public class DocumentDownload extends HttpServlet {
    @Resource(name = "java:app/jdbc/main", type=DataSource.class)
    private DataSource main;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!isRevisionIdValid(req.getParameter("revisionId"))) {
            // parameter not set
            resp.sendError(400);
            return;
        }

        long revisionId = Long.parseLong(req.getParameter("revisionId"));


        Connection connection = null;
        PreparedStatement revisionByIdSelect = null;
        try {
            connection = main.getConnection();
            revisionByIdSelect = connection.prepareStatement("SELECT * FROM REVISIONS R WHERE R.ID = ?");
            ResultSet revision = findRevision(revisionByIdSelect, revisionId);
            if (revision == null) {
                resp.sendError(404);
                return;
            }


            String mimetype = revision.getString("mimetype");
            resp.setHeader("Content-Type", mimetype);
            resp.setHeader("Content-Disposition", "attachment");
            InputStream file = revision.getBinaryStream("filecontent");
            IOUtils.copy(file, resp.getOutputStream());
        } catch (SQLException e) {
            throw new ServletException(e);
        } finally {
            try {
                revisionByIdSelect.close();
            } catch (Exception e) {

            }

            try {
                connection.close();
            } catch (Exception e) {

            }
        }
    }

    private boolean isRevisionIdValid(String revisionId) {
        if (revisionId == null) {
            return false;
        }

        try {
            Long.parseLong(revisionId);
        } catch(NumberFormatException e) {
            return false;
        }

        return true;
    }

    private ResultSet findRevision(PreparedStatement revisionByIdSelect, long id) throws SQLException{
            revisionByIdSelect.setLong(1, id);
            ResultSet resultSet = revisionByIdSelect.executeQuery();

            if (resultSet.next()) {
                return resultSet;
            }

            return null;
    }
}
