package market.model.controller;

import lombok.RequiredArgsConstructor;
import market.dto.BulkDeleteDto;
import market.dto.transfer.Create;
import market.dto.transfer.Update;
import market.entity.Model;
import market.model.dto.ModelDto;
import market.model.dto.ModelView;
import market.model.service.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/model-management")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @GetMapping("/models")
    public ResponseEntity<List<ModelView>> getAll() {
        return new ResponseEntity<>(this.modelService.getModels(), HttpStatus.OK);
    }

    @GetMapping("/models/{id}")
    public ResponseEntity<ModelView> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(this.modelService.getModel(id), HttpStatus.OK);
    }

    @PostMapping("/models")
    public ResponseEntity<Model> create(@Validated(Create.class) @RequestBody ModelDto modelDto) {
        return new ResponseEntity<>(this.modelService.create(modelDto), HttpStatus.CREATED);
    }

    @PutMapping("/models")
    public ResponseEntity<Model> update(@Validated(Update.class) @RequestBody ModelDto modelDto)   {
        return new ResponseEntity<>(this.modelService.update(modelDto), HttpStatus.OK);
    }

    @DeleteMapping("/models")
    public ResponseEntity<String> delete(@Valid @RequestBody BulkDeleteDto bulkDeleteDto) {
        this.modelService.bulkDelete(bulkDeleteDto.getIds());
        return ResponseEntity.ok("deleted successfully");
    }



}
