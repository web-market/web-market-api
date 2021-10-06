package market.supply.controller;

import lombok.RequiredArgsConstructor;
import market.dto.transfer.Create;
import market.entity.ActualStoreState;
import market.supply.dto.*;
import market.supply.service.SupplyService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
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

    @GetMapping("/supplies/search")
    public ResponseEntity<List<SupplyItemView>> getSupplyByStatus(@RequestParam LocalDateTime date, @RequestParam String status) {
        return new ResponseEntity<>(this.supplyService.getSuppliesByStatusAndArrivalDate(status, date), HttpStatus.OK);
    }

    @GetMapping("/supplies/processing/{id}")
    public ResponseEntity<SupplyCompositeItemView> getSupplyForProcessing(@PathVariable Long id) {
        return new ResponseEntity<>(this.supplyService.getSupplyCompositeItemViewById(id), HttpStatus.OK);
    }

    @GetMapping("/supplies/separation-info/{id}")
    public ResponseEntity<SupplySeparationInfoView> getSupplySeparationInfo(@PathVariable Long id) {
        return ResponseEntity.ok(this.supplyService.getSupplySeparationInfo(id));
    }

    @PostMapping("/supplies")
    public ResponseEntity<Void> create(@Validated(Create.class) @RequestBody SupplyCompositeDto supplyCompositeDto) {
        final Long supplyId = this.supplyService.create(supplyCompositeDto).getId();
        return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION,
                "/supply-management/supplies/processing/" + supplyId).build();
    }

    @PostMapping("/supplies/processing")
    public ResponseEntity<List<ActualStoreState>> processSupply(@RequestBody SupplyProcessingDto supplyProcessingDto) {
        return ResponseEntity.ok(this.supplyService.processSupply(supplyProcessingDto));
    }

    @PostMapping("/supplies/identification-number/check")
    public ResponseEntity<Boolean> checkIdentificationNumber(@Valid @RequestBody IdentificationNumberCheckDto identificationNumberCheckDto) {
        return new ResponseEntity<>(
                this.supplyService.isIdentificationNumberUnique(identificationNumberCheckDto.getIdentificationNumber()),
                HttpStatus.OK);
    }

    @DeleteMapping("/supplies/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.supplyService.delete(id);
        return ResponseEntity.ok("deleted successfully");
    }
}
