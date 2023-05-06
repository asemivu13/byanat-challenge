package com.asem.byanat.controller;

import com.asem.byanat.model.Tower;
import com.asem.byanat.service.TowerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get list of towers by filter: ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Tower.class)) })
    })
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
