package com.example.businessmanagement2.controller.performance;

import com.example.businessmanagement2.controller.schedule.ScheduleController;
import com.example.businessmanagement2.controller.schedule.ScheduleDTO;
import com.example.businessmanagement2.controller.schedule.ScheduleForm;
import com.example.businessmanagement2.controller.schedule.ScheduleListDTO;
import com.example.businessmanagement2.controller.schedule.ScheduleResponseMassage;
import com.example.businessmanagement2.repository.performance.PerformanceEntity;
import com.example.businessmanagement2.repository.schedule.ScheduleEntity;
import com.example.businessmanagement2.service.performance.PerformanceService;
import java.net.URI;
import java.util.ArrayList;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class PerformanceController {

  private final PerformanceService performanceService;

  private static PerformanceDTO toPerformanceDTO(PerformanceEntity performanceEntity){
    var performanceDTO = new PerformanceDTO(performanceEntity.getPerformanceId(), performanceEntity.getUserId(),
        performanceEntity.getWorkingDate(), performanceEntity.getPlace(), performanceEntity.getWorkContent(),
        performanceEntity.getNumberOfPeople());
    return performanceDTO;
  }

  @GetMapping("/performances/{id}")
  private ResponseEntity<PerformanceDTO> showPerformance(@PathVariable("id") Long performanceId) {
    var entity = performanceService.findById(performanceId);
    var dto = toPerformanceDTO(entity);
    return ResponseEntity.ok(dto);
  }

  @GetMapping("/performances")
  private ResponseEntity<PerformanceListDTO> findPerformanceList() {
    var entityList = performanceService.findPerformanceList();
    var dtoList = entityList.stream().map(PerformanceController::toPerformanceDTO)
        .collect(Collectors.toList());
    var dto = new PerformanceListDTO(new ArrayList<>(dtoList));
    return ResponseEntity.ok(dto);
  }

  @PostMapping("/performances")
  private ResponseEntity<PerformanceResponseMassage> createPerformance(
      @RequestBody @Validated PerformanceForm form, UriComponentsBuilder uriBuilder) {
    PerformanceEntity pe = performanceService.create(form.getUserId(), form.getWorkingDate(),
        form.getPlace(), form.getWorkContent(), form.getNumberOfPeople());
    URI uri = uriBuilder.path("performances/" + pe.getPerformanceId()).build().toUri();
    var srm = new PerformanceResponseMassage("作業実績を登録しました");
    return ResponseEntity.created(uri).body(srm);
  }

  @PatchMapping("/performances/{id}")
  private ResponseEntity<PerformanceResponseMassage> updatePerformance(
      @PathVariable("id") Long performanceId, @RequestBody @Validated PerformanceForm form) {
    performanceService.update(performanceId, form.getUserId(), form.getWorkingDate(),
        form.getPlace(), form.getWorkContent(), form.getNumberOfPeople());
    var srm = new PerformanceResponseMassage("作業実績を更新しました");
    return ResponseEntity.ok(srm);
  }

  @DeleteMapping("/performances/{id}")
  private ResponseEntity<PerformanceResponseMassage> deletePerformance(
      @PathVariable("id") Long performanceId) {
    performanceService.delete(performanceId);
    return ResponseEntity.noContent().build();
  }
}
