package com.kuljava.swiatwsi.rawmaterials;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("rawmaterials.scheduler")
@Getter
@Setter
public class RawMaterialsDTO {

    private long woodIncrease;
    private long clayIncrease;
    private long ironIncrease;
    private long ironInitialValue;
    private long woodInitialValue;
    private long clayInitialValue;
}
