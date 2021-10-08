package market.storeOperation.controller;

import lombok.RequiredArgsConstructor;
import market.storeOperation.dto.StoreOperationDto;
import market.storeOperation.service.StoreOperationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/store-operation-management")
@RequiredArgsConstructor
public class StoreOperationController {

    private final StoreOperationService storeOperationService;

    @PostMapping("/operation")
    public ResponseEntity<Void> shiftProducts(@RequestBody StoreOperationDto storeOperationDto) {
        this.storeOperationService.performOperation(storeOperationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
