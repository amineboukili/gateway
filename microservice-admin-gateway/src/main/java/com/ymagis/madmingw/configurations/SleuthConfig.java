package com.ymagis.madmingw.configurations;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Amine BOUKILI
 */

@Configuration
public class SleuthConfig {
    public Sampler defaultSampler() {

        return Sampler.ALWAYS_SAMPLE; // Mark all requests by ID
    }
}
