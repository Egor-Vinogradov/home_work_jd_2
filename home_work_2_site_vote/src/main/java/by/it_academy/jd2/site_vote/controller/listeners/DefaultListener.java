package by.it_academy.jd2.site_vote.controller.listeners;

import by.it_academy.jd2.site_vote.service.VoteService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DefaultListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String pathFile = sce.getServletContext().getInitParameter("pathFile");
        saveRest(pathFile, false);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        String pathFile = sce.getServletContext().getInitParameter("pathFile");
        saveRest(pathFile, true);
    }

    private void saveRest(String pathFile, boolean save) {
        VoteService service = VoteService.getInstance();
        if (save) {
            service.saveResult(service.getArtistResult(), service.getGenreResult(),
                    service.getAboutResult(), pathFile);
        } else {
            service.readData();
        }
    }
}
