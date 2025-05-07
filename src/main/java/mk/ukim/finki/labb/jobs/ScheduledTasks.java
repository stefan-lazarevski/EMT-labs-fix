package mk.ukim.finki.labb.jobs;

import mk.ukim.finki.labb.repository.HousingCountViewRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTasks {

    private final HousingCountViewRepository housingCountViewRepository;

    public ScheduledTasks(HousingCountViewRepository housingCountViewRepository) {
        this.housingCountViewRepository = housingCountViewRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void refreshView(){
       housingCountViewRepository.refreshMaterializedView();
    }
}
