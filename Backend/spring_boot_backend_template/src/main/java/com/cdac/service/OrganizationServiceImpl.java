package com.cdac.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.dao.OrganizationDao;
import com.cdac.dao.UserDao;
import com.cdac.dto.ApiResponse;
import com.cdac.dto.OrganizationReqDTO;
import com.cdac.dto.OrganizationRespDTO;
import com.cdac.entities.Organization;
import com.cdac.entities.User;
import com.cdac.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;
    private final UserDao userDao;
    private final ModelMapper modelMapper;
    
    
    @Override
    public OrganizationRespDTO addOrganization(OrganizationReqDTO dto) {
        // Check if the admin user exists
        User admin = userDao.findById(dto.getAdminId())
                .orElseThrow(() -> new ResourceNotFoundException("Admin user not found with ID: " + dto.getAdminId()));

        // Map the DTO to entity
        Organization organization = modelMapper.map(dto, Organization.class);
        organization.setAdmin(admin); // manually set admin

        // Save and return response DTO
        Organization saved = organizationDao.save(organization);
        return modelMapper.map(saved, OrganizationRespDTO.class);
    }


    @Override
    public OrganizationRespDTO updateOrganization(Long id, OrganizationReqDTO dto) {
        // Fetch existing organization
        Organization org = organizationDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with ID: " + id));

        // Check if the new admin user exists
        User admin = userDao.findById(dto.getAdminId())
                .orElseThrow(() -> new ResourceNotFoundException("Admin user not found with ID: " + dto.getAdminId()));

        // Update fields using modelMapper (except admin)
        modelMapper.map(dto, org);
        org.setAdmin(admin); // manually set updated admin

        // Save and return updated response
        Organization updated = organizationDao.save(org);
        return modelMapper.map(updated, OrganizationRespDTO.class);
    }

    @Override
    public ApiResponse deleteOrganization(Long id) {
        Organization org = organizationDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found"));
        organizationDao.delete(org);
        return new ApiResponse("Organization deleted successfully") ;
    }

    @Override
    public OrganizationRespDTO getOrganization(Long id) {
        Organization org = organizationDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found"));
        return modelMapper.map(org, OrganizationRespDTO.class);
    }
}
