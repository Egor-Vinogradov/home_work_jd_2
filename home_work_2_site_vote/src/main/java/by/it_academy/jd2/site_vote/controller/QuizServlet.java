package by.it_academy.jd2.site_vote.controller;

import by.it_academy.jd2.site_vote.service.VoteService;
import by.it_academy.jd2.site_vote.controller.Html;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "QuizServlet", urlPatterns = "/")
public class QuizServlet extends HttpServlet {

    private final VoteService service;
    private Html html = Html.getInstance();

    @Override
    public void destroy() {
//        this.service.saveResult(this.service.getArtistResult(), this.service.getGenreResult(),
//                this.service.getAboutResult());
    }

    @Override
    public void init() throws ServletException {
        this.service.readData();
    }

    public QuizServlet() {
        this.service = VoteService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException
    {

        String save = req.getParameter("save");
        String pathFile = req.getServletContext().getInitParameter("pathFile");

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        if (save !=null) {
            this.service.saveResult(this.service.getArtistResult(), this.service.getGenreResult(),
                    this.service.getAboutResult(), pathFile);
            writer.write(pageResult());
            return;
        }

        writer.write(html.initPages(null));

        writer.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String artist = req.getParameter("artist");
        String[] genres = req.getParameterValues("genre");
        String about = req.getParameter("about");
        String result = req.getParameter("result");


        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();



        if (result != null) {
            writer.write(pageResult());
            return;
        }

        if (genres == null || genres.length < 3) {
            writer.write(html.initPages(html.alertMessage()));
            return;
        }

        this.service.addVote(artist, genres, about);

        writer.write(html.initPages(null));

        writer.close();

    }

    public String pageResult() {
        Map<String, Integer> artistResult = this.service.getArtistResult();
        Map<String, Integer> genreResult = this.service.getGenreResult();
        List<String> aboutResult = this.service.getAboutResult();

        return html.initPageResult(artistResult, genreResult, aboutResult);
    }
}
