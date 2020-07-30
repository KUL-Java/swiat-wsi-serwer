package com.kuljava.swiatwsi.world;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VillageToViewConverter implements Converter<Village, VillageView> {
    @Override
    public VillageView convert(Village village) {
        return new VillageView(village.getName(), village.getLocation(),village.getOwner().getUserName());
    }
}
