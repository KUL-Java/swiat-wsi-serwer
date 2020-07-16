package com.kuljava.swiatwsi.rawmaterials;

import com.kuljava.swiatwsi.rawmaterials.repository.ClayRepository;
import com.kuljava.swiatwsi.rawmaterials.repository.IronRepository;
import com.kuljava.swiatwsi.rawmaterials.repository.WoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private static final Long WOODINCREASE = 10L;
    private static final Long CLAYINCREASE = 10L;
    private static final Long IRONINCREASE = 10L;
    @Autowired
    WoodRepository wood;
    @Autowired
    ClayRepository clay;
    @Autowired
    IronRepository iron;

    @Scheduled(fixedRate = 1000)
    public void increaseQuantities() {
        wood.increaseAll(WOODINCREASE);
        clay.increaseAll(CLAYINCREASE);
        iron.increaseAll(IRONINCREASE);
    }
}
