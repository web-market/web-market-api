package market.supply.controller;

import lombok.RequiredArgsConstructor;
import market.supply.dto.SupplyCompositeDto;
import market.supply.dto.SupplyItemView;
import market.supply.service.SupplyService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/supply-management")
@RequiredArgsConstructor
public class SupplyController {

    private final SupplyService supplyService;

    @GetMapping("/supplies")
    public ResponseEntity<List<SupplyItemView>> getAll() {
        return new ResponseEntity<>(this.supplyService.getSupplies(), HttpStatus.OK);
    }

    @GetMapping("/supplies/{id}")
    public ResponseEntity<SupplyItemView> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(this.supplyService.getSupply(id), HttpStatus.OK);
    }

    @PostMapping("/supplies")
    public ResponseEntity<Void> create(@RequestBody SupplyCompositeDto supplyCompositeDto) {
        this.supplyService.create(supplyCompositeDto);
        return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION,
                "/supply-management/supplies").build();
    }

}
