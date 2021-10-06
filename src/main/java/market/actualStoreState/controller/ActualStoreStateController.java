package market.actualStoreState.controller;

import lombok.RequiredArgsConstructor;
import market.actualStoreState.dto.ActualStoreStateItemView;
import market.actualStoreState.service.ActualStoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/actual-store-management")
@RequiredArgsConstructor
public class ActualStoreStateController {

    private final ActualStoreService actualStoreService;

    @GetMapping("/stores")
    public ResponseEntity<List<ActualStoreStateItemView>> getAll() {
        return new ResponseEntity<>(this.actualStoreService.getAllStores(), HttpStatus.OK);
    }

}
