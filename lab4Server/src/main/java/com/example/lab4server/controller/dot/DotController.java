package com.example.lab4server.controller.dot;

import com.example.lab4server.DTO.dot.DotRequest;
import com.example.lab4server.DTO.dot.DotResponse;
import com.example.lab4server.DTO.dot.CircleResponse;
import com.example.lab4server.sequrity.service.SecurityUser;
import com.example.lab4server.service.dot.DotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DotController {

    private final DotService dotService;

    @Autowired
    public DotController(DotService dotService) {
        this.dotService = dotService;
    }

    @PostMapping("/dots")
    public void saveDot(@RequestBody @NotNull @Valid DotRequest dotRequest,
                        @AuthenticationPrincipal SecurityUser securityUser) {
        dotService.saveDot(dotRequest, securityUser.getUserId());
    }

    @DeleteMapping("/dots")
    public void deleteAllDotsByUserId(@AuthenticationPrincipal SecurityUser securityUser) {
        dotService.deletedAllDotsByUserId(securityUser.getUserId());
    }

    @GetMapping("/circles/{r}")
    public List<CircleResponse> getAllCirclesByUserId(@PathVariable Double r,
                                                      @AuthenticationPrincipal SecurityUser securityUser) {
        return dotService.findAllCirclesByUserId(securityUser.getUserId(), r);
    }

    @GetMapping("/dots")
    public List<DotResponse> getAllDotsByUserId(@AuthenticationPrincipal SecurityUser securityUser) {
        return dotService.findAllDotsByUserId(securityUser.getUserId());
    }
}
