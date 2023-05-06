package com.asem.byanat.controller;

import com.asem.byanat.model.Tower;
import com.asem.byanat.service.TowerService;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/challenge")
@Validated
public class TowerController {
    private TowerService towerService;

    public TowerController(TowerService towerService) {
        this.towerService = towerService;
    }

    @GetMapping(value = "/tower")
    public @ResponseBody List<Tower> getTowers(
            @RequestParam(value = "networkoperator", required = false) String networkOperator,
            @RequestParam(value = "technology", required = false) String technology,
            @RequestParam(value = "towerid", required = false) @Pattern(regexp = "\\d+", message = "Only numbers are allowed") String towerId,
            @RequestParam(value = "towertype", required = false) String towerType
    ) {
        return towerService.getTowers(networkOperator, technology, towerId, towerType);
    }
}
