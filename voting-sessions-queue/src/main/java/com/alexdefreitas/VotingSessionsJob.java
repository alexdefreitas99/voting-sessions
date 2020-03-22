package com.alexdefreitas;

import org.springframework.scheduling.annotation.Scheduled;

public class VotingSessionsJob {

    @Scheduled(fixedDelayString = "${voting.sessions.job}")
    public void a(){
        System.out.println("a");
    }
}
