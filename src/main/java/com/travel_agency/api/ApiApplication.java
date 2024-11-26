package com.travel_agency.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sidney-essjr
 * @version 1.0.1
 * @apiNote API to allow other tourism companies and third-party applications to integrate with your
 * system, exposing information about destinations, travel packages, hotel availability and tourist
 * activities.
 */

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
