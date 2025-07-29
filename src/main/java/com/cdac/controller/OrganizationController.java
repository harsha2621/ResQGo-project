package com.cdac.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cdac.dto.OrganizationReqDTO;
import com.cdac.service.OrganizationService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/organization")
@AllArgsConstructor
@Validated
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    @Operation(description = "Add a new organization")
    public ResponseEntity<?> addOrganization(@RequestBody @Valid OrganizationReqDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(organizationService.addOrganization(dto));
    }

    @PutMapping("/{id}")
    @Operation(description = "Update an existing organization by ID")
    public ResponseEntity<?> updateOrganization(@PathVariable Long id,
                                                @RequestBody @Valid OrganizationReqDTO dto) {
        return ResponseEntity.ok(organizationService.updateOrganization(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete an organization by ID")
    public ResponseEntity<?> deleteOrganization(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.deleteOrganization(id));
    }

    @GetMapping("/{id}")
    @Operation(description = "Get organization details by ID")
    public ResponseEntity<?> getOrganization(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.getOrganization(id));
    }
}
