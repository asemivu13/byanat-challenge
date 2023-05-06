package com.asem.byanat.controller;

import com.asem.byanat.model.Tower;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/challenge")
public class TowerController {
    @GetMapping(value = "/tower")
    public @ResponseBody List<Tower> getTowers(
            @RequestParam(value = "networkoperator", required = false) String networkOperator,
            @RequestParam(value = "technology", required = false) String technology,
            @RequestParam(value = "towerid", required = false) String towerId,
            @RequestParam(value = "towertype", required = false) String towerType
    ) {
        return null;
    }
}
