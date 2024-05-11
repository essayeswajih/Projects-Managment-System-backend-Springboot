package com.islem.tasks.schedulingtasks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.islem.tasks.entity.Project;
import com.islem.tasks.service.EmailService;
import com.islem.tasks.service.impl.ProjectServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class ScheduledTasks {

    private final EmailService emailService;
    private final ProjectServiceImpl projectService;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(fixedRate = 5000)
    @Scheduled(cron = "0 0 0 * * ?")
    public void ProjectDelayEndSoon() {
        List<Project> lp = new ArrayList<>();
        lp=projectService.findProjectsEndingToday();
        for(Project p : lp){
            String email = p.getUser().getEmail();
            String subject = "Projecty : Project Deadline Alert";
            Date d = p.getEndDate();
            String text = p.getName()+" Will end soon at "+d;
            emailService.sendSimpleMessage(email,subject,text);
            log.info("msg sended");
        }
    }
}