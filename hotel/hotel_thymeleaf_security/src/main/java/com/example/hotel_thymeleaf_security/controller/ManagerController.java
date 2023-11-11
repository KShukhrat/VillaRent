package com.example.hotel_thymeleaf_security.controller;

import com.example.hotel_thymeleaf_security.entity.dtos.VillageResponseDto;
import com.example.hotel_thymeleaf_security.entity.villa.VillaRentEntity;
import com.example.hotel_thymeleaf_security.service.village.VillageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/manager")
//@PreAuthorize(value = "hasRole('ADMIN') or hasRole('MANAGER') or hasRole('SUPER_ADMIN')")
@RequiredArgsConstructor
public class ManagerController {
    private final VillageService villageService;
    @GetMapping()
    public String managerDashboard(){
        return "manager/index";
    }
    @GetMapping("/add/hotel")
    public String addVillagePage(){
        return "manager/add-hotel";
    }

    @PostMapping("/add/hotel")
    public String addHotel(@ModelAttribute VillageResponseDto dto,
                           Principal principal,
                           Model model){
        villageService.save(dto, principal.getName());
        return "redirect:/manager";
    }

    @GetMapping("/getVillagesOwner")
    public String getOwnerVillages(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            Principal principal
    ) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<VillaRentEntity> villas = villageService.getAllPage(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("villas", villas);
        int totalPages = villas.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "manager/hotel-list";
    }

    @GetMapping("/getAllVillages")
    public String getAllVillages(Model model,
                                 @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("size") Optional<Integer> size
    ) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<VillaRentEntity> villas = villageService.getAllPage(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("villas", villas);
        int totalPages = villas.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "manager/hotel-list";
    }
}