package com.asem.byanat.service;

import com.asem.byanat.model.Tower;

import java.util.List;

public interface TowerService {
    List<Tower> getTowers(String networkOperator, String technology, String towerId, String towerType);
}
