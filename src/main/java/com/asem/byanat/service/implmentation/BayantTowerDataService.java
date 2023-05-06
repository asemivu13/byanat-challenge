package com.asem.byanat.service.implmentation;

import com.asem.byanat.model.Tower;
import com.asem.byanat.service.TowerService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BayantTowerDataService implements TowerService {
    private static final String TOWER_DATA_ENDPOINT_URL = "https://byanat.wiremockapi.cloud/api/v1/towers";
    private final RestTemplate restTemplate;
    private String networkOperator;
    private String technology;
    private Long towerId;
    private String towerType;
    public BayantTowerDataService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<Tower> getTowers(String networkOperator, String technology, String towerId, String towerType) {
        setFiltrationData(networkOperator, technology, towerId, towerType);
        List<Tower> response = new ArrayList<>();
        response = restTemplate.exchange(
                TOWER_DATA_ENDPOINT_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Tower>>() {
                }
        ).getBody();
        return filterDataByParams(response);
    }

    private void setFiltrationData(String networkOperator, String technology, String towerId, String towerType) {
        this.networkOperator = networkOperator;
        this.technology = technology;
        if (towerId != null) {
            this.towerId = Long.parseLong(towerId);
        }
        this.towerType = towerType;
    }

    private List<Tower> filterDataByParams(List<Tower> data) {
        List<Tower> result = new ArrayList<>();

        for (Tower tower : data) {
            if (this.networkOperator != null && !tower.getOperator().strip().equalsIgnoreCase(this.networkOperator.strip())) {
                continue;
            }
            if (this.technology != null && !tower.getTechnology().strip().equalsIgnoreCase(this.technology.strip())) {
                continue;
            }
            if (this.towerType != null && !tower.getTowerType().strip().equalsIgnoreCase(this.towerType.strip())) {
                continue;
            }
            if (this.towerId != null && !Objects.equals(tower.getTowerId(), this.towerId)) {
                continue;
            }

            result.add(tower);
        }

        return result;
    }

}
